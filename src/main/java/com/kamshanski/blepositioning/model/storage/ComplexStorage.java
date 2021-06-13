package com.kamshanski.blepositioning.model.storage;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.MacAddress;
import com.kamshanski.blepositioning.model.methods.filters.FilterBunch;
import com.kamshanski.utils.collections.primitivearraylists.ArrayListDouble;

import java.util.ArrayList;

public class ComplexStorage {
    private final Beacon beacon;
    private final MacAddress mac;
    private final String tag;
    ArrayList<double[]> storages = new ArrayList<>(100);
    ArrayListDouble holder = new ArrayListDouble(25, 5);
    private final FilterBunch filter;
    int fullSize = 0;
    double sum = 0.0d;

    public ComplexStorage(Beacon beacon, MacAddress mac, String tag, FilterBunch filter) {
        this.beacon = beacon;
        this.mac = mac;
        this.tag = tag;
        this.filter = filter;
    }

    public synchronized void completeRecording(boolean invert) {
        if (holder.size() > 0) {
            double[] numbers = holder.toArray();
            if (invert) {
                ArrayListDouble.invert(numbers);
            }
            if (filter != null)
                numbers = filter.filter(numbers);
            storages.add(numbers);
            holder.clear();
        }
    }

    public synchronized void putItem(double ss) {
        holder.add(ss);
        fullSize++;
        sum += ss;
    }

    public synchronized double[] get(int index) {
        return index < storages.size() ? storages.get(index) : new double[0];
    }

    public synchronized int size() {
        return storages.size();
    }

    public synchronized int fullSize() {
        return fullSize;
    }

    public synchronized double getMean() {
        return fullSize > 0 ? sum / fullSize : 0;
    }

    public synchronized double[] getFullSizeAndMean() {
        return new double[] {fullSize, getMean()};
    }

    public boolean metaEquals(int beaconId, MacAddress mac) {
        return beaconId == beacon.id && mac.equals(this.mac);
    }

    public synchronized void clear() {
        storages.clear();
        holder.clear();
        fullSize = 0;
        sum = 0.0d;
    }

    public String name() {
        return beacon.name() + "_" + tag + "_" + mac.macString;
    }



}
