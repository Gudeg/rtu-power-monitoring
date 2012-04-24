package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextArea;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Window;

public class PortForwardingForm extends BaseFormWidget {
    // TODO: Use grid on next version to help assist the user 
    
    // Text Area can be edited manually. The value will be refreshed if 
    // other field already set
    private TextArea portForwardingConfig;  

    public PortForwardingForm() {
        super();

        portForwardingConfig = new TextArea();
        portForwardingConfig.setPreventScrollbars(false);
        portForwardingConfig.setHeight(200);

        setFieldProperties(portForwardingConfig, 
            "Port Forwarding Config", true, 26, false);

        postInitialize("portforwarding-form");
    }

    @Override 
    protected void send() {
        JSONObject obj = new JSONObject();

        obj.put("port_forwarding_config", 
            new JSONString(portForwardingConfig.getValue()));

        sendJSONPost(obj);
    }
    @Override 
    protected void validate() {

        submit.setEnabled(true);

    }
    @Override 
    protected void assignInitialValue() {

        portForwardingConfig.setValue(
            getFieldValue("port_forwarding_config"));

    }
}
