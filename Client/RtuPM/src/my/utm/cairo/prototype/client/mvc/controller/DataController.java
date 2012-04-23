package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.model.Folder;

import my.utm.cairo.prototype.client.mvc.view.DataFolderView;
import my.utm.cairo.prototype.client.mvc.view.DataView;

public class DataController extends BaseModuleController { 


    private DataFolderView folderView; 
    private DataView view; 

    public DataController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.NAV_DATA);
        registerEventTypes(AppEvents.NAV_DATA_UPTIME);
        modCode = 2;
    }

    // TODO: Can be converted to generic method on the parent 
    @Override 
    public void handleEvent(AppEvent e) {
        EventType et = e.getType();

        if (et == AppEvents.INIT && checkPermission( 
                (Integer) e.getData())) {

            forwardToView(folderView, e);
            forwardToView(view, e);
            
        } else if (et == AppEvents.NAV_DATA) {
            forwardToView(view, e);
        } else if (et == AppEvents.NAV_DATA_UPTIME) {
            forwardToView(view, e);
        }
    }
    @Override 
    public void initialize() {
        folderView = new DataFolderView(this);
        view = new DataView(this);
    }
}
