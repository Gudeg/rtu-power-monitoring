package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextArea;

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

    }

    @Override 
    protected void send() {

    }

    @Override 
    protected void validate() {

    }
}
