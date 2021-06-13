package com.kamshanski.blepositioning.model.methods.solvers.particleswarm;

public class ParticleSwamBuilder {
    private double xMax = 100;
    private double xMin = -100;
    private double yMax = 100;
    private double yMin = -100;
    private int maxIterations = 70;
    private int xPointsNum = 5;
    private int yPointsNum = 5;
    private LeastSquaresFunction f;

    public ParticleSwamBuilder(LeastSquaresFunction f) {
        this.f = f;
    }

    public ParticleSwamBuilder setxMax(double xMax) {
        this.xMax = xMax;
        return this;
    }

    public ParticleSwamBuilder setxMin(double xMin) {
        this.xMin = xMin;
        return this;
    }

    public ParticleSwamBuilder setyMax(double yMax) {
        this.yMax = yMax;
        return this;
    }

    public ParticleSwamBuilder setyMin(double yMin) {
        this.yMin = yMin;
        return this;
    }

    public ParticleSwamBuilder setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public ParticleSwamBuilder setxPointsNum(int xPointsNum) {
        this.xPointsNum = xPointsNum;
        return this;
    }

    public ParticleSwamBuilder setyPointsNum(int yPointsNum) {
        this.yPointsNum = yPointsNum;
        return this;
    }

    public ParticleSwam build() {
        return new ParticleSwam(xMax, xMin, yMax, yMin, maxIterations, xPointsNum, yPointsNum, f);
    }
}