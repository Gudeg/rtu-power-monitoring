package my.utm.cairo.prototype.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.HttpProxy;
import com.extjs.gxt.ui.client.data.JsonPagingLoadResultReader;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;

import com.extjs.gxt.ui.client.store.ListStore;

import com.extjs.gxt.ui.client.widget.ContentPanel;

import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;

import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.URL;

import my.utm.cairo.prototype.client.mvc.model.PowerData;

public class DataGrid extends ContentPanel {
    private Grid<PowerData> grid;
    private ListStore<PowerData> store = null;

    private HttpProxy<PagingLoadResult<PowerData>> proxy;
    private JsonPagingLoadResultReader<PagingLoadResult<PowerData>> reader;
    private PagingLoader<PagingLoadResult<PowerData>> loader; 

    private final int pagingSize = 10;

    private RequestBuilder rb; 
    private String url = "http://127.0.0.1:8080/index.php/test/json";

    private ColumnModel cm;
    private final PagingToolBar toolBar = new PagingToolBar(pagingSize);

    public DataGrid() {
        setLayout(new FitLayout());
        setBodyBorder(false);
        setHeaderVisible(false);
        setBodyStyle("padding: 20px; background: none");

        cm = new ColumnModel(initColumn());
        initLoader(url); 

        store = new ListStore<PowerData>(loader);

        grid = new Grid<PowerData>(store, cm);
        grid.setAutoHeight(true);
        grid.setAutoExpandColumn("date");

        grid.setBorders(true);
        grid.setLoadMask(true);
        grid.setStripeRows(true);
        grid.setColumnLines(true);
        grid.setColumnReordering(true);

        grid.getView().setEmptyText("No Data Retrieved");

        toolBar.bind(loader);

        setBottomComponent(toolBar);

        add(grid);
        loader.load();
    }

    private List<ColumnConfig> initColumn() {

        final List<ColumnConfig> columns = 
            new ArrayList<ColumnConfig>();

        columns.add(new ColumnConfig("logid", "Record Number", 100));
        columns.add(new ColumnConfig("average_consumption", 
            "Average Consumption", 200)); 

        columns.add(new ColumnConfig("uptime", "Uptime", 100));
        columns.add(new ColumnConfig("date", "Date", 220));

        return columns; 
    }

    private void initLoader(String url) {

        rb = new RequestBuilder(RequestBuilder.GET, URL.encode(url));

        proxy = new HttpProxy<PagingLoadResult<PowerData>>(rb);

        reader = new JsonPagingLoadResultReader 
            <PagingLoadResult<PowerData>>(PowerData.getModelType());

        loader = new BasePagingLoader<PagingLoadResult<PowerData>>
                 (proxy, reader);
        loader.setLimit(15);
    }

    public ListStore<PowerData> getStore() {
        if (store != null) { 
            return store; 
        } else {
            return null;
        }
    }
}
