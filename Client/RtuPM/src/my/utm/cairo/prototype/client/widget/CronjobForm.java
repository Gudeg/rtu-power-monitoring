package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextArea;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Window;

public class CronjobForm extends BaseFormWidget {

    // TODO: Use editable grid on the next version to ease the user 
    //       input

    private TextArea cronjobConfig; 
    private static final String formName = "cronjob-form";

    public CronjobForm() {
        super();

        setSubmitUrl(formName);
        cronjobConfig = new TextArea(); 
        cronjobConfig.setPreventScrollbars(true);
        cronjobConfig.setHeight(200);
        setFieldProperties(cronjobConfig, "Scheduler (Crontab)");

    }

    @Override
    protected void send() {
        JSONObject obj = new JSONObject();

        obj.put("crontab", new JSONString(cronjobConfig.getValue()));

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

    @Override 
    protected void validate() {

        submit.setEnabled(true);

    }

}
