package com.kamshanski.blepositioning.model;

import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.model.core.MacAddress;
import com.kamshanski.blepositioning.model.methods.Positioning;
import com.kamshanski.blepositioning.model.methods.solvers.particleswarm.ParticleSwamEstimator;
import com.kamshanski.blepositioning.model.storage.Measurement;
import com.kamshanski.blepositioning.model.methods.distancefunctions.DistanceFunction;
import com.kamshanski.blepositioning.model.methods.distancefunctions.Exponent;
import com.kamshanski.blepositioning.model.methods.filters.*;
import com.kamshanski.blepositioning.model.methods.solvers.classificators.RandomForestMedian;
import com.kamshanski.blepositioning.model.methods.solvers.classificators.SvrMedian;
import com.kamshanski.blepositioning.model.methods.solvers.localization.WCL;
import com.kamshanski.blepositioning.model.storage.ComplexStorage;
import com.kamshanski.blepositioning.model.storage.NiceStorage;
import com.kamshanski.blepositioning.model.storage.PositioningStorage;
import com.kamshanski.blepositioning.utils.U;
import com.kamshanski.utils.dataflow.LiveData;
import com.kamshanski.utils.test.Timer;
import com.kamshanski.utils.collections.builders.HashMapBuilder;
import com.kamshanski.utils.collections.primitivearraylists.ArrayListDouble;

import java.util.*;

import static com.kamshanski.blepositioning.comport.ComPortConstants.PAYLOAD;

public class Model {
    public final LiveData<HashMap<String, HashMap<Beacon, double[]>>> latestRssiMeasuring = new LiveData<>(null);
    public final LiveData<HashMap<String, Dimensions>> latestPosition = new LiveData<>(null);
    public final LiveData<Integer> processingFinished = new LiveData<>(0);
    public final LiveData<MacAddress[]> targetsMacs = new LiveData<>(new MacAddress[0]);

    public boolean expIsOn = false;
    public final LiveData<Integer> slavesNum =  new LiveData<>(0);
    public final LiveData<Integer> targetsNum = new LiveData<>(0);

    private int targetsCapacity = 0;
    public ArrayList<NiceStorage> rssiStorages;
    public ArrayList<ComplexStorage> experimentalStorages = new ArrayList<>(5);
    public LinkedHashMap<String, ArrayList<Dimensions>> posExpMap = new LinkedHashMap<>(16);

    private Map<String, Positioning> positionings = new LinkedHashMap<>(5);


    private Model() {
        rssiStorages = new ArrayList<>();
/*
        filterStorage.add(new NiceStorage("raw", new FilterBunch(new NiceFilter[0])));


        experimentalStorages.add(new ComplexStorage(Beacon.MAIN, Beacon.TARGET.mac, "raw", null));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_1, Beacon.TARGET.mac, "raw", null));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_2, Beacon.TARGET.mac, "raw", null));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_3, Beacon.TARGET.mac, "raw", null));

        experimentalStorages.add(new ComplexStorage(Beacon.MAIN, Beacon.TARGET.mac, "Median7", new FilterBunch(new NiceFilter[]{
                new Median(7)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_1, Beacon.TARGET.mac, "Median7", new FilterBunch(new NiceFilter[]{
                new Median(7)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_2, Beacon.TARGET.mac, "Median7", new FilterBunch(new NiceFilter[]{
                new Median(7)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_3, Beacon.TARGET.mac, "Median7", new FilterBunch(new NiceFilter[]{
                new Median(7)
        })));

        experimentalStorages.add(new ComplexStorage(Beacon.MAIN, Beacon.TARGET.mac, "Kalman0.05,2", new FilterBunch(new NiceFilter[]{
                new Kalman(0.05, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_1, Beacon.TARGET.mac, "Kalman0.05,2", new FilterBunch(new NiceFilter[]{
                new Kalman(0.05, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_2, Beacon.TARGET.mac, "Kalman0.05,2", new FilterBunch(new NiceFilter[]{
                new Kalman(0.05, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_3, Beacon.TARGET.mac, "Kalman0.05,2", new FilterBunch(new NiceFilter[]{
                new Kalman(0.05, 2)
        })));


        experimentalStorages.add(new ComplexStorage(Beacon.MAIN, Beacon.TARGET.mac, "Median(10)_Kalman(0.2,4)", new FilterBunch(new NiceFilter[]{
                new Median(7),
                new Kalman(0.2, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_1, Beacon.TARGET.mac, "Median(10)_Kalman(0.2,4)", new FilterBunch(new NiceFilter[]{
                new Median(7),
                new Kalman(0.2, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_2, Beacon.TARGET.mac, "Median(10)_Kalman(0.2,4)", new FilterBunch(new NiceFilter[]{
                new Median(7),
                new Kalman(0.2, 2)
        })));
        experimentalStorages.add(new ComplexStorage(Beacon.SLAVE_3, Beacon.TARGET.mac, "Median(10)_Kalman(0.2,4)", new FilterBunch(new NiceFilter[]{
                new Median(7),
                new Kalman(0.2, 2)
        })));


        filterStorage.add(new NiceStorage("mean", new FilterBunch(new NiceFilter[] {
                new MeanAndVariance(true)
        })));
        filterStorage.add(new NiceStorage("kaufman", new FilterBunch(new NiceFilter[]{
                new KaufmansAdaptiveMovingAverage()
        })));
        filterStorage.add(new NiceStorage("kaufman1", new FilterBunch(new NiceFilter[]{
                new KaufmansAdaptiveMovingAverage(15,10,50, -60)
        })));
        filterStorage.add(new NiceStorage("Kalman(0.05,2)", new FilterBunch(new NiceFilter[]{
                new Kalman(0.05, 2)
        })));
        filterStorage.add(new NiceStorage("Median7Kalman(0.05,2)", new FilterBunch(new NiceFilter[]{
                new Median(7),
                new Kalman(0.2, 2)
        })));

        filterStorage.add(new NiceStorage("Median7", new FilterBunch(new NiceFilter[]{
                new Median(7)
        })));


        pos.put("M_Exp0_PS_K", new Pair<>(
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        new HashMapBuilder<Beacon, DistanceFunction>()
                                .put(Beacon.MAIN, new Exponent(-11.1937, -17.1790))
                                .put(Beacon.SLAVE_1, new Exponent(-8.5589, -29.7391))
                                .put(Beacon.SLAVE_2, new Exponent(-11.9023, -16.8160))
                                .put(Beacon.SLAVE_3, new Exponent(-10.5379, -19.3999))
                                .finish(),
                        new ParticleSwamEstimator(70, 0, 70, 0, 900, 10, 10),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true
                ),
                new PositioningStorage(Beacon.TARGET.mac)
        ));

        pos.put("M_Exp1_PS_K", new Pair<>(
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        new HashMapBuilder<Beacon, DistanceFunction>()
                                .put(Beacon.MAIN,    new Exponent(-9.2420, -26.0896))
                                .put(Beacon.SLAVE_1, new Exponent(-6.5964, -38.3706))
                                .put(Beacon.SLAVE_2, new Exponent(-8.9192, -29.7185))
                                .put(Beacon.SLAVE_3, new Exponent(-8.7964, -27.3935))
                                .finish(),
                        new ParticleSwamEstimator(70, 0, 70, 0, 900, 10, 10),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true
                ),
                new PositioningStorage(Beacon.TARGET.mac)
        ));
*/


        rssiStorages.add(new NiceStorage("Без фильтра", new FilterBunch(new NiceFilter[]{
        })));

        rssiStorages.add(new NiceStorage("Медианный, n=7", new FilterBunch(new NiceFilter[]{
            new Median(7)
        })));
//
//        rssiStorages.add(new NiceStorage("Калман, P=0.2, Q=2", new FilterBunch(new NiceFilter[]{
//            new Kalman(0.2, 2)
//        })));

//        rssiStorages.add(new NiceStorage("Кауфман, n=15, f=10, s=50", new FilterBunch(new NiceFilter[]{
//                new KaufmansAdaptiveMovingAverage(15, 3, 20, 16)
//        })));

//        rssiStorages.add(new NiceStorage("Median(7)", new FilterBunch(new NiceFilter[]{
//                new Median(7)
//        })));



        positionings.put("M_Exp1_PS_!",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        new HashMapBuilder<Beacon, DistanceFunction>()
                                .put(Beacon.MAIN, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_1, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_2, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_3, new Exponent(-11.2, -20))
                                .finish(),
                        new ParticleSwamEstimator(70, 0, 70, 0, 500, 10, 10),
                        new FilterBunch(new NiceFilter[0]),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
        ));

        positionings.put("M_Exp1_PS_K",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        new HashMapBuilder<Beacon, DistanceFunction>()
                                .put(Beacon.MAIN, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_1, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_2, new Exponent(-11.2, -20))
                                .put(Beacon.SLAVE_3, new Exponent(-11.2, -20))
                                .finish(),
                        new ParticleSwamEstimator(70, 0, 70, 0, 500, 10, 10),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
                ));

        positionings.put("M_!_SVR.M_!",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        null,
                        new SvrMedian(),
                        new FilterBunch(new NiceFilter[] { }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
        ));

        positionings.put("M_!_SVR.M_K",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        null,
                        new SvrMedian(),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
                ));

        positionings.put("M_!_RF.M_!",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        null,
                        new RandomForestMedian(),
                        new FilterBunch(new NiceFilter[] { }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
                ));

        positionings.put("M_!_RF.M_K",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        null,
                        new RandomForestMedian(),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac)
                ));

        positionings.put("M_Exp_WCL_!", new Positioning (
                    new FilterBunch(new NiceFilter[]{
                            new Median(7),
                    }),
                    new HashMapBuilder<Beacon, DistanceFunction>()
                            .put(Beacon.MAIN, new Exponent(-11.1937, -17.1790))
                            .put(Beacon.SLAVE_1, new Exponent(-11.5589, -19.7391))
                            .put(Beacon.SLAVE_2, new Exponent(-11.9023, -16.8160))
                            .put(Beacon.SLAVE_3, new Exponent(-10.5379, -19.3999))
                            .finish(),
                    new WCL(2.1),
                    new FilterBunch(new NiceFilter[] {
                    })  ,
                    true,
                    new PositioningStorage(Beacon.TARGET.mac))
        );

        positionings.put("M_Exp_WCL_K",
                new Positioning (
                        new FilterBunch(new NiceFilter[]{
                                new Median(7),
                        }),
                        new HashMapBuilder<Beacon, DistanceFunction>()
                                .put(Beacon.MAIN, new Exponent(-11.1937, -17.1790))
                                .put(Beacon.SLAVE_1, new Exponent(-8.5589, -29.7391))
                                .put(Beacon.SLAVE_2, new Exponent(-11.9023, -16.8160))
                                .put(Beacon.SLAVE_3, new Exponent(-10.5379, -19.3999))
                                .finish(),
                        new WCL(2.1),
                        new FilterBunch(new NiceFilter[] {
                                new Kalman(0.2, 2)
                        }),
                        true,
                        new PositioningStorage(Beacon.TARGET.mac))
        );
    }


    public void putNewMeasurements(long duration, long arrivalTime, String payload) {
        Timer.start("fullMeasuring");
        String addrs = payload.substring(PAYLOAD.length);   // to cut off all noisy data

        HashMap<Integer, ArrayListDouble> uninvertedRssis = new HashMap<>(30);
        MacAddress mac = null;
        Measurement measurement = new Measurement();

        for (int i = 0, startIndex = 0; i < 50; i++) {
            // read and go to the next record in payload
            String macStr = addrs.substring(startIndex, startIndex+=12);
            String rssiStr = addrs.substring(startIndex, startIndex+=3);
            String beaconIdStr = addrs.substring(startIndex, startIndex+=2);

            // just for simplicity
            mac = MacAddress.getMac(macStr);

            // Parse measurement and save it
            int rssi = Integer.parseInt(rssiStr, 16);
            int beaconId = Integer.parseInt(beaconIdStr, 16);
            ArrayListDouble ald = uninvertedRssis.computeIfAbsent(beaconId, (id) -> new ArrayListDouble(30, 10));
            ald.add(rssi);
            measurement.put(Beacon.byId(beaconId), rssi);

            // experimental part (delete after experiment)
            if (expIsOn)
                for (ComplexStorage cs : experimentalStorages) {
                    if (cs.metaEquals(beaconId, mac)) {
                        cs.putItem(rssi);
                    }
                }
        }
        if (expIsOn)
            for (ComplexStorage cs : experimentalStorages) {
                cs.completeRecording(true);
            }

        uninvertedRssis.forEach((beaconId, rssis) -> rssis.invert().toArray());


        try {
            for (NiceStorage fs : rssiStorages) {
                fs.clearLast();
            }

            uninvertedRssis.forEach((beaconId, rssis) -> {
                double[] invertedRssi = rssis.toArray();
                for (NiceStorage fs : rssiStorages) {
                    fs.add(beaconId, invertedRssi);
                }
            });

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        measurement.complete();
        HashMap<String, Dimensions> posMap = new LinkedHashMap<>(4);
        StringBuilder b = new StringBuilder();
        positionings.forEach((tag, positioning) -> {
            Dimensions d = positioning.of(measurement.get());
            U.nout(tag + " " + positioning.getLog());   // Log
            if (d != null) {
                posMap.put(tag, d);
                if (expIsOn && measurement.get().size() == 4) {
                    posExpMap
                            .computeIfAbsent(tag, t -> new ArrayList<>(100))
                            .add(d);
                }
                b.append(String.format(Locale.US, "\n%s:\tX = %.3f,\t Y = %.3f", tag, d.x, d.y));
                //            measuring.setDimensions(d);
            } else {
                b.append(String.format(Locale.US, "\n%s gives null dimension", tag));
            }
        });
        U.nout(b.toString());


        HashMap<String, HashMap<Beacon, double[]>> packs = new HashMap<>(rssiStorages.size());
        for (NiceStorage fs : rssiStorages) {
            packs.put(fs.name, fs.lastPack);
        }

        latestRssiMeasuring.set(packs);
        latestPosition.set(posMap);
        processingFinished.set(processingFinished.get() + 1);

        Timer.print("fullMeasuring");
    }

    public boolean setSlavesNum(int slavesNum) {
        if (slavesNum >= 0) {
            this.slavesNum.set(slavesNum);
            return true;
        }
        return false;
    }

    public boolean setTargets(String sizeStr, String capacityStr, String macStr) {
        try {
            targetsNum.set(Integer.parseInt(sizeStr));
            targetsCapacity = Integer.parseInt(capacityStr);
            parseTargetsMacs(macStr);
            return true;
        } catch (NumberFormatException e) {
            U.nout("ERROR !!!!!!!!!!!!! " + e.getMessage());
            return false;
        }
    }

    public void parseTargetsMacs(String buf) {
        int bufSize = buf.length() / 17;
        assert bufSize == targetsNum.get();
        MacAddress[] targetsMacs = new MacAddress[targetsNum.get()];
        for (int i = 0; i < bufSize; i++) {
            targetsMacs[i] = new MacAddress(buf.substring(17*i, 17*(i+1)));
        }
        this.targetsMacs.set(targetsMacs);
    }

    public String getTargetsString() {
        StringBuilder builder = new StringBuilder();
        if (targetsNum.get() > 0) {
            builder.append("Targets{")
                    .append("\n   Size: ").append(targetsNum)
                    .append("\n   Capacity: ").append(targetsCapacity)
                    .append("\n   Devices: ");
            for (int i = 0; i < targetsMacs.get().length; i++) {
                builder.append("\n      ").append(targetsMacs.get()[i].macString);
            }
            builder.append("\n}TargetsSet");
        }
        else {
            builder.append("Targets{ 0 }");
        }

        return builder.toString();
    }

    public String getTargetsSetString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, count = targetsNum.get(); i < count; i++) {
            builder.append(i).append(") ").append(targetsMacs.get()[i].macString);
        }
        return builder.toString();
    }

    // Singleton

    public List<ComplexStorage> getExpStorages() {
        return experimentalStorages;
    }

    public Map<String, Positioning> getPositionings() {
        return positionings;
    }

    private static LiveData<Model> instance = new LiveData<>(null);
    public static LiveData<Model> getClearInstance(boolean clear) {
        if (instance.get() == null || clear) {
            synchronized (Model.class) {
                if (instance.get() == null || clear) {
                    instance.set(new Model());
                }
            }
        }
        return instance;
    }
}
