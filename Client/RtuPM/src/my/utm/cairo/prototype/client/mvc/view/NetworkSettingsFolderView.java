package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;

import com.extjs.gxt.ui.client.widget.ContentPanel;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.model.Folder;

public class NetworkSettingsFolderView extends BaseFolderView {
    private static final String menuHeading = "Network Properties";

    private static final Folder networkSettings = 
        new Folder("Network Settings");

    private static final Folder portForwarding = 
        new Folder("Port Forwarding");

    public NetworkSettingsFolderView(Controller controller) { 
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
                Dispatcher.get().dispatch(AppEvents.NAV_NET_SETTINGS);
            }
        });

        setupTree(networkSettings, portForwarding);

        tree.getSelectionModel().addSelectionChangedListener(
            new SelectionChangedListener<Folder>() {

            @Override
            public void selectionChanged(SelectionChangedEvent<Folder> se) {
                String f = se.getSelection().get(0).getName();
                AppEvent evt;

                if ( f.equals(networkSettings.getName()) ) {

                    evt = new AppEvent(AppEvents.NAV_NET_SETTINGS);
                    
                } else if( f.equals(portForwarding.getName()) ) {

                    evt = new AppEvent(AppEvents.NAV_PORT_FORWARDING);

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
