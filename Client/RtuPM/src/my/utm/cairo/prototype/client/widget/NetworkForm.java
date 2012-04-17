package my.utm.cairo.prototype.client.widget;


import com.extjs.gxt.ui.client.widget.form.TextField;

public class NetworkForm extends BaseFormWidget { 

    private TextField<String> ipAddress; 
    private TextField<String> subnetMask; 
    private TextField<String> defaultGateway; 
    private TextField<String> dnsServer1; 
    private TextField<String> dnsServer2; 

    private static final String formName = "network-form";

    public NetworkForm() {
        super();

        setFormAction(formName);

        ipAddress = new TextField<String>();
        setFieldProperties(ipAddress, "Server IP Address");
        
        subnetMask = new TextField<String>();
        setFieldProperties(subnetMask, "Subnet Mask");

        defaultGateway = new TextField<String>();
        setFieldProperties(defaultGateway, "Default Gateway");

        dnsServer1 = new TextField<String>();
        setFieldProperties(dnsServer1, "DNS Server 1");

        dnsServer2 = new TextField<String>();
        setFieldProperties(dnsServer2, "DNS Server 2");

    }

    @Override 
    protected void validate() {

    }

    @Override
    protected void send() {

    }
}
