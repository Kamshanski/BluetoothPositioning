package com.kamshanski.blepositioning.model.methods.filters;

import java.util.Arrays;

public class FilterBunch implements NiceFilter {
    NiceFilter[] filters;

    public FilterBunch(NiceFilter[] filters) {
        this.filters = filters;
    }

    @Override
    public double[] filter(double[] in) {
        double[] temp = Arrays.copyOf(in, in.length);
        for (NiceFilter filter : filters) {
            temp = filter.filter(temp);
        }
        return temp;
    }

    @Override
    public NiceFilter makeTheSame() {
        NiceFilter[] newBunch = new NiceFilter[filters.length];
        for (int i = 0; i < filters.length; i++) {
            newBunch[i] = filters[i].makeTheSame();
        }
        return new FilterBunch(newBunch);
    }

    public int size() {
        return filters.length;
    }
}
