package com.kamshanski.blepositioning.model.storage;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.methods.filters.FilterBunch;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.BiConsumer;

//Just for one mac address
public class NiceStorage {
    public final String name;
    public LinkedHashMap<Beacon, LinkedList<double[]>> storage;
    public HashMap<Beacon, double[]> lastPack;
    public int maxLength = 0;
    public HashMap<Integer, FilterBunch> filters;
    public final FilterBunch filterSample;

    public NiceStorage(String name, FilterBunch filterSample) {
        this.name = name;
        this.storage = new LinkedHashMap<>();
        this.lastPack = new HashMap<>(4);
        this.filters = new HashMap<>(4);
        this.filterSample = filterSample;
    }

    public void add(int beaconId, double[] rssis) {
        compute((len, store) -> {
            Beacon beacon = Beacon.byId(beaconId);
            FilterBunch filters = this.filters.computeIfAbsent(beaconId, id -> (FilterBunch) filterSample.makeTheSame());
            double[] filteredRssi = filters.filter(rssis);
            LinkedList<double[]> ll = store.computeIfAbsent(beacon, id -> new LinkedList<>());
            ll.addFirst(filteredRssi);
            lastPack.put(beacon, filteredRssi);
        });
    }

    public void clearLast() {
        lastPack.keySet().forEach(key -> lastPack.put(key, null));
    }

    public HashMap<Beacon, double[]> getLastPacks() {
        return lastPack;
    }

    public synchronized void compute(BiConsumer<Integer, LinkedHashMap<Beacon, LinkedList<double[]>>> consumer) {
        consumer.accept(maxLength, storage);
    }
}
