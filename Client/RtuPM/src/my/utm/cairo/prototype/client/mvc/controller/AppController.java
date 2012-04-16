package my.utm.cairo.prototype.client.mvc.controller;

import java.math.BigInteger;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import java.security.MessageDigest;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

// TODO: Remove this
import com.google.gwt.user.client.Window;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.model.User;

import my.utm.cairo.prototype.client.mvc.view.AppView;

public class AppController extends Controller { 
    private AppView appView;

    public AppController() { 
        registerEventTypes(AppEvents.INIT);
        registerEventTypes(AppEvents.LOGIN);
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
            onAuthenticate();
        } else if ( type.equals(AppEvents.ERROR) ) {
            onError(event);
        }
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
    private void onAuthenticate() { 
        User user = (User) Registry.get(AppView.USER);
        if ( authenticate( user ) ) {

            Dispatcher.get().dispatch(AppEvents.INIT, 
                user.getPermission());

        } else {
            Dispatcher.get().dispatch(AppEvents.LOGIN);
        }
    }

    private boolean authenticate(User user) {
        //TODO: do remote authentication
        //TODO: Delete the next line (permission set), permission
        //should be set by the server
        user.set("permission", 31);
        return true;
    }


    @Override 
    public void initialize() {
        super.initialize();
        appView = new AppView(this);
    }
}
