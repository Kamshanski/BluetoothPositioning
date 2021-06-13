package com.kamshanski.blepositioning.appcontrol;

import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.blepositioning.model.core.Dimensions;
import com.kamshanski.blepositioning.utils.SquareDimension;
import com.kamshanski.utils.structures.pairs.Pair;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.Marker;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.knowm.xchart.style.colors.XChartSeriesColors.*;
import static org.knowm.xchart.style.markers.SeriesMarkers.*;
import static org.knowm.xchart.style.markers.SeriesMarkers.RECTANGLE;

public class PositionAreaPlotViewModel extends PlotViewModelAbstract {
    final SquareDimension plotConstraints;
    final SquareDimension fieldConstraints;
    HashMap<String, Pair<double[], double[]>> ys = new HashMap<>(7);
    public static final int TARGET_RADIUS = 10;

    public PositionAreaPlotViewModel(SquareDimension plotConstraints, SquareDimension fieldConstraints) {
        super();

        model.observe(m -> {
            m.latestPosition.observe(this::setNewPosition, false);
        }, true);

        this.plotConstraints = plotConstraints;
        this.fieldConstraints = fieldConstraints;

        graph = new XYChartBuilder()
                .xAxisTitle("X, см")
                .yAxisTitle("Y, см")
                .theme(Styler.ChartTheme.XChart)
                .build();

        graph.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        graph.getStyler().setChartTitleVisible(false);
        graph.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        graph.getStyler().setMarkerSize(16);
        graph.getStyler().setSeriesColors(new Color[]{BLUE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE, ORANGE, PURPLE,});
        graph.getStyler().setSeriesMarkers(new Marker[]{DIAMOND,
                CIRCLE, CIRCLE,
                SQUARE, SQUARE,
                TRIANGLE_DOWN, TRIANGLE_DOWN,
                TRIANGLE_UP, TRIANGLE_UP,
                CROSS, CROSS,
                PLUS, PLUS,
                RECTANGLE, RECTANGLE});

        graph.addSeries("FieldBorer",
                new double[]{fieldConstraints.xMin, fieldConstraints.xMax, fieldConstraints.xMax, fieldConstraints.xMin, fieldConstraints.xMin},
                new double[]{fieldConstraints.yMin, fieldConstraints.yMin, fieldConstraints.yMax, fieldConstraints.yMax, fieldConstraints.yMin});

    }


    public void setNewPosition(HashMap<String, Dimensions> pack) {
        Set<String> tags = new LinkedHashSet<>(pack.keySet());
        tags.addAll(ys.keySet());
        for (String tag : tags) {
            Dimensions d = pack.get(tag);
            if (d == null) {
                ys.remove(tag);
                graph.removeSeries(tag);
            } else {
                double x = fieldConstraints.fitX(d.x);
                double y = fieldConstraints.fitY(d.y);
                Pair<double[], double[]> pair = ys.get(tag);
                if (pair != null) {
                    pair.fst[0] = x;
                    pair.snd[0] = y;
                    graph.updateXYSeries(tag, pair.fst, pair.snd, null);
                } else {
                    pair = Pair.of(new double[]{x}, new double[]{y});
                    ys.put(tag, pair);
                    graph.addSeries(tag, pair.fst, pair.snd, null);
                }
            }
        }
        notifyPlotPanel();
    }

    @Override
    public Styler getStyler() {
        return graph.getStyler();
    }
}
