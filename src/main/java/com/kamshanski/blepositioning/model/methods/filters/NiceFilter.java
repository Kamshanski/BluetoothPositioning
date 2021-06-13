package com.kamshanski.blepositioning.model.methods.filters;

public interface NiceFilter {
    double[] filter(double[] in);
    NiceFilter makeTheSame();
}
