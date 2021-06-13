package com.kamshanski.blepositioning.model.methods.solvers;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;

import java.util.HashMap;

public interface Estimator {
    Dimensions estimate(HashMap<Beacon, Double> dataset);
}
