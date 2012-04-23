package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Window;

public class NetworkForm extends BaseFormWidget { 

    private TextField<String> ipAddress; 
    private TextField<String> subnetMask; 
    private TextField<String> defaultGateway; 
    private TextField<String> dnsServer1; 
    private TextField<String> dnsServer2; 

    private static final String formName = "network-form";

    public NetworkForm() {
        super();

        setSubmitUrl(formName);

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

        submit.setEnabled(true);

    }

    @Override
    protected void send() {

        JSONObject obj = new JSONObject();

        obj.put("server_ip_address", 
            new JSONString(ipAddress.getValue()));

        obj.put("subnet_mask", 
            new JSONString(subnetMask.getValue()));

        obj.put("default_gateway", 
            new JSONString(defaultGateway.getValue()));

        obj.put("dns_server1", 
            new JSONString(defaultGateway.getValue()));

        obj.put("dns_server2", 
            new JSONString(defaultGateway.getValue()));

        rb = new RequestBuilder(RequestBuilder.POST, submitUrl);
        rb.setHeader("content-type", 
                "application/x-www-form-urlencoded");
        
        try { 

            String data = URL.encode("data=" + obj.toString());
            rb.sendRequest(data, getDefaultRequestCallback());

        } catch (RequestException e) { 

            Window.alert("Post error: " + e.toString());

        }
    }
}
