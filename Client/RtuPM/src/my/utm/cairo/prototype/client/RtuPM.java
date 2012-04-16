package my.utm.cairo.prototype.client;

import com.extjs.gxt.ui.client.GXT;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

import com.extjs.gxt.ui.client.util.Theme;

import com.google.gwt.core.client.EntryPoint;

import my.utm.cairo.prototype.client.mvc.controller.AppController;
import my.utm.cairo.prototype.client.mvc.controller.DataController;
import my.utm.cairo.prototype.client.mvc.controller.FirmwareUpdateController;
import my.utm.cairo.prototype.client.mvc.controller.NetworkSettingsController;
import my.utm.cairo.prototype.client.mvc.controller.SchedulerController;
import my.utm.cairo.prototype.client.mvc.controller.SettingsController;

public class RtuPM implements EntryPoint {
    
    @Override 
    public void onModuleLoad() {
        GXT.setDefaultTheme(Theme.GRAY, true);

        Dispatcher dispatcher = Dispatcher.get();
        dispatcher.addController(new AppController());
        dispatcher.addController(new DataController());
        // normal settings
        dispatcher.addController(new SettingsController());
        // network settings, port forwarding
        dispatcher.addController(new NetworkSettingsController());
        dispatcher.addController(new SchedulerController());
        dispatcher.addController(new FirmwareUpdateController());
        //dispatcher.addController(new AdminController());

        dispatcher.dispatch(AppEvents.LOGIN);
    }
}
