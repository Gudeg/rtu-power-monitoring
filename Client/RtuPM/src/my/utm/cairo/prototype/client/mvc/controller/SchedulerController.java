package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.view.SchedulerFolderView;
import my.utm.cairo.prototype.client.mvc.view.SchedulerView;

public class SchedulerController extends BaseModuleController { 

    private SchedulerFolderView folderView; 
    private SchedulerView view; 

    public SchedulerController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.NAV_CRONJOB);
        modCode = 8;
    }

    @Override 
    public void handleEvent(AppEvent e) {
        EventType et = e.getType();
        
        if (et == AppEvents.INIT && checkPermission( 
                (Integer) e.getData())) {

            forwardToView(folderView, e);

        } else if (et == AppEvents.NAV_CRONJOB) {

            forwardToView(view, e);

        }
    }

    @Override 
    public void initialize() {
        view = new SchedulerView(this);
        folderView = new SchedulerFolderView(this);
    }
}
