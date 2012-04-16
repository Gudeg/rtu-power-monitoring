package my.utm.cairo.prototype.client.mvc.controller;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

public class BaseModuleController extends Controller { 
    protected int modCode; 

    protected boolean checkPermission(int code) {
        if ( (code & modCode) != 0) {
            return true; 
        } else { 
            return false;
        }
    }
    @Override 
    public void handleEvent(AppEvent e) {}

}
