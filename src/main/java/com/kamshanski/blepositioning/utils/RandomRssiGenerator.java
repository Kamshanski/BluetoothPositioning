package com.kamshanski.blepositioning.utils;

import java.util.Random;

public class RandomRssiGenerator {
    public static final String SIN = "sin";
    public static final String COS = "cos";
    public static final String TRI = "tri";
    public static final String SQR = "sqr";

    Random random;
    String form;
    double max, min, fr, a, fi, bias;
    double triA, triFr;
    double t = 0.0;

    public RandomRssiGenerator(String form, double max, double min, double fr, double a, double bias, double fi) {
        random = new Random();
        this.form = form;
        this.max = max;
        this.min = min;
        this.fr = fr;
        this.a = a;
        this.bias = bias;
        this.fi = fi;

        this.triA = 2 * a / Math.PI;
        this.triFr = 2 * Math.PI / fr;
    }

    public double[] getArray(int size) {
        double[] ar = new double[size];
        fillArray(ar);
        return ar;
    }

    public double[] fillArray(double[] data) {
        for (int i = 0; i < data.length; i++) {
            data[i] = next();
        }
        return data;
    }

    public double next() {
        t += 0.07;
        return ((random.nextDouble() * (max - min)) + min) + getLeadingLine();
    }

    private double getLeadingLine() {
        switch (form) {
            case SIN: return Math.sin(fr*t + fi) * a + bias;
            case SQR: return Math.signum(Math.sin(fr*t + fi)) * a + bias;
            case COS: return Math.cos(fr*t + fi) * a + bias;
            case TRI: return triA * Math.asin(Math.sin(triFr * t));
            default: return Math.sin(t);
        }
    }
}
