package com.kamshanski.blepositioning.model.storage;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;

import java.util.HashMap;
import java.util.LinkedList;

public class Measurement {
    private HashMap<Beacon, LinkedList<Double>> tempArray = new HashMap<>();
    private HashMap<Beacon, double[]> rssis = new HashMap<>();
    private Dimensions dimensions;

    public void put(Beacon beacon, double rssi) {
        this.tempArray.computeIfAbsent(beacon, b -> new LinkedList<>()).addFirst(rssi);
    }

    public void complete() {
        tempArray.forEach((beacon, list) -> {
            double[] ar = new double[list.size()];
            int i = 0;
            for (Double d : list) {
                ar[i++] = d;
            }
            rssis.put(beacon, ar);
            tempArray = null;
        });
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public HashMap<Beacon, double[]> get() {
        return rssis;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
