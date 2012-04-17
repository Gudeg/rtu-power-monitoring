package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;

import com.google.gwt.user.client.Window;

public class AccountForm extends BaseFormWidget {

    private TextField<String> firstName; 
    private TextField<String> lastName; 
    private TextField<String> email; 
    private TextField<String> password;
    private TextField<String> confirmPassword; 

    private static final String formName = "account-form";

    public AccountForm() {
        super();

        setFormAction(formName);
        Window.alert(Window.Location.getHost() + "/" + formActionUrl);

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
    }

    @Override 
    protected void validate() {

        submit.setEnabled(passwordMatch());

    }
}
