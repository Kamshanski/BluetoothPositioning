package com.kamshanski.blepositioning.model.methods.distancefunctions;

import com.kamshanski.blepositioning.model.core.Beacon;

public interface DistanceFunction {
    double of(double rssi);
     DistanceFunction makeTheSame(Beacon beacon);
}
