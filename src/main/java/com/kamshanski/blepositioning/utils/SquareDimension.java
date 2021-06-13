package com.kamshanski.blepositioning.utils;

public class SquareDimension {
    public final int xMax, xMin, yMax, yMin;

    public SquareDimension(int xMax, int xMin, int yMax, int yMin) {
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
    }

    public double fitX(double in) {
        if (in > xMax)
            return xMax;
        else if (in < xMin)
            return xMin;
        else
            return in;
    }

    public double fitY(double in) {
        if (in > yMax)
            return yMax;
        else if (in < yMin)
            return yMin;
        else
            return in;
    }
}
