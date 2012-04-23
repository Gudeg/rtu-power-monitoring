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
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import com.google.gwt.user.client.Window;

abstract public class BaseFormWidget extends ContentPanel { 

    protected FormPanel form; 
    protected Button reset; 
    protected Button submit; 
    protected String targetURL;
    protected String submitUrl = "post/form/"; 

    private int minLength = 6;
    private boolean isPassword = false;
    private boolean allowBlank = false;

    private RequestCallback rcb; 
    protected RequestBuilder rb; 

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

    protected void setSubmitUrl(String formName) {
        submitUrl = URL.encode("http://" + Window.Location.getHost() + 
            "/index.php/" + submitUrl + formName);
    }

    protected RequestCallback getDefaultRequestCallback() { 

        rcb = new RequestCallback() {

            @Override 
            public void onError(Request req, Throwable e) {

                Window.alert("Failed to connect: " + e.toString());

            }

            @Override
            public void onResponseReceived(Request req, Response res) {
                if (200 == res.getStatusCode()) {

                    Window.alert("Form post success!");

                } else { 

                    Window.alert("Reply: Error");

                }
            }

        };

        return rcb;
    }

    protected abstract void validate();
    protected abstract void send(); 
}
