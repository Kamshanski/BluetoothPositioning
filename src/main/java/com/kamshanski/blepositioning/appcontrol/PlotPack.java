package com.kamshanski.blepositioning.appcontrol;

import java.util.HashMap;

public class PlotPack {
    final HashMap<String, double[]> pack;
    int max;

    public PlotPack() {
        pack = new HashMap<>(4);
    }

    public void add(String key, double[] ys) {
        pack.put(key, ys);
        max = Math.max(ys.length, max);
    }

    public HashMap<String, double[]> getPack() {
        return pack;
    }

    public int getMax() {
        return max;
    }
}
