package com.kamshanski.blepositioning.model.core;

public class Dimensions {
    public double x, y, z = 0.0;
    public double fv;       // optional

    public Dimensions(double x, double y, double z) {
        set(x, y, z);
    }

    public void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    public void set(double x, double y) {
        set(x, y, z);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", fv=" + fv +
                '}';
    }
}
