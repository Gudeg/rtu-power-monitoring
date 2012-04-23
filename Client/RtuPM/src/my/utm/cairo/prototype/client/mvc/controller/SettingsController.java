package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.view.SettingsFolderView;
import my.utm.cairo.prototype.client.mvc.view.SettingsView;

public class SettingsController extends BaseModuleController { 

    private SettingsFolderView folderView; 
    private SettingsView view; 

    public SettingsController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.NAV_SETTINGS);
        registerEventTypes(AppEvents.NAV_DATE_TIME);
        registerEventTypes(AppEvents.NAV_ACCOUNT);
        modCode = 32;
    }

    @Override 
    public void handleEvent(AppEvent e) {
        EventType et = e.getType();
        if (et == AppEvents.INIT && checkPermission( 
                (Integer) e.getData())) {

            forwardToView(folderView, e);

        } else if (et == AppEvents.NAV_SETTINGS) {
            forwardToView(view, e);
        } else if (et == AppEvents.NAV_DATE_TIME) {
            forwardToView(view, e);
        } else if (et == AppEvents.NAV_ACCOUNT) {
            forwardToView(view, e);
        }
    }

    @Override 
    public void initialize() {
        view = new SettingsView(this);
        folderView = new SettingsFolderView(this);
    }
}
