package my.utm.cairo.prototype.client.widget;

import java.io.UnsupportedEncodingException;

import java.math.BigInteger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;

import com.extjs.gxt.ui.client.mvc.Dispatcher;

import com.extjs.gxt.ui.client.util.IconHelper;

import com.extjs.gxt.ui.client.widget.Dialog;

import com.extjs.gxt.ui.client.widget.button.Button;

import com.extjs.gxt.ui.client.widget.form.TextField;

import com.extjs.gxt.ui.client.widget.layout.FormLayout;

import com.extjs.gxt.ui.client.widget.Status;

import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

import my.utm.cairo.prototype.client.AppEvents;

public class LoginDialog extends Dialog {
    protected TextField<String> userid; 
    protected TextField<String> password; 
    protected Button reset;
    protected Button login;
    protected Status status;

    private String pwd;


    public LoginDialog() {
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(90);
        layout.setDefaultWidth(155);
        setLayout(layout);

        setButtonAlign(HorizontalAlignment.LEFT);
        setButtons("");
        setIcon(IconHelper.createStyle("user"));
        setHeading("RTU Power Monitoring Login");
        setModal(true);
        setBodyBorder(true);
        setBodyStyle("padding: 8px; background: none");
        setWidth(300);
        setResizable(false);

        KeyListener keyListener = new KeyListener() {
            public void componentKeyUp(ComponentEvent event) {
                validate();
            }
        };

        userid = new TextField<String>();
        userid.setMinLength(4);
        userid.setFieldLabel("User ID");
        userid.addKeyListener(keyListener);
        add(userid);

        password = new TextField<String>();
        password.setMinLength(6);
        password.setFieldLabel("Password");
        password.addKeyListener(keyListener);
        password.setPassword(true);
        add(password);

        setFocusWidget(userid);
    }

    @Override 
    protected void createButtons() {
        super.createButtons();
        status = new Status();
        status.setBusy("Please wait...");
        status.hide();
        status.setAutoWidth(true);

        getButtonBar().add(status);
        getButtonBar().add(new FillToolItem());

        reset = new Button("Reset");
        reset.addSelectionListener(new SelectionListener<ButtonEvent>(){
            @Override
            public void componentSelected(ButtonEvent ce) {
                userid.reset();
                password.reset();
                validate();
                userid.focus();
            }
        });

        login = new Button("Login");
        login.disable();
        login.addSelectionListener(new SelectionListener<ButtonEvent>(){
            public void componentSelected(ButtonEvent e) {
                onSubmit();
            }
        });
        addButton(reset);
        addButton(login);
    }

    protected void onSubmit() {
        status.show();
        getButtonBar().disable();

        try { 
            MessageDigest md = MessageDigest.getInstance("SHA");
            String tmp = password.getValue();
            byte[] b = tmp.getBytes("UTF-8");
            b = md.digest(b);
            BigInteger bI = new BigInteger(1, b);
            String hashed = bI.toString(16);

            while (hashed.length() < 32) { 
                hashed = "0"+hashed;
            }
            pwd = hashed;

            Window.alert(pwd);

        } catch (NoSuchAlgorithmException e) {

            Dispatcher.get().dispatch(AppEvents.ERROR, e);

        } catch (UnsupportedEncodingException e) {

            Dispatcher.get().dispatch(AppEvents.ERROR, e);

        }

        Timer t = new Timer() {

            @Override
            public void run() {
                LoginDialog.this.hide();
            }
        };
        t.schedule(2000);
    }

    protected boolean hasValue(TextField<String> field) {
        return field.getValue() != null && 
            field.getValue().length() >= 4;
    }

    protected void validate() {
        login.setEnabled(hasValue(userid) && hasValue(password) &&
            password.getValue().length() > 3);
    }

    public String getUserId() {
        return userid.getValue(); 
    }

    public String getPassword() {
        return pwd;
    }
}
