package com.kamshanski.blepositioning.appcontrol;

import com.kamshanski.blepositioning.model.Model;
import com.kamshanski.utils.dataflow.LiveData;
import com.kamshanski.utils.dataflow.Notifier;
import org.apache.poi.ss.formula.functions.Mode;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;

public abstract class PlotViewModelAbstract {
    protected XYChart graph;
    public final Notifier plotUpdateNotifier = new Notifier();
    protected final LiveData<Model> model;

    public PlotViewModelAbstract() {
        this.model = Model.getClearInstance(false);
    }

    public XYChart getGraph() {return graph;}
    public Styler getStyler() {return graph.getStyler();}
    public void notifyPlotPanel() {
        plotUpdateNotifier.notifyListeners();
    }
}
