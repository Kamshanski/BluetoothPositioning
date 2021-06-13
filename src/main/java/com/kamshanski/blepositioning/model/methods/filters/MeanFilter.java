package com.kamshanski.blepositioning.model.methods.filters;

import java.util.Arrays;

public class MeanFilter implements NiceFilter {
    double mean, variance;
    final boolean sameSize;

    public MeanFilter(boolean sameSize) {
        this.sameSize = sameSize;
    }

    @Override
    public double[] filter(double[] in) {
        double rssiSum = 0;
        int size =  in.length;
        for (double v : in) {
            rssiSum += v;
        }
        mean = rssiSum / size;

        double variance = 0.0d;
        for (double v : in) {
            variance += Math.pow(v - mean, 2);
        }
        variance /= size;

        double[] result;
        if (sameSize) {
            result = new double[size];
            Arrays.fill(result, mean);
        } else {
            result = new double[] {mean};
        }

        return result;
    }

    @Override
    public NiceFilter makeTheSame() {
        return new MeanFilter(sameSize);
    }

    public double getMean() {
        return mean;
    }

    public double getVariance() {
        return variance;
    }
}
