package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.view.FirmwareUpdateFolderView;
import my.utm.cairo.prototype.client.mvc.view.FirmwareUpdateView;

public class FirmwareUpdateController extends BaseModuleController { 

    private FirmwareUpdateFolderView folderView; 
    private FirmwareUpdateView view; 

    public FirmwareUpdateController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.NAV_FIRMWARE);
        registerEventTypes(AppEvents.NAV_FIRMWARE_UPDATE);
        modCode = 16;
    }


    @Override 
    public void handleEvent(AppEvent e) {
        EventType et = e.getType();
        if (et == AppEvents.INIT && checkPermission( 
                (Integer) e.getData())) {

            forwardToView(folderView, e);

        } else if (et == AppEvents.NAV_FIRMWARE) {

            forwardToView(view, e);

        } else if (et == AppEvents.NAV_FIRMWARE_UPDATE) {

            forwardToView(view, e);

        }
    }

    @Override 
    public void initialize() {
        view = new FirmwareUpdateView(this);
        folderView = new FirmwareUpdateFolderView(this);
    }
}

