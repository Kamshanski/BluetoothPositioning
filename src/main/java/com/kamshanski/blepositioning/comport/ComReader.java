package com.kamshanski.blepositioning.comport;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.kamshanski.blepositioning.utils.U;

import static com.fazecast.jSerialComm.SerialPort.*;
import static com.kamshanski.blepositioning.comport.ComPortConstants.*;

public class ComReader implements SerialPortDataListener {
    public static final int listeningEvents = LISTENING_EVENT_DATA_AVAILABLE | LISTENING_EVENT_DATA_RECEIVED | LISTENING_EVENT_DATA_WRITTEN;
    public static final int DEFAULT_CAPACITY = 1024;

    private int portNum = -1;
    private ComPortListener comPortListener;
    private SerialPort serialPort;
    private StringBuilder builder;
    private StringBuilder sizeBuf, capacityBuf, macsBuf;
    private long timeStart = -1024, timeEnd = -1024;
    private boolean isOnNewLine = false;
    private boolean isOpened = false;

    public ComReader(ComPortListener comPortListener) {
        this.comPortListener = comPortListener;
        builder = new StringBuilder(DEFAULT_CAPACITY);
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        try {
            if (event.getEventType() == LISTENING_EVENT_DATA_AVAILABLE) {
                U.nout("DATA_AVAILABLE");
            }
            else if (event.getEventType() == LISTENING_EVENT_DATA_RECEIVED) {
                long curTime = System.currentTimeMillis();
                byte[] newData = event.getReceivedData();
                for (int i = 0; i < newData.length; ++i) {
                    char ch = (char) newData[i];
                    if (ch != '\n' && ch != '\r') {
                        builder.append(ch);
                    }
                    else if (builder.length() > 0) {

                        comPortListener.onTerminal(builder.toString());

                        if (builder.length() >= PAYLOAD_LENGTH && about(PAYLOAD)) {
                            comPortListener.onPayload(timeStart, timeEnd, curTime, builder.toString());
                        } else if (about(TIME_START)) {
                            try {
                                timeStart = Long.parseLong(builder.substring(TIME_START.length));
                            } catch (NumberFormatException e) {
                                U.nout("ERROR!!!!  " + e.getMessage());
                            }
                        } else if (about(TIME_END)) {
                            try {
                                timeEnd = Long.parseLong(builder.substring(TIME_END.length));
                            } catch (NumberFormatException e) {
                                U.nout("ERROR!!!!  " + e.getMessage());
                            }
                        } else if (about(RELOADING)) {
                            comPortListener.onRestarting("Restarting");
                        } else if (about(ERROR)) {
                            comPortListener.onError("Error");
                        } else if (about(READY)) {
                            comPortListener.onReady("Device is ready");
                        } else if (about(TARGET_SET_START)) {
                            // Do nothing :/
                            macsBuf = new StringBuilder(24);
                        } else if (about(SIZE)) {
                            builder.delete(0, SIZE.length);
                            sizeBuf = new StringBuilder(builder);
                        } else if (about(CAPACITY)) {
                            builder.delete(0, CAPACITY.length);
                            capacityBuf = new StringBuilder(builder);
                        } else if (about(DEVICE_MAC)) {
                            builder.delete(0, DEVICE_MAC.length);
                            macsBuf.append(builder);
                        } else if (about(TARGET_SET_END)) {
                            // Submit all to app's parser
                            comPortListener.onTargetsSet(sizeBuf.toString(), capacityBuf.toString(), macsBuf.toString());
                        } else if (about(SLAVES)) {
                            U.nout(builder.toString());
                            builder.delete(0, SLAVES.length);

                            comPortListener.onSlavesNumber(builder.toString());
                        }

                        // U.nout(builder.toString());
                        builder.setLength(0);
                    }
                }
            }
            else if (event.getEventType() == LISTENING_EVENT_DATA_WRITTEN) {
                U.nout("DATA_WRITTEN");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            U.nout("Critical error");
        }

    }

    public boolean about(char[] meaning) {
        for (int i = 0; i < meaning.length; i++) {
            if (builder.charAt(i) != meaning[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getListeningEvents() {
        return listeningEvents;
    }

    SerialPort[] getPortsList() {
        return SerialPort.getCommPorts();
    }

    public boolean open(int portNum) {
        if (!isOpened) {
            if (this.portNum != portNum) {
                this.portNum = portNum;
                this.serialPort = SerialPort.getCommPort("COM" + portNum);
            }
            serialPort.setComPortParameters(115200, 8, ONE_STOP_BIT, NO_PARITY);
            serialPort.addDataListener(this);
            if (serialPort.openPort()) {
                isOpened = true;
                U.nout("Connected Successfully");
                return true;
            } else {
                U.nout("Connection failed");
            }
        }
        return false;
    }

    public boolean close() {
        if (isOpened) {
            isOpened = false;
            return serialPort.closePort();
        }
        return false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public int getPortNum() {
        return portNum;
    }

    //
//    @Override
//    public void serialEvent(SerialPortEvent event) {
//        if (event.isRXCHAR() && event.getEventValue() > 0) {
//            try {
//                String payload = serialPort.readString(event.getEventValue());
//            } catch (SerialPortException ex) {
//                U.out(ex.getMessage());
//            }
//        }
//    }
}
