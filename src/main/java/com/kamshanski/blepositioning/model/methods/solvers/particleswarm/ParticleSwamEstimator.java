package com.kamshanski.blepositioning.model.methods.solvers.particleswarm;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.methods.solvers.Estimator;

import java.util.HashMap;

public class ParticleSwamEstimator implements Estimator {
    ParticleSwam swam;

    public ParticleSwamEstimator(double xMax, double xMin, double yMax, double yMin, int maxIterations, int xPointsNum, int yPointsNum) {
        swam = new ParticleSwam(xMax, xMin, yMax, yMin, maxIterations, xPointsNum, yPointsNum, LeastSquaresFunction.Builder.getZero());
    }

    @Override
    public Dimensions estimate(HashMap<Beacon, Double> dataset) {
        LeastSquaresFunction.Builder builder = new LeastSquaresFunction.Builder();
        dataset.forEach((beacon, distance) -> {
            builder.addPart(beacon.x, beacon.y, distance);
        });
        swam.clear(builder.build());

        ParticleSwam.Answer ans = swam.calculateFull(null);
        return new Dimensions(ans.x, ans.y, ans.f);
    }
}
