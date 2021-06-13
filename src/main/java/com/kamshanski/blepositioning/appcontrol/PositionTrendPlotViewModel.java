package com.kamshanski.blepositioning.appcontrol;

import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.utils.collections.primitivearraylists.ArrayListDouble;
import com.kamshanski.utils.collections.slidingwindowarrays.SlidingWindowDoubleArray;
import com.kamshanski.utils.dataflow.DataPropagator;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Marker;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.knowm.xchart.style.colors.XChartSeriesColors.*;
import static org.knowm.xchart.style.markers.SeriesMarkers.*;
import static org.knowm.xchart.style.markers.SeriesMarkers.RECTANGLE;

public class PositionTrendPlotViewModel extends PlotViewModelAbstract {
    public final DataPropagator<String> positionLogMessage = new DataPropagator<>();
    public int slaveSourcesCount = 0;
    public final int N;
    HashMap<String, SlidingWindowDoubleArray> xs = new HashMap<>(7);
    HashMap<String, SlidingWindowDoubleArray> ys = new HashMap<>(7);
    public static final int TARGET_RADIUS = 10;

    public PositionTrendPlotViewModel(int N) {
        super();
        this.N = N;
        model.observe(m -> {
            m.latestPosition.observe(this::setNewPosition, false);
            m.latestRssiMeasuring.observe(map -> {
                var iterator = map.values().iterator();
                if (iterator.hasNext()) {
                    slaveSourcesCount = iterator.next().size();
                }
            }, false);
        }, true);


        graph = new XYChartBuilder()
                .yAxisTitle("Координата, см")
                .theme(Styler.ChartTheme.XChart)
                .build();

        graph.getStyler().setChartTitleVisible(false);
        graph.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        graph.getStyler().setSeriesColors(new Color[]{BLUE, BLUE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE,});
        graph.getStyler().setSeriesMarkers(new Marker[]{
                DIAMOND, DIAMOND,
                CIRCLE, CIRCLE,
                SQUARE, SQUARE,
                TRIANGLE_DOWN, TRIANGLE_DOWN,
                TRIANGLE_UP, TRIANGLE_UP,
                CROSS, CROSS,
                PLUS, PLUS,
                RECTANGLE, RECTANGLE});

        graph.addSeries("Max", new double[]{0, N - 1}, new double[]{70, 70});
        graph.addSeries("Min", new double[]{0, N - 1}, new double[]{0, 0});

    }

    public void setNewPosition(HashMap<String, Dimensions> pack) {
        StringBuilder builder = new StringBuilder().append("From ").append(slaveSourcesCount).append(" slaves");
        Set<String> tags = new LinkedHashSet<>(pack.keySet());
        tags.addAll(ys.keySet());
        for (String tag : tags) {
            Dimensions d = pack.get(tag);
            if (d == null) {
                ys.remove(tag + "_X");
                xs.remove(tag + "_Y");
                graph.removeSeries(tag + "_X");
                graph.removeSeries(tag + "_Y");
            } else {
                double x = d.x;
                double y = d.y;
                SlidingWindowDoubleArray arrayX = xs.computeIfAbsent(tag, t -> {
                    graph.addSeries(t + "_X", new double[]{0}, new double[]{0});
                    return new SlidingWindowDoubleArray(N - 1, 0);
                });
                SlidingWindowDoubleArray arrayY = ys.computeIfAbsent(tag, t -> {
                    graph.addSeries(t + "_Y", new double[]{0}, new double[]{0});
                    return new SlidingWindowDoubleArray(N - 1, 0);
                });
                arrayX.put(x);
                arrayY.put(y);
                double[] xGrid = ArrayListDouble.invert(ArrayListDouble.doubleRange(0, arrayY.getSize(), 1));
                try {
                    graph.updateXYSeries(tag + "_X", xGrid, ArrayListDouble.invert(arrayX.toArray()), null);
                    graph.updateXYSeries(tag + "_Y", xGrid, ArrayListDouble.invert(arrayY.toArray()), null);
                } catch (IllegalArgumentException ex) {
                    graph.addSeries(tag + "_X", xGrid, ArrayListDouble.invert(arrayX.toArray()), null);
                    graph.addSeries(tag + "_Y", xGrid, ArrayListDouble.invert(arrayY.toArray()), null);
                }

                builder.append("\n%s:\n   (%.2f\t : %.2f)".formatted(tag, x, y));
            }
        }
        positionLogMessage.set(builder.toString());
        notifyPlotPanel();
    }

}
