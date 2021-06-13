package com.kamshanski.blepositioning.model.methods.filters;

public interface Filter<T> {
    void put(int rssi);
    T calculate();

}
