package com.kamshanski.blepositioning.model.methods.solvers.particleswarm;

import java.util.Random;

public class Point {
    double x, y;
    double xVel = 0.0, yVel = 0.0;
    double xBestLocal =.0, yBestLocal =.0, fBestLocal = .0;
    double functionValue=.0;
    double Cin;                 // Main coefficients, that defines algorithm behavior
    double Ccog;                //
    double Csoc;                //

    public Point(double x, double y, Random U) {
        this.x = x;
        this.y = y;
        xBestLocal = x;
        yBestLocal = y;
        xVel = U.nextDouble();
        yVel = U.nextDouble();
        Cin = U.nextDouble();
        Ccog = U.nextDouble();
        Csoc = U.nextDouble();
    }

    public double[] updatePosition(double xGlobalBest, double yGlobalBest) {
        xVel = Cin*xVel + Ccog*(xBestLocal - x) + Csoc*(xGlobalBest - x);   // Main formula of the algorithm update
        yVel = Cin*yVel + Ccog*(yBestLocal - y) + Csoc*(yGlobalBest - y);

        x = x + xVel;
        y = y + yVel;

        return new double[] {x, y};
    }

    public void setFunctionValue(double fv) {
        this.functionValue = fv;
        if (fv < fBestLocal) {
            fBestLocal = fv;
            xBestLocal = x;
            yBestLocal = y;
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;

        xVel = 0;
        yVel = 0;

        xBestLocal = x;
        yBestLocal = y;
        fBestLocal = 0;
        functionValue = 0;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getFv() {
        return functionValue;
    }
}
