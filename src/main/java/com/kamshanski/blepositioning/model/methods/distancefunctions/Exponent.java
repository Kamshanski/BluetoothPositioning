package com.kamshanski.blepositioning.model.methods.distancefunctions;


import com.kamshanski.blepositioning.model.core.Beacon;

/**
 * Rssi = k * ln(p) + b
 * p = exp((Rssi - b) / k),
 * p = exp(alpha * rssi + beta)
 * where:
 *  p is a distance
 *  alpha = 1/k
 *  beta = -b/k
 */
public class Exponent implements DistanceFunction {
    public final double k, b, alpha, beta;

    public Exponent(double k, double b) {
        this.k = k;
        this.b = b;
        this.alpha = 1/k;
        this.beta = -b/k;
    }

    @Override
    public double of(double rssi) {
        return Math.exp(alpha * rssi + beta);
    }

    @Override
    public DistanceFunction makeTheSame(Beacon beacon) {
        return new Exponent(k, b);
    }
}
