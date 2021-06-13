package com.kamshanski.blepositioning.model.methods.mean;

public final class MeanVariance extends Mean {
    double variance = .0d;

    public MeanVariance() {
    }

    @Override
    public double calculate(double[] ar) {
        super.calculate(ar);
        variance = variance(ar, mean);
        return mean;
    }

    public static double variance(double[] ar) {
        return variance(ar, mean(ar));
    }

    public static double variance(double[] ar, double mean) {
        double sum = 0.0d;
        for (double v : ar) {
            sum += Math.pow(v - mean, 2);
        }
        return sum / (ar.length - 1);
    }
}
