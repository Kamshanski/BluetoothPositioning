package com.kamshanski.blepositioning.model.methods.distancefunctions;


import com.kamshanski.blepositioning.model.core.Beacon;

/**
 * pho = exp((RSSId-RSSI)/a*ln(d)) => pho = exp(b + RSSI * a)
 * a = -ln(d)/a
 * b = RSSId*ln(d)/a
 */
public class PivotExponent implements DistanceFunction {
    final double pivotDistance, pivotRssi, a, b, a_orig;

    public PivotExponent(double pivotDistance, double pivotRssi, double a) {
        this.pivotDistance = pivotDistance;
        this.pivotRssi = pivotRssi;
        this.a_orig = a;
        double ln_d = Math.log(pivotDistance);
        // apply simplification
        this.a = - ln_d / a;
        this.b = pivotRssi * ln_d / a;

    }

    @Override
    public double of(double rssi) {
        return Math.exp(a * rssi + b);
    }

    @Override
    public DistanceFunction makeTheSame(Beacon beacon) {
        return new PivotExponent(pivotDistance, pivotRssi, a_orig);
    }
}
