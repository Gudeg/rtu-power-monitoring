package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;

import com.extjs.gxt.ui.client.store.TreeStore;

import com.extjs.gxt.ui.client.util.IconHelper;

import com.extjs.gxt.ui.client.widget.ContentPanel;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.model.Folder;

public class DataFolderView extends BaseFolderView {
    private static final String menuHeading = "Power Monitoring Data";

    // Folder indicate submenu
    private final Folder powerConsumption = 
        new Folder("Power Consumption");
    private final Folder powerUptime = 
        new Folder("Power Uptime");
        
    public DataFolderView(Controller controller) { 
        super(controller);
    }

    @Override
    protected void initialize() {
    }

    @Override 
    protected void handleEvent(AppEvent e) {
        EventType et = e.getType();
        if (et == AppEvents.INIT) {
            initUI();
        }
    }

    protected void initUI() {

        ContentPanel west = Registry.get(AppView.WEST_PANEL); 
        ContentPanel navEntry = new ContentPanel();

        navEntry.setAnimCollapse(false);
        navEntry.setHeading(menuHeading);

        navEntry.addListener(Events.Expand, 
            new Listener<ComponentEvent>() {

            public void handleEvent(ComponentEvent e) {
                Dispatcher.get().dispatch(AppEvents.NAV_DATA);
            }
        });

        // TODO: refactor this into one BaseFolderView class
        setupTree(powerConsumption, powerUptime);

        tree.getStyle().setLeafIcon(IconHelper.createStyle("data"));

        tree.getSelectionModel().addSelectionChangedListener(
            new SelectionChangedListener<Folder>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<Folder> se) {
                String f = se.getSelection().get(0).getName();
                AppEvent evt;

                if ( f.equals(powerConsumption.getName()) ) {

                    evt = new AppEvent(AppEvents.NAV_DATA);
                    
                } else if ( f.equals(powerConsumption.getName()) ) {
                    
                    evt = new AppEvent(AppEvents.NAV_DATA_UPTIME);

                } else {

                    evt = new AppEvent(AppEvents.ERROR);

                }

                evt.setData("folder-name", f);
                fireEvent(evt);

            }

        });

        navEntry.add(tree);
        west.add(navEntry);
    }
}
