package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import com.extjs.gxt.ui.client.util.Margins;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.widget.DataChart;
import my.utm.cairo.prototype.client.widget.DataGrid;

public class DataView extends BaseWidgetView { 

    private DataGrid testGrid;
    private DataChart testChart; 

    public DataView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {

        super.initialize();

        testGrid = new DataGrid();

        south.setSize(250);
        south.setMargins(new Margins(5, 20, 0, 20));
    }

    private void setupGrid() {
        container.setHeading("Data Grid");
        container.setIconStyle("power-consumption");

        testChart = new DataChart(testGrid.getStore());

        container.add(testGrid, center);
        container.add(testChart, south);
    }

    @Override 
    protected void handleEvent(AppEvent e) {
        LayoutContainer wrapper = loadWrapper();

        EventType et = e.getType();

        if (et == AppEvents.NAV_DATA) {

            setupGrid();

        } else if (et == AppEvents.INIT) {

            setupGrid();

        }

        attachContainer(wrapper);
    }
}
