package com.kamshanski.blepositioning.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.kamshanski.blepositioning.appcontrol.PositionAreaPlotViewModel;
import com.kamshanski.blepositioning.appcontrol.PositionTrendPlotViewModel;
import com.kamshanski.blepositioning.appcontrol.RssiPlotViewModel;
import com.kamshanski.blepositioning.appcontrol.ViewModel;
import com.kamshanski.blepositioning.utils.SquareDimension;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {
    private JPanel panel1;
    private JPanel mapPlotPanel;
    private JPanel expCard;
    private JPanel radioPlotPanel;
    private JButton btnConnect;
    private JLabel lblSlavesNum;
    private JLabel lblTargetsNum;
    private JLabel lblExperimentOnOff;
    private JButton btnStartStopExperiment;
    private JButton btnRecordExperiment;
    private JTextArea txProgramLog;
    private JButton btnRemoveRecords;
    private JButton btnShowResults;
    private JTextArea txExperimentName;
    private JComboBox cmbComPortNum;
    private JTextArea txExperimentLog;
    private JTextArea txComPortLog;
    private JTextArea txTargetsSet;
    private JScrollPane scrollComPortLog;
    private JPanel trackingCardPanel;
    private JTextArea txPositionLog;
    private JPanel cardHolder;
    private JPanel expCardPanel;
    private JButton btnExperiment;
    private JButton btnTracking;
    private JPanel posHistoryPanel;
    private JSplitPane sp2;
    private JSplitPane sp1;

    //PaintPanel paintPanel;
    RssiPlotViewModel rssiPlotViewModel;
    PositionAreaPlotViewModel positionAreaPlotViewModel;
    PositionTrendPlotViewModel positionTrendPlotViewModel;

    XChartPanel<XYChart> positionGraphPanel;
    XChartPanel<XYChart> rssiGraphPanel;
    XChartPanel<XYChart> positionHistoryGraphPanel;

    private CardLayout cardLayout;
    private ViewModel viewModel;

    public Application() {
        initApplication();

        setContentPane(panel1);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        createUIComponents();

        setVisible(true);
    }

    public void initApplication() {
        // 248
        Color chartColor = new Color(255, 255, 255);

        rssiPlotViewModel = new RssiPlotViewModel(200);
        rssiPlotViewModel.getStyler().setChartBackgroundColor(chartColor);

        positionAreaPlotViewModel = new PositionAreaPlotViewModel(
                new SquareDimension(100, -30, 100, -30),
                new SquareDimension(70, 0, 70, 0)
        );
        positionAreaPlotViewModel.getStyler().setChartBackgroundColor(chartColor);

        positionTrendPlotViewModel = new PositionTrendPlotViewModel(15);
        positionTrendPlotViewModel.getStyler().setChartBackgroundColor(chartColor);

        setSize(900, 700);
        setTitle("BLE Positioning App");
        try {
            setIconImage(ImageIO.read(new File("./src/main/resources/images/Bluetooth.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewModel = new ViewModel();
    }

    private void createUIComponents() {
        cardLayout = (CardLayout) cardHolder.getLayout();
        cardLayout.show(cardHolder, trackingCardPanel.getName());

        sp2.setDividerLocation(885);
        sp1.setDividerLocation(641);

        positionGraphPanel = new XChartPanel<>(positionAreaPlotViewModel.getGraph());
        mapPlotPanel.add(positionGraphPanel);

        positionHistoryGraphPanel = new XChartPanel<>(positionTrendPlotViewModel.getGraph());
        posHistoryPanel.add(positionHistoryGraphPanel);

        rssiGraphPanel = new XChartPanel<>(rssiPlotViewModel.getGraph());
        radioPlotPanel.add(rssiGraphPanel);

        cmbComPortNum.setSelectedIndex(3);

        setListeners();
    }

    private void setListeners() {
        btnConnect.addActionListener(e -> viewModel.openConnection());

        txExperimentName.getDocument().addDocumentListener(new TextChangedListener() {
            @Override
            public void onTextChanged(String value) {
                viewModel.expName.set(value);
            }
        });

        viewModel.printMessage.observe(this::println);

        btnExperiment.addActionListener(e -> cardLayout.show(cardHolder, expCardPanel.getName()));
        btnTracking.addActionListener(e -> cardLayout.show(cardHolder, trackingCardPanel.getName()));

        cmbComPortNum.addItemListener(i -> viewModel.comPortNum.set(i.getItem().toString()));

        viewModel.experimentResultsMessage.observe(msg -> txExperimentLog.setText(msg));
        viewModel.programLogMessage.observe(msg -> txProgramLog.setText(msg));

        // ExpCard
        btnStartStopExperiment.addActionListener(e -> viewModel.experimentInOn.set(!viewModel.experimentInOn.get()));
        viewModel.experimentInOn.addConstraint(arg -> {
            if (viewModel.expName.get() == null || viewModel.expName.get().isEmpty()) {
                printToExperimentsResultsLabel("Experiment name is null!!!");
                return false;
            }
            return true;
        });
        viewModel.experimentInOn.observe(isOn -> {
            if (isOn) {
                btnStartStopExperiment.setText("Stop Experiment");
                lblExperimentOnOff.setText("On");
            } else {
                btnStartStopExperiment.setText("Start Experiment");
                lblExperimentOnOff.setText("Off");
            }
        });
        viewModel.isConnected.observe(connected -> {
            if (connected) {
                btnConnect.setText("Disconnect from Main");
            } else {
                btnConnect.setText("Connect to Main");
            }
        });

        viewModel.targetCount.observe(sum -> lblTargetsNum.setText(sum.toString()));
        viewModel.targetsListString.observe(set -> txTargetsSet.setText(set));
        viewModel.slaveCount.observe(count -> lblSlavesNum.setText(count.toString()));

        btnRecordExperiment.addActionListener(e -> viewModel.record());
        btnRemoveRecords.addActionListener(e -> viewModel.remove());
        btnShowResults.addActionListener(e -> viewModel.displayExperimentsResults());


        rssiPlotViewModel.plotUpdateNotifier.listen(rssiGraphPanel::repaint);
        positionAreaPlotViewModel.plotUpdateNotifier.listen(positionGraphPanel::repaint);
        positionTrendPlotViewModel.plotUpdateNotifier.listen(positionHistoryGraphPanel::repaint);
        positionTrendPlotViewModel.positionLogMessage.observe(txPositionLog::setText);

        // TrackCard
//        pField.setPreferredSize(new Dimension(500, 500));
//        pField.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                Container pd = e.getComponent().getParent();
//                int w = pd.getWidth();
//                int h = pd.getHeight();
//                Dimension d = e.getComponent().getPreferredSize();
//                d.setSize(h, h);
//                e.getComponent().setPreferredSize(d);
//                U.nout("new");
//
////                if (w != h) {
////                    e.getComponent().setSize(h, h);
////                }
//            }
//        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setAutoscrolls(true);
        panel1.setMinimumSize(new Dimension(0, 0));
        panel1.setPreferredSize(new Dimension(800, 800));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setInheritsPopupMenu(false);
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(panel2, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Targets: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Slaves: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label2, gbc);
        lblSlavesNum = new JLabel();
        lblSlavesNum.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(lblSlavesNum, gbc);
        lblTargetsNum = new JLabel();
        lblTargetsNum.setText("0");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(lblTargetsNum, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("ComPort:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel2.add(label3, gbc);
        cmbComPortNum = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("0");
        defaultComboBoxModel1.addElement("1");
        defaultComboBoxModel1.addElement("2");
        defaultComboBoxModel1.addElement("3");
        defaultComboBoxModel1.addElement("4");
        defaultComboBoxModel1.addElement("5");
        defaultComboBoxModel1.addElement("6");
        defaultComboBoxModel1.addElement("7");
        defaultComboBoxModel1.addElement("8");
        defaultComboBoxModel1.addElement("9");
        defaultComboBoxModel1.addElement("10");
        defaultComboBoxModel1.addElement("11");
        defaultComboBoxModel1.addElement("12");
        defaultComboBoxModel1.addElement("13");
        defaultComboBoxModel1.addElement("14");
        defaultComboBoxModel1.addElement("15");
        defaultComboBoxModel1.addElement("16");
        defaultComboBoxModel1.addElement("17");
        defaultComboBoxModel1.addElement("18");
        defaultComboBoxModel1.addElement("19");
        defaultComboBoxModel1.addElement("20");
        defaultComboBoxModel1.addElement("21");
        defaultComboBoxModel1.addElement("22");
        defaultComboBoxModel1.addElement("23");
        defaultComboBoxModel1.addElement("24");
        cmbComPortNum.setModel(defaultComboBoxModel1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(cmbComPortNum, gbc);
        btnConnect = new JButton();
        btnConnect.setText("Connect to Main");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(btnConnect, gbc);
        btnExperiment = new JButton();
        btnExperiment.setText("Experiment");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(btnExperiment, gbc);
        btnTracking = new JButton();
        btnTracking.setText("Tracking");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel2.add(btnTracking, gbc);
        txTargetsSet = new JTextArea();
        txTargetsSet.setMinimumSize(new Dimension(0, 0));
        txTargetsSet.setRows(10);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(txTargetsSet, gbc);
        cardHolder = new JPanel();
        cardHolder.setLayout(new CardLayout(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(cardHolder, gbc);
        expCardPanel = new JPanel();
        expCardPanel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        expCardPanel.setName("expCardPanel");
        cardHolder.add(expCardPanel, "expCardPanel");
        expCard = new JPanel();
        expCard.setLayout(new GridBagLayout());
        expCard.setName("expCard");
        expCard.setPreferredSize(new Dimension(400, 77));
        expCardPanel.add(expCard, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Experiment: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        expCard.add(label4, gbc);
        btnStartStopExperiment = new JButton();
        btnStartStopExperiment.setText("Start Experiment");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        expCard.add(btnStartStopExperiment, gbc);
        btnRecordExperiment = new JButton();
        btnRecordExperiment.setText("Record to Excel");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        expCard.add(btnRecordExperiment, gbc);
        btnRemoveRecords = new JButton();
        btnRemoveRecords.setText("Remove Records");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        expCard.add(btnRemoveRecords, gbc);
        txExperimentName = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        expCard.add(txExperimentName, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Experiment Name: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        expCard.add(label5, gbc);
        lblExperimentOnOff = new JLabel();
        lblExperimentOnOff.setText("Off");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        expCard.add(lblExperimentOnOff, gbc);
        btnShowResults = new JButton();
        btnShowResults.setText("ShowResults");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        expCard.add(btnShowResults, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Collected Data:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        expCard.add(label6, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Program Log: ");
        expCardPanel.add(label7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("ComPortLog");
        expCardPanel.add(label8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(0, 40));
        scrollPane1.setPreferredSize(new Dimension(1, 51));
        expCardPanel.add(scrollPane1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txProgramLog = new JTextArea();
        txProgramLog.setColumns(0);
        txProgramLog.setMinimumSize(new Dimension(1, 1));
        txProgramLog.setPreferredSize(new Dimension(1, 51));
        txProgramLog.setRows(3);
        scrollPane1.setViewportView(txProgramLog);
        scrollComPortLog = new JScrollPane();
        scrollComPortLog.setAutoscrolls(true);
        scrollComPortLog.setMinimumSize(new Dimension(14, 150));
        expCardPanel.add(scrollComPortLog, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txComPortLog = new JTextArea();
        txComPortLog.setMinimumSize(new Dimension(1, 0));
        txComPortLog.setRows(10);
        scrollComPortLog.setViewportView(txComPortLog);
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(14, 150));
        expCardPanel.add(scrollPane2, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        txExperimentLog = new JTextArea();
        txExperimentLog.setText("");
        scrollPane2.setViewportView(txExperimentLog);
        trackingCardPanel = new JPanel();
        trackingCardPanel.setLayout(new GridBagLayout());
        trackingCardPanel.setMinimumSize(new Dimension(400, 0));
        trackingCardPanel.setName("trackingCardPanel");
        cardHolder.add(trackingCardPanel, "trackingCardPanel");
        sp1 = new JSplitPane();
        sp1.setDividerLocation(409);
        sp1.setLastDividerLocation(409);
        sp1.setOrientation(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        trackingCardPanel.add(sp1, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        sp1.setLeftComponent(panel3);
        txPositionLog = new JTextArea();
        txPositionLog.setLineWrap(true);
        txPositionLog.setMaximumSize(new Dimension(170, 2147483647));
        txPositionLog.setMinimumSize(new Dimension(170, 0));
        txPositionLog.setOpaque(true);
        txPositionLog.setPreferredSize(new Dimension(170, 170));
        txPositionLog.setRows(10);
        txPositionLog.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(txPositionLog, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Positioning Log:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel3.add(label9, gbc);
        sp2 = new JSplitPane();
        sp2.setDividerLocation(703);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel3.add(sp2, gbc);
        posHistoryPanel = new JPanel();
        posHistoryPanel.setLayout(new BorderLayout(0, 0));
        posHistoryPanel.setEnabled(false);
        posHistoryPanel.setMinimumSize(new Dimension(30, 30));
        posHistoryPanel.setName("trackingCard");
        posHistoryPanel.setOpaque(true);
        posHistoryPanel.setPreferredSize(new Dimension(10, 10));
        sp2.setRightComponent(posHistoryPanel);
        mapPlotPanel = new JPanel();
        mapPlotPanel.setLayout(new BorderLayout(0, 0));
        mapPlotPanel.setEnabled(false);
        mapPlotPanel.setMinimumSize(new Dimension(30, 30));
        mapPlotPanel.setName("trackingCard");
        mapPlotPanel.setOpaque(true);
        mapPlotPanel.setPreferredSize(new Dimension(10, 10));
        sp2.setLeftComponent(mapPlotPanel);
        radioPlotPanel = new JPanel();
        radioPlotPanel.setLayout(new BorderLayout(0, 0));
        radioPlotPanel.setName("plotCard");
        sp1.setRightComponent(radioPlotPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }



    public String getExpName() {
        return txExperimentName.getText();
    }

    public void setConnectionStatus(boolean connected) {
        if (connected) {
            btnConnect.setText("Disconnect from Main");
        } else {
            btnConnect.setText("Connect to Main");
        }
    }

    public void setSlaveNum(int slaveNum) {
        lblSlavesNum.setText(Integer.toString(slaveNum));
    }

    public void setTargetsNum(int targetsNum) {
        lblTargetsNum.setText(Integer.toString(targetsNum));
    }

    public void setExperimentStatus(boolean isOn) {
        viewModel.experimentInOn.set(isOn);
        if (isOn) {
            btnStartStopExperiment.setText("Stop Experiment");
            lblExperimentOnOff.setText("On");
        } else {
            btnStartStopExperiment.setText("Start Experiment");
            lblExperimentOnOff.setText("Off");
        }
    }

    public void printToExperimentsResultsLabel(String s) {
        txExperimentLog.setText(s);
    }

    public void printToProgramLog(String s) {
        txProgramLog.setText(s);
//            txProgramLog.append(s);
//            txProgramLog.append("\n");
    }


    public void println(String s) {
        txComPortLog.append(s);
        txComPortLog.append("\n");
        JScrollBar vBar = scrollComPortLog.getVerticalScrollBar();
        vBar.setValue(vBar.getMaximum());
    }
}
