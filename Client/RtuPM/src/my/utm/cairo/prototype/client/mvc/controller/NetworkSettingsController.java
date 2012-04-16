package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.view.NetworkSettingsFolderView;
import my.utm.cairo.prototype.client.mvc.view.NetworkSettingsView;

public class NetworkSettingsController extends BaseModuleController { 

    private NetworkSettingsFolderView folderView; 
    private NetworkSettingsView view; 

    public NetworkSettingsController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.NAV_NET_SETTINGS);
        registerEventTypes(AppEvents.NAV_PORT_FORWARDING);
        modCode = 4;
    }

    @Override 
    public void handleEvent(AppEvent e) {
        EventType et = e.getType();

        if (et == AppEvents.INIT && checkPermission( 
                (Integer) e.getData())) {

            forwardToView(folderView, e);

        } else if (et == AppEvents.NAV_NET_SETTINGS) {

            forwardToView(view, e);

        } else if (et == AppEvents.NAV_PORT_FORWARDING) {

            forwardToView(view, e);

        }
    }
    @Override 
    public void initialize() {
        view = new NetworkSettingsView(this);
        folderView = new NetworkSettingsFolderView(this);
    }
}

