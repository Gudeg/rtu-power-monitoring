package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.charts.client.Chart;

import com.extjs.gxt.charts.client.model.BarDataProvider;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;

import com.extjs.gxt.charts.client.model.Legend.Position;

import com.extjs.gxt.charts.client.model.ScaleProvider;

import com.extjs.gxt.charts.client.model.charts.BarChart;

import com.extjs.gxt.charts.client.model.charts.BarChart.BarStyle;

import com.extjs.gxt.ui.client.store.ListStore;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.google.gwt.user.client.Window;

import my.utm.cairo.prototype.client.mvc.model.PowerData;

public class DataChart extends LayoutContainer { 
    private ListStore<PowerData> store; 
    private final String chartURL = "chart/open-flash-chart.swf";
    private final Chart chart = new Chart(chartURL);

    // TODO: Should use grid ready event instead
    public DataChart(ListStore<PowerData> store) {
        this.store = store; 

        // TODO: Chart Listener 
        //ChartListener listener = new ChartListener() {
            //public void chartClick(ChartEvent ce) {
                //int row = ce.getChartConfig().getValues().
                    //indexOf(ce.getDataType());
                //int col = ce.getChartModel().getChartConfigs.
                    //indexOf(ce.getChartConfig()) + 1;

                // fire event that contains row and col

                // later on grid
                //CellSelectionModel<PowerData> csm = 
                    //(CellSelectionModel<PowerData>) grid.getSelectionModel();

                //csm.selectCell(row, col);

        chart.setBorders(true);
        chart.setHeight(200);
        chart.setVisible(true);

        ChartModel cm = new ChartModel(
            "Power Consumption Per Day", 
            "font-size: 14px; font-family: Helvetica, Verdana;" + 
            "text-align: center");

        cm.setBackgroundColour("fefefe");
        cm.setLegend(new Legend(Position.TOP, true));
        cm.setScaleProvider(ScaleProvider.ROUNDED_NEAREST_SCALE_PROVIDER);

        BarChart bar = new BarChart(BarStyle.GLASS);
        bar.setColour("#ff6600");
        BarDataProvider dataProvider = new BarDataProvider(
            "average_consumption", "date");

        dataProvider.bind(store);
        bar.setDataProvider(dataProvider);
        //bar.addChartListener(listener);
        cm.addChartConfig(bar);

        chart.setChartModel(cm);
        add(chart);
    }
}
