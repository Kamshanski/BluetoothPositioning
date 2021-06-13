package com.kamshanski.blepositioning.model.methods.mean;

public class Mean {
    double mean = 0.0d;

    public Mean() {
    }

    public double calculate(double[] ar) {
        return mean = mean(ar);
    }

    public double getLastMean() {
        return mean;
    }

    public static double mean(double[] ar) {
        double sum = .0d;
        int count = ar.length;

        if (count < 1) {
            return .0d;
        }

        for (double v : ar) {
            sum += v;
        }

        return sum / count;
    }

    public Mean makeTheSame() {
        return new Mean();
    }
}
