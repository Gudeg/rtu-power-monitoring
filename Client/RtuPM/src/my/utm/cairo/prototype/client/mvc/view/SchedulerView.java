package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.widget.CronjobForm;

public class SchedulerView extends BaseWidgetView { 
    private CronjobForm cronjobForm;

    public SchedulerView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {
        super.initialize();

        cronjobForm = new CronjobForm();
    }

    private void setupCronjobForm() {

        cronjobForm.fetchInitialValue();
        container.setHeading("Crontab config");
        container.add(cronjobForm, center); 
        container.setIconStyle("cronjob");
        
    }

    @Override 
    protected void handleEvent(AppEvent e) {

        LayoutContainer wrapper = loadWrapper();

        EventType et = e.getType();

        if (et == AppEvents.NAV_CRONJOB) {

            setupCronjobForm();

        }

        attachContainer(wrapper);
    }
}
