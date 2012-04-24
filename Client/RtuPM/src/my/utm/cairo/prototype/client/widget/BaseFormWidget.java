package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

import com.extjs.gxt.ui.client.util.IconHelper;

import com.extjs.gxt.ui.client.widget.ContentPanel;

import com.extjs.gxt.ui.client.widget.button.Button;

import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;

import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

import com.google.gwt.user.client.Window;

abstract public class BaseFormWidget extends ContentPanel { 

    protected FormPanel form; 
    protected Button reset; 
    protected Button submit; 
    protected String targetURL;
    protected String submitUrl = "post/form/"; 
    protected String getUrl = "get/form/";

    protected JSONObject initialValue; 

    private int minLength = 6;
    private boolean isPassword = false;
    private boolean allowBlank = false;

    protected RequestBuilder rb; 

    protected String formName; 

    public BaseFormWidget() {
        initForm(); 
        initButton();
        add(form);
    }

    public BaseFormWidget(boolean noButton) {
        if (noButton) {
            initForm();
        } else {
            initForm();
            initButton();
        }
        add(form);
    }

    public void initForm() {
        setLayout(new FitLayout());
        setBodyBorder(false);
        setHeaderVisible(false);
        setBodyStyle("padding: 20px; background: none");

        form = new FormPanel();

        FormLayout layout = new FormLayout();
        layout.setLabelWidth(150);
        layout.setDefaultWidth(500);

        form.setLayout(layout); 
        form.setBodyBorder(false);
        form.setHeaderVisible(false);
    }

    public void postInitialize(final String formName) {
        setSubmitUrl(formName);
    }

    public void initButton() {

        reset = new Button("Reset");
        reset.addSelectionListener(new SelectionListener<ButtonEvent>(){
            @Override
            public void componentSelected(ButtonEvent e) {
                form.reset();
            }
        });
        
        submit = new Button("Submit");
        submit.setIcon(IconHelper.createStyle("save-button"));
        submit.disable();
        submit.addSelectionListener(new SelectionListener<ButtonEvent>() {
            public void componentSelected(ButtonEvent e) {
                onSubmit();
            }
        });

        form.addButton(reset);
        form.addButton(submit);
        form.setButtonAlign(HorizontalAlignment.CENTER);
    }

    public KeyListener getValidateKeyListener() {

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent e) {
                validate();
            }
        };

        return keyListener;
    }


    protected void onSubmit() {
        submit.disable();
        send();
    }

    protected <T extends TextField> void setFieldProperties(T field, 
        String label, boolean allowBlank, int minLength, 
        boolean isPassword) {

        field.setFieldLabel(label);
        field.setAllowBlank(allowBlank);
        field.addKeyListener(getValidateKeyListener());
        field.setMinLength(minLength);
        form.add(field);

    }

    protected <T extends TextField> void setFieldProperties(T field, 
        String label) {

        setFieldProperties(field, label, allowBlank, minLength, 
            isPassword);

    }

    protected <T extends TextField> void setFieldProperties(T field, 
        String label, boolean isPwd) {

        setFieldProperties(field, label, allowBlank, minLength, isPwd);
    }

    private void setSubmitUrl(String formName) {
        submitUrl = URL.encode("http://" + Window.Location.getHost() + 
            "/index.php/" + submitUrl + formName);
        getUrl = URL.encode("http://" + Window.Location.getHost() + 
            "/index.php/" + getUrl + formName);
    }

    protected RequestCallback getDefaultRequestCallback(
        final String success, final String failure) {

        return new RequestCallback() {

            @Override 
            public void onError(Request req, Throwable e) {

                Window.alert("Failed to connect: " + e.toString());

            }

            @Override
            public void onResponseReceived(Request req, Response res) {
                if (200 == res.getStatusCode()) {

                    Window.alert(success);

                } else { 

                    Window.alert(failure);

                }
            }

        };
    }

    protected RequestCallback getDefaultRequestCallback() { 
        return getDefaultRequestCallback(
            "Form post success!", "Reply: Error");
    }

    protected abstract void validate();
    protected abstract void send(); 
    protected abstract void assignInitialValue();

    protected boolean sendJSONPost(JSONObject obj) {

        rb = new RequestBuilder(RequestBuilder.POST, submitUrl); 
        rb.setHeader("content-type", 
            "application/x-www-form-urlencoded");

        try { 

            String data = URL.encode("data=" + obj.toString());
            rb.sendRequest(data, getDefaultRequestCallback());
            return true; 

        } catch (RequestException e) { 
            
            Window.alert("Post error: " + e.toString());
            return false;

        }
    }

    public void fetchInitialValue() {
        RequestBuilder fetch = new RequestBuilder(RequestBuilder.GET, 
            getUrl);

        try { 

            fetch.sendRequest(null, new RequestCallback() {

                @Override 
                public void onError(Request req, Throwable e) {

                    Window.alert("Failed to connect: " + e.toString());

                }

                @Override
                public void onResponseReceived(Request req, Response res) {
                    if (200 == res.getStatusCode()) {

                        initialValue = 
                            JSONParser.parse(res.getText()).isObject();

                        assignInitialValue();

                    } else { 

                        Window.alert("Error retrieving form JSON Value");
                        initialValue = null;

                    }
                }
            });

        } catch (RequestException e) {

            Window.alert("Get error: " + e.toString());

        }
    }

    protected String getFieldValue(String key) {

        return initialValue.get(key).toString()
            .replace("\"", "")
            .replace("\\n", "\n")
            .replace("\\t", "\t");
    }
}
