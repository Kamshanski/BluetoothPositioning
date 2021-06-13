package com.kamshanski.blepositioning.model.storage;

import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.core.MacAddress;

import java.util.LinkedList;
import java.util.function.Consumer;

public class PositioningStorage {
    private final MacAddress target;
    private LinkedList<Dimensions> storage = new LinkedList<>();
    private Dimensions lastPack = null;

    public PositioningStorage(MacAddress target) {
        this.target = target;
    }

    public synchronized void put(Dimensions measurement) {
        storage.addFirst(measurement);
        lastPack = measurement;
    }

    public synchronized Dimensions getLast() {
        return lastPack;
    }

    public synchronized Dimensions get(int i) {
        return storage.get(i);
    }

    public synchronized int size() {
        return storage.size();
    }

    public synchronized void forEach(Consumer<Dimensions> consumer) {
        storage.forEach(consumer);
    }
}
