package com.kamshanski.blepositioning.model.methods.solvers.classificators;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.methods.solvers.Estimator;

import java.util.HashMap;

// precision is 5 digit after dot 0.12345
public abstract class Classificator implements Estimator {
    double[] input = new double[4];

    @Override
    public Dimensions estimate(HashMap<Beacon, Double> dataset) {
        if (dataset.size() != 4) {
            return null;
            //throw new IllegalArgumentException("Only 4 beacons supported!");
        }

        input[0] = dataset.get(Beacon.MAIN)     /-90;
        input[1] = dataset.get(Beacon.SLAVE_1)  /-90;
        input[2] = dataset.get(Beacon.SLAVE_2)  /-90;
        input[3] = dataset.get(Beacon.SLAVE_3)  /-90;

        double x = computeX(input)*70.0d;
        double y = computeY(input)*70.0d;

        return new Dimensions(x, y, 0);
    }

    @Override
    public String toString() {
        return getClass().getName();
    }

    abstract double computeX(double[] input);
    abstract double computeY(double[] input);

}
