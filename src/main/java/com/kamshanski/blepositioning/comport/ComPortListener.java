package com.kamshanski.blepositioning.comport;

import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.blepositioning.utils.U;
import com.kamshanski.utils.dataflow.DataPropagator;
import com.kamshanski.utils.dataflow.LiveData;

public class ComPortListener {
    LiveData<Model> model;
    public final DataPropagator<String> log = new DataPropagator<>();

    public void println(String msg) {
        log.set(msg);
    }

    public ComPortListener() {
        this.model = Model.getClearInstance(false);
    }

    public void onTerminal(String msg) {
        println(msg);
    }

    public void onPayload(long timeStart, long timeEnd, long arrivalTime, String msg) {
        model.get().putNewMeasurements(timeEnd - timeStart, arrivalTime, msg);
        println("New payload arrived: " + (timeEnd - timeStart) + " ms");
        println(msg);
    }

    public void onError(String msg) {
        println("ERROR OCCURRED!");

//            println("Experiment data is saved to" + path);
    }

    public void onReady(String msg) {
        model = Model.getClearInstance(true);
        println("Device is On. All previous data was erased");
    }

    public void onRestarting(String msg) {
        println("Device is restarting");
    }

    public void onTargetsSet(String sizeStr, String capacityStr, String macStr) {
        if (model.get().setTargets(sizeStr, capacityStr, macStr)) {
            println(model.get().getTargetsString());
        } else {
            println("Received TargetSet is corrupted");
        }
    }

    public void onSlavesNumber(String msg) {
        println("Slave msg: " + msg);
        int slaveNum = -1;
        try {
            slaveNum = Integer.parseInt(msg);
            if (!model.get().setSlavesNum(slaveNum)) {
                println("Received slave num is not correct!");
                return;
            }
        } catch (NumberFormatException e) {
            U.nout("ERROR!!!!  " + e.getMessage());
        }
    }
}
