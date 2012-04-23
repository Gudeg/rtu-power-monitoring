package my.utm.cairo.prototype.client.mvc.controller;

import java.math.BigInteger;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import java.security.MessageDigest;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

import com.extjs.gxt.ui.client.widget.Viewport;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

// TODO: Remove this
import com.google.gwt.user.client.Window;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.model.User;

import my.utm.cairo.prototype.client.mvc.view.AppView;

public class AppController extends Controller { 
    private AppView appView;

    private RequestBuilder rb; 
    private RequestCallback rcb;
    private int permission = 0;

    private static final String logoutUrl = URL.encode("http://"
        + Window.Location.getHost() + "/index.php/auth/logout");

    private static final String authUrl = URL.encode("http://" 
        + Window.Location.getHost() + "/index.php/auth/login");

    public AppController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.LOGIN);
        registerEventTypes(AppEvents.LOGOUT);
        registerEventTypes(AppEvents.ERROR);
        registerEventTypes(AppEvents.AUTH);
    }

    @Override 
    public void handleEvent(AppEvent event) { 
        EventType type = event.getType();
        if( type.equals(AppEvents.INIT) ) {
            onInit(event);
        } else if ( type.equals(AppEvents.LOGIN) ) {
            onLogin(event);
        } else if ( type.equals(AppEvents.AUTH) ) {
            User user = (User) Registry.get(AppView.USER);
            authenticate(user);
        } else if ( type.equals(AppEvents.ERROR) ) {
            onError(event);
        } else if ( type.equals(AppEvents.LOGOUT) ) {
            onLogout();
        }
    }

    private void onLogout() {

        rb = new RequestBuilder(RequestBuilder.GET, logoutUrl); 

        rcb = new RequestCallback() {

            @Override 
            public void onError(Request req, Throwable e) {

                Window.alert("Connection Error");
                Dispatcher.get().dispatch(AppEvents.LOGIN);

            }

            @Override 
            public void onResponseReceived(Request req, Response res) {

                if (200 == res.getStatusCode()) {

                    Window.alert("You've been logged out, "+
                        "Have a nice day!");


                } else { 

                    Window.alert("Redirect to login");

                }

                Dispatcher.get().dispatch(AppEvents.LOGIN);
            }
        };

        try { 

            rb.sendRequest(null, rcb);

        } catch (RequestException e) {

            Dispatcher.get().dispatch(AppEvents.LOGIN);

        }
        Viewport vport = Registry.get(AppView.VIEWPORT);
        vport.removeAll(); 
        vport = null;
    }

    public void onError(AppEvent event) { 
        //TODO: Better error mechanism
        Window.alert("error: " + event.getData());
    }

    public void onLogin(AppEvent event) {

        forwardToView(appView, event);

    }

    public void onInit(AppEvent event) { 

        forwardToView(appView, event);

    }

    private void authenticate(final User user) {
        //TODO: do remote authentication
        //TODO: Delete the next line (permission set), permission
        rb = new RequestBuilder(RequestBuilder.POST, authUrl); 

        rb.setHeader("content-type", 
            "application/x-www-form-urlencoded");
        
        JSONObject obj = new JSONObject();

        obj.put("userid", new JSONString((String) user.getUserId()));
        obj.put("password", new JSONString((String) user.getPassword()));

        try { 

            String data = URL.encode("auth=" + obj.toString()); 
            rcb = new RequestCallback() {

                @Override 
                public void onError(Request req, Throwable e) {

                    Window.alert("Connection Error");
                    Dispatcher.get().dispatch(AppEvents.LOGIN);

                }

                @Override 
                public void onResponseReceived(Request req, Response res) {

                    if (200 == res.getStatusCode()) {

                        user.set("permission", 
                            Integer.parseInt(res.getText()));

                        Dispatcher.get().dispatch(AppEvents.INIT, 
                            user.getPermission());

                    } else { 

                        Window.alert("Login failed");
                        Dispatcher.get().dispatch(AppEvents.LOGIN);

                    }
                }
            };

            rb.sendRequest(data, rcb);

        } catch (RequestException e) {

            Window.alert("Connection error: " + e.toString() + 
                "Please try again or contact the system admin");

            Dispatcher.get().dispatch(AppEvents.LOGIN);

        }
    }

    @Override 
    public void initialize() {
        super.initialize();
        appView = new AppView(this);


    }
}
