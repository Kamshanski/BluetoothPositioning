package com.kamshanski.blepositioning.model.methods.solvers.particleswarm;

import java.util.Random;
import java.util.function.Consumer;

public class ParticleSwam {

    // static parameters
    double xMax, xMin, yMax, yMin;
    int maxIterations;
    int xPointsNum, yPointsNum;
    LeastSquaresFunction f;

    // dynamic evaluation parameters
    double globalBestX, globalBestY, globalBestFunctionValue;
    double xBestTemp, yBestTemp, fBestTemp;
    int globalBestId, idBestTemp;
    int step = 0;

    Point[] points;
    Random uniformRandom = new Random();

    public ParticleSwam(double xMax, double xMin, double yMax, double yMin, int maxIterations, int xPointsNum, int yPointsNum, LeastSquaresFunction f) {
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        this.maxIterations = maxIterations;
        this.xPointsNum = xPointsNum;
        this.yPointsNum = yPointsNum;
        clear(f);
    }

    public void clear(LeastSquaresFunction f) {
        this.f = f;
        this.globalBestX = 0;
        this.globalBestY = 0;
        this.globalBestFunctionValue = 0;
        this.xBestTemp = 0;
        this.yBestTemp = 0;
        this.fBestTemp = 0;
        this.globalBestId = 0;
        this.idBestTemp = 0;
        this.step = 0;
        fitClearPoints();
    }

    public void fitClearPoints() {
        // create points if needed
        if (points == null) {
            points = new Point[xPointsNum * yPointsNum];
            for (int i = 0; i < points.length; i++) {
                points[i] = new Point(0, 0, uniformRandom);
            }
        } else if (points.length < xPointsNum * yPointsNum) {
            Point[] prev = points;
            points = new Point[xPointsNum * yPointsNum];
            for (int i = 0; i < points.length; i++) {
                points[i] = i < prev.length ? prev[i] : new Point(0, 0, uniformRandom);
            }
        }

        // put points to initial positions
        double dx = (xMax - xMin) / (xPointsNum + 1);
        double dy = (yMax - yMin) / (yPointsNum + 1);

        for (int i = 0; i < xPointsNum; ++i) {
            double x = xMin + dx*(double)(i+1);
            int xi = i*yPointsNum;
            for (int j = 0; j < yPointsNum; ++j) {
                double y = yMin + dy*(double)(j+1);
                Point p = points[xi+j];
                if (p == null) {
                    throw new NullPointerException("Stranno(((");
                }
                p.setPosition(x, y);
                double J = f.of(p.x, p.y);
                p.fBestLocal = J;
                if (J < globalBestFunctionValue || xi+j == 0) {
                    globalBestFunctionValue = J;
                    globalBestX = x;
                    globalBestY = y;
                    globalBestId = xi+j;
                }
                points[xi+j] = p;
            }
        }
        xBestTemp = globalBestX;                        // Best point staring values
        yBestTemp = globalBestY;                        //
        fBestTemp = globalBestFunctionValue;            //
        idBestTemp = globalBestId;
    }

    public boolean hasNext() {
        return step <= maxIterations;
    }

    public Answer next() {
        if (fBestTemp < globalBestFunctionValue) {
            globalBestFunctionValue = fBestTemp;
            globalBestX = xBestTemp;
            globalBestY = yBestTemp;
            globalBestId = idBestTemp;
        }

        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];
            p.updatePosition(globalBestX, globalBestY);
            double funcVal = f.of(p.x, p.y);
            p.setFunctionValue(funcVal);
            if (funcVal < fBestTemp) {
                fBestTemp = funcVal;
                xBestTemp = p.x;
                yBestTemp = p.y;
                idBestTemp = i;
            }
        }

        ++step;

        return new Answer(globalBestX, globalBestY, globalBestFunctionValue, globalBestId);
    }

    public Answer calculateFull(Consumer<Answer> consumer) {
        Answer ans = null;
        while (hasNext()) {
            ans = next();
            if (consumer != null) {
                consumer.accept(ans);
            }
        }
        return ans;
    }

    public static class Answer {
        double x, y, f;
        public int step, globalBestParticleId;

        public Answer(double x, double y, double f, int id) {
            this.x = x;
            this.y = y;
            this.f = f;
            this.globalBestParticleId = id;
        }

        public double getX() {
            return x;
        }
        public double getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Answer{" +
                    "x=" + x +
                    ", y=" + y +
                    ", f=" + f +
                    ", step=" + step +
                    ", id=" + globalBestParticleId +
                    '}';
        }
    }
}
