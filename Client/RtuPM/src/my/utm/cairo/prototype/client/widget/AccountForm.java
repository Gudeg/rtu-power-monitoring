package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Window;

public class AccountForm extends BaseFormWidget {

    private TextField<String> firstName; 
    private TextField<String> lastName; 
    private TextField<String> email; 
    private TextField<String> password;
    private TextField<String> confirmPassword; 
    private TextField<String> userId;

    private RequestCallback rcb; 
    private RequestBuilder rb; 

    private static final String formName = "account-form";

    public AccountForm() {
        super();

        userId = new TextField<String>();
        setFieldProperties(userId, "User ID", true, 4, false);

        firstName = new TextField<String>();
        setFieldProperties(firstName, "First Name");

        lastName = new TextField<String>();
        setFieldProperties(lastName, "Last Name", true, 4, false);

        email = new TextField<String>();
        setFieldProperties(email, "Email Address");

        password = new TextField<String>();
        setFieldProperties(password, "Password", true);
        password.setPassword(true);

        confirmPassword = new TextField<String>();
        setFieldProperties(confirmPassword, "Confirm Password", true);
        confirmPassword.setPassword(true);

        postInitialize("account-form");
    }

    private boolean passwordMatch() {

        if ( !password.getValue().equals("") && 
             !confirmPassword.getValue().equals("") && 
             password.getValue().equals(confirmPassword.getValue()) ) {

            return true; 

        } else { 

            return false; 

        }
    }

    @Override
    protected void send() {

        JSONObject obj = new JSONObject();

        obj.put("userid", new JSONString(userId.getValue()));
        obj.put("firstname", new JSONString(firstName.getValue()));
        obj.put("lastName", new JSONString(lastName.getValue()));
        obj.put("email", new JSONString(email.getValue()));
        obj.put("password", new JSONString(password.getValue()));

        sendJSONPost(obj);
    }

    @Override 
    protected void validate() {

        submit.setEnabled(passwordMatch());
    }

    @Override 
    protected void assignInitialValue() {

        userId.setValue(getFieldValue("userid"));
        firstName.setValue(getFieldValue("firstname"));
        lastName.setValue(getFieldValue("lastName"));
        email.setValue(getFieldValue("email"));

    }
}
