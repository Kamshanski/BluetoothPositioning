package com.kamshanski.blepositioning.appcontrol;

import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.blepositioning.model.core.Beacon;
import com.kamshanski.utils.collections.primitivearraylists.ArrayListDouble;
import com.kamshanski.utils.dataflow.DataPropagator;
import com.kamshanski.utils.structures.wrappers.Wrap;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.Marker;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.knowm.xchart.style.colors.XChartSeriesColors.*;
import static org.knowm.xchart.style.markers.SeriesMarkers.*;

public class RssiPlotViewModel extends PlotViewModelAbstract {
    double[] x;
    HashMap<String, double[]> ys = new HashMap<>(7);
    ArrayList<Integer> delimiters = new ArrayList<>();
    int N;
    String UPPER_LIMIT_SERIES_NAME = "Max RSSI";
    String LOWER_LIMIT_SERIES_NAME = "Min RSSI";

    public RssiPlotViewModel(int N) {
        super();
        model.observe(m -> {
            m.latestRssiMeasuring.observe(this::plotRssiPacks, false);
        }, true);
        XYChartBuilder xyChartBuilder = new XYChartBuilder();
        xyChartBuilder.xAxisTitle("Измерения");
        xyChartBuilder.yAxisTitle("RSSI, дБм");
        graph = xyChartBuilder
                .build();

        this.N = N;
        graph.addSeries(UPPER_LIMIT_SERIES_NAME, new int[]{0, N}, new int[]{-20, -20});
        graph.addSeries(LOWER_LIMIT_SERIES_NAME, new int[]{0, N}, new int[]{-90, -90});
        graph.getStyler().setSeriesColors(
                new Color[]{BLUE, BLUE, ORANGE, PURPLE, GREEN, RED, YELLOW, MAGENTA, PINK, LIGHT_GREY, CYAN, BROWN, BLACK, ORANGE, PURPLE, GREEN, RED, YELLOW, MAGENTA, PINK, LIGHT_GREY, CYAN, BROWN, BLACK});
        getStyler().setSeriesMarkers(new Marker[]{RECTANGLE, RECTANGLE, CIRCLE, SQUARE, DIAMOND, TRIANGLE_DOWN, TRIANGLE_UP, CROSS, PLUS, CIRCLE, SQUARE, DIAMOND, TRIANGLE_DOWN, TRIANGLE_UP, CROSS, PLUS});

        x = ArrayListDouble.doubleRange(0, N, 1);

    }

    public void plotRssiPacks(HashMap<String, HashMap<Beacon, double[]>> lastPacks) {
        PlotPack plotPack = new PlotPack();
        lastPacks.forEach((tag, lastPack) -> {
            lastPack.forEach((beacon, rssis) -> {
                if (rssis != null) {
//                    plotPack.add("(" + beacon.id + ") " + tag, rssis);
                    plotPack.add(tag, rssis);
                }
            });
        });

        putNewData(plotPack);
    }

    public void putNewData(PlotPack plotPack) {
        HashMap<String, double[]> pp = plotPack.getPack();
        HashSet<String> keys = new HashSet<>(ys.keySet());
        keys.addAll(pp.keySet());
        final int maxLen = plotPack.getMax();
        final Wrap<double[]> emptyY = new Wrap<>(null);

        keys.forEach(k -> {
            AtomicBoolean newSeries = new AtomicBoolean(false);
            double[] Y = ys.computeIfAbsent(k, k1 -> {
                double[] ys = new double[N];
                Arrays.fill(ys, -20);
                newSeries.set(true);
                return ys;
            });
            double[] newY = pp.get(k);
            if (newY == null) {
                if (emptyY.val == null) {
                    emptyY.val = new double[maxLen];
                    Arrays.fill(emptyY.val, 0.0);
                }
                newY = emptyY.val;
            }
            newY = ArrayListDouble.interpolate(newY, maxLen);
            ArrayListDouble.shiftAndFillBeginning(newY, Y);
            if (newSeries.get()) {
                graph.addSeries(k, x, Y);
            } else {
                graph.updateXYSeries(k, x, Y, null);
            }
        });

        notifyPlotPanel();

        // add and remove all unnecessary delimiters
//            delimiters.add(0);
//            for (int i = 0; i < delimiters.size(); i++) {
//                delimiters.set(i, delimiters.get(i) + size);
//            }
//            delimiters.removeIf(i -> i > N);

    }
}

