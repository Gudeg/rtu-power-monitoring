package my.utm.cairo.prototype.client;

import com.extjs.gxt.ui.client.GXT;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

import com.extjs.gxt.ui.client.util.Theme;

import com.google.gwt.core.client.EntryPoint;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import com.google.gwt.user.client.Window;

import my.utm.cairo.prototype.client.mvc.controller.AppController;
import my.utm.cairo.prototype.client.mvc.controller.DataController;
import my.utm.cairo.prototype.client.mvc.controller.FirmwareUpdateController;
import my.utm.cairo.prototype.client.mvc.controller.NetworkSettingsController;
import my.utm.cairo.prototype.client.mvc.controller.SchedulerController;
import my.utm.cairo.prototype.client.mvc.controller.SettingsController;

public class RtuPM implements EntryPoint {

    private RequestBuilder rb; 
    private RequestCallback rcb; 

    private String url = URL.encode("http://" 
        + Window.Location.getHost() + "/index.php/auth/check");

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
        

        init();
    }

    private void init() {

        rb = new RequestBuilder(RequestBuilder.GET, url); 
        rcb = new RequestCallback() {

            @Override 
            public void onError(Request req, Throwable e) {
                Window.alert("Initialization error: Cannot connect to server\n" + e.toString());
            }

            @Override 
            public void onResponseReceived(Request req, Response res) {

                if (200 == res.getStatusCode()) {

                    int data = Integer.parseInt(res.getText());
                    Dispatcher.get().dispatch(AppEvents.INIT, data);

                } else { 

                    Dispatcher.get().dispatch(AppEvents.LOGIN);

                }
            }
        };

        try { 

            rb.sendRequest(null, rcb);

        } catch (RequestException e) { 

            Window.alert("Unable to connect to the server");

        }


    }
}
