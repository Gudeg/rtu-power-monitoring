package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.widget.FirmwareUpdateForm;

public class FirmwareUpdateView extends BaseWidgetView { 
    private FirmwareUpdateForm firmwareUpdateForm; 

    public FirmwareUpdateView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {

        super.initialize();
        firmwareUpdateForm = new FirmwareUpdateForm();

    }

    private void setupFirmwareUpdateForm() {

        firmwareUpdateForm.fetchInitialValue();
        container.setHeading("Firmware Update Form");
        container.add(firmwareUpdateForm, center); 
        container.setIconStyle("tree-folder-open");

    }

    @Override 
    protected void handleEvent(AppEvent e) {

        LayoutContainer wrapper = loadWrapper();

        EventType et = e.getType();

        if (et == AppEvents.NAV_FIRMWARE_UPDATE) { 

            setupFirmwareUpdateForm();

        }

        attachContainer(wrapper);
    }
}
