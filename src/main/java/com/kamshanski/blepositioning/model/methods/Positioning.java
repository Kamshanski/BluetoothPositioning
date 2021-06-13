package com.kamshanski.blepositioning.model.methods;

import com.kamshanski.blepositioning.model.methods.filters.NiceFilter;
import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.methods.distancefunctions.DistanceFunction;
import com.kamshanski.blepositioning.model.methods.filters.FilterBunch;
import com.kamshanski.blepositioning.model.methods.mean.MeanVariance;
import com.kamshanski.blepositioning.model.methods.solvers.Estimator;
import com.kamshanski.blepositioning.model.storage.Measurement;
import com.kamshanski.blepositioning.model.storage.PositioningStorage;
import com.kamshanski.blepositioning.utils.U;
import com.kamshanski.utils.test.Timer;

import java.util.Arrays;
import java.util.HashMap;

public class Positioning {
    final NiceFilter rssiFiltersBunchSample;
    HashMap<Beacon, NiceFilter> rssiFilters = new HashMap<>(4);
    HashMap<Beacon, MeanVariance> meanFunctions = new HashMap<>(4);
    final HashMap<Beacon, DistanceFunction> distanceFunctions;
    final Estimator coordinatesEstimator;
    final FilterBunch xFilter, yFilter;
    final boolean meanFirst;
    public final PositioningStorage storage;
    private StringBuilder log = new StringBuilder("");;

    public Positioning(FilterBunch rssiFiltersBunchSample,
                       HashMap<Beacon, DistanceFunction> distanceFunctions,
                       Estimator coordinatesEstimator,
                       FilterBunch coordinatesFilter,
                       boolean meanFirst,
                       PositioningStorage storage) {
        this.rssiFiltersBunchSample = rssiFiltersBunchSample;
        this.distanceFunctions = distanceFunctions;
        this.coordinatesEstimator = coordinatesEstimator;
        this.xFilter = coordinatesFilter != null ? (FilterBunch) coordinatesFilter.makeTheSame() : null;
        this.yFilter = coordinatesFilter != null ? (FilterBunch) coordinatesFilter.makeTheSame() : null;
        this.meanFirst = meanFirst;
        this.storage = storage;
    }

    @SuppressWarnings("all")
    public Dimensions of(HashMap<Beacon, double[]> input) {
        log.setLength(0);   // Log
        log.append("{\n\t[");   // Log
        int size = input.size();
        if (size < 3) {
            U.nout("Not enough sources to retrieve 2D coordinates");
            return new Dimensions(99999, 99999, 99999);
        }

        HashMap<Beacon, Double> distances = new HashMap<>(size);

        input.forEach((beacon, rawRssi) -> {
            log.append("\n\t\t").append(beacon.id).append(": ");   // Log
            Timer.start("1");   // Log

            DistanceFunction distFunc = null;
            if (distanceFunctions != null) {
                distFunc = distanceFunctions.get(beacon);
                if (distFunc == null) {
                    U.nout("Beacon " + beacon + " was not declared in distance functions");
                    return;
                }
            }

            double[] rssis = Arrays.copyOf(rawRssi, rawRssi.length);

            double[] filteredValues = rssiFilters.computeIfAbsent(beacon, b -> rssiFiltersBunchSample.makeTheSame()).filter(rssis);

            double singleValue;
            if (meanFirst) {
                double mean = meanFunctions.computeIfAbsent(beacon, b -> new MeanVariance()).calculate(filteredValues);

                singleValue = mean;
                if (distFunc != null) {
                    double rho = distFunc.of(mean);
                    singleValue = rho;
                }
            } else {
                if (distFunc != null) {
                    for (int i = 0; i < filteredValues.length; i++) {
                        filteredValues[i] = distFunc.of(filteredValues[i]);
                    }
                }
                double mean = meanFunctions.computeIfAbsent(beacon, b -> new MeanVariance()).calculate(filteredValues);
                singleValue = mean;
            }

            distances.put(beacon, singleValue);
            log.append(Timer.finish("1", false)/1000L).append(" mcs");   // Log
        });

        log.append(" ],\n\t").append(" estimation: ");   // Log
        Timer.start("1");   // Log

        Dimensions coordinates = coordinatesEstimator.estimate(distances);

        if (xFilter != null && xFilter.size() > 0 && coordinates != null) {
            double[] filteredX = xFilter.filter(new double[]{coordinates.x});
            double[] filteredY = yFilter.filter(new double[]{coordinates.y});

            coordinates.set(filteredX[0], filteredY[0]);
        }

        storage.put(coordinates);

        log.append(Timer.finish("1", false)/1000L).append(" mcs\n}");   // Log
        return coordinates;
    }

    public String getLog() {
        return log.toString();   // Log
    }
}
