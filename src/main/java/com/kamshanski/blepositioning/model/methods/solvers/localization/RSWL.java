package com.kamshanski.blepositioning.model.methods.solvers.localization;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.methods.solvers.Estimator;

import java.util.HashMap;

public class RSWL implements Estimator {
    final double lambda, base;
    final double RSSI_max;

    public RSWL(double lambda, double RSSI_max) {
        this.lambda = lambda;       // as a is used only with a minus a
        this.base = 1.0-lambda;     //
        this.RSSI_max = RSSI_max;   // Assume thar RSSI_max equals for all beacons
    }

    @Override
    public Dimensions estimate(HashMap<Beacon, Double> dataset) {
//        double exp;
//        double numeratorX = 0.0, numeratorY = 0.0, denominator = 0.0;
//
//        for (double rssi : dataset.getRssis()) {
//            exp = Math.pow(base, RSSI_max - rssi);
//
//            numeratorX += exp * dataset.sx();
//            numeratorY += exp * dataset.sy();
//            denominator += exp;
//        }
//
//        return new Dimensions(numeratorX/denominator, numeratorY/denominator, 0);
        return null;
    }
}
