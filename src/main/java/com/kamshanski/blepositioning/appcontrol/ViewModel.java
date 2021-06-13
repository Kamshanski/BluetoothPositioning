package com.kamshanski.blepositioning.appcontrol;


import com.kamshanski.blepositioning.comport.ComPortListener;
import com.kamshanski.blepositioning.comport.ComReader;
import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.excel.Excel;
import com.kamshanski.blepositioning.model.storage.ComplexStorage;
import com.kamshanski.blepositioning.model.storage.PositioningStorage;
import com.kamshanski.utils.dataflow.DataPropagator;
import com.kamshanski.utils.dataflow.LiveData;
import com.kamshanski.utils.structures.wrappers.Mutable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ViewModel {
    public final LiveData<Boolean> experimentInOn = new LiveData<>(false);
    public final LiveData<String> expName = new LiveData<>("");
    public final LiveData<String> comPortNum = new LiveData<>("3");
    public final LiveData<Boolean> isConnected = new LiveData<>(false);
    public final LiveData<Integer> targetCount = new LiveData<>(0);
    public final LiveData<Integer> slaveCount = new LiveData<>(0);
    public final LiveData<String> targetsListString = new LiveData<>("");

    public final DataPropagator<String> experimentResultsMessage = new DataPropagator<>();
    public final DataPropagator<String> printMessage = new DataPropagator<>();
    public final DataPropagator<String> programLogMessage = new DataPropagator<>();

    private final LiveData<Model> model;
    private final ComReader comReader;

    public ViewModel() {
        this.model = Model.getClearInstance(false);
        model.observe(m -> {
            m.latestPosition.observe(ViewModel.this::printNewPosition, false);
            m.processingFinished.observe(arg -> {
                if (experimentInOn.get()) {
                    displayExperimentsResults();
                }
                println(arg + " package was processed successfully");
            });
            m.slavesNum.observe(ViewModel.this.slaveCount::set);
            m.targetsNum.observe(ViewModel.this.targetCount::set);
            m.targetsMacs.observe(v -> ViewModel.this.targetsListString.set(model.get().getTargetsSetString()));
        });

        ComPortListener comPortListener = new ComPortListener();
        comPortListener.log.observe(this::println);
        comReader = new ComReader(comPortListener);


        experimentInOn.observe(newVal -> model.get().expIsOn = newVal);
    }

    // Button Listeners
    public void openConnection() {
        String s = comPortNum.get();
        // Check comport num to be non-negative integer
        try {
            int comPortNum = Integer.parseInt(s);
            if (comPortNum < 0) {
                throw new NumberFormatException("Number must be non-negative");
            }

            if (comReader.isOpened()) {
                if (comReader.close()) {
                    isConnected.set(false);
                    println("Disconnected Successfully");
                } else {
                    println("Disconnection failed");
                }
            } else {
                if (comReader.open(comPortNum)) {
                    isConnected.set(true);
                    println("Connected Successfully");
                } else {
                    println("Connection failed");
                }
            }

        } catch (NumberFormatException ex) {
            println("Inserted ComPort Number isn't an Integer value. Insert int");
            printException(ex);
        }
    }


    public void record() {
        if (expName.get() == null || expName.get().isEmpty()) {
            printToExperimentsResultsLabel("Experiment name is null!!!");
            return;
        }

        boolean prevState = model.get().expIsOn;
        model.get().expIsOn = false;

        // Запись

        List<ComplexStorage> css = model.get().getExpStorages();
        if (css.size() > 0) {

            final Excel excel = new Excel(10, 1);

            int limit = css.get(0).size();
            int rowIndex = 0, maxRowIndex = 0;
            for (int i = 0; i < limit; i++) {
                for (ComplexStorage cs : css) {
                    int temp = excel.putData(cs.name(), cs.get(i), rowIndex);
                    if (temp > maxRowIndex) {
                        maxRowIndex = temp;
                    }
                }
                rowIndex = maxRowIndex;
            }

            String expPath = excel.save(expName.get());
            printToExperimentsResultsLabel("Experiment " + expName + " is saved to " + expPath);
        } else {
            println("No RSSI to be recorded");
        }


        var poss = model.get().posExpMap;
        if (poss.size() > 0) {
            final Excel posExcel = new Excel(10, 1);

            final Mutable.Int c = new Mutable.Int(1);
            final Mutable.Int row = new Mutable.Int(10);
            poss.forEach((tag, ps) -> {
                row.v = 10;
                posExcel.putCell(row.v, c.v, tag);

                row.add(1);
                posExcel.putCell(row.v, c.v, "X");
                posExcel.putCell(row.v, c.v + 1, "Y");

                row.add(1);
                ps.forEach(dimensions -> {
                    posExcel.putCell(row.v, c.v, dimensions.x);
                    posExcel.putCell(row.v, c.v + 1, dimensions.y);
                    row.add(1);
                });

                c.add(3);
            });

            String posExpPath = posExcel.save("Pos_" + expName.get());

            // Запись (конец)

            printToExperimentsResultsLabel("Positions of " + expName + " are saved to " + posExpPath);
        } else {
            println("No positions to be recorded");
        }
        model.get().expIsOn = prevState;
    }

    public void remove() {
        boolean prevState = model.get().expIsOn;
        model.get().expIsOn = false;
        for (ComplexStorage expStorage : model.get().getExpStorages()) {
            expStorage.clear();
        }
        model.get().posExpMap.values().forEach(ArrayList::clear);
        model.get().expIsOn = prevState;
        printToExperimentsResultsLabel("Storages are cleared");
    }

    public void displayExperimentsResults() {
        StringBuilder builder = new StringBuilder();
        for (ComplexStorage expStorage : model.get().getExpStorages()) {
            builder.append(expStorage.name())
                    .append(": ")
                    .append(" (")
                    .append(String.format(Locale.US, "%.2f", expStorage.getMean()))
                    .append(")")
                    .append(expStorage.fullSize())
                    .append("\n");
        }

        model.get().posExpMap.forEach((tag, list) ->
            builder
                    .append(tag)
                    .append(") ")
                    .append(list.size())
                    .append('\n')
        );

        printToExperimentsResultsLabel(builder.toString());
    }


    private void printNewPosition(HashMap<String, Dimensions> dimensions) {
        StringBuilder b = new StringBuilder();
        dimensions.forEach((tag, dim) -> {
                if (tag != null && dim != null)
                    b.append("By ")
                            .append(tag)
                            .append("\n")
                            .append(String.format("   X = %.2f, Y = %.2f\n", dim.x, dim.y));
        });
    }

    // Utils

    private void printToExperimentsResultsLabel(String msg) {
        experimentResultsMessage.set(msg);
    }

    private void printToProgramLog(String msg) {
        programLogMessage.set(msg);
    }

    private void printException(Exception ex) {
        StackTraceElement[] stes = ex.getStackTrace();
        for (StackTraceElement ste : stes) {
            println(ste.toString());
        }
    }

    private void println(String s) {
        printMessage.set(s);
    }


    ///// trash

    // //Show serial Ports
    //            SerialPort[] ports = comReader.getPortsList();
    //            StringBuilder builder = new StringBuilder(150);
    //            for (int i = 0; i < ports.length; i++) {
    //                builder.append(i)
    //                        .append(") DescriptivePortName: ").append(ports[i].getDescriptivePortName())
    //                        .append(", PortDescription: ").append(ports[i].getPortDescription())
    //                        .append(", SystemPortName: ").append(ports[i].getSystemPortName())
    //                        .append(" |\n ");
    //            }
    //            statusLabel.setText(builder.toString());
}