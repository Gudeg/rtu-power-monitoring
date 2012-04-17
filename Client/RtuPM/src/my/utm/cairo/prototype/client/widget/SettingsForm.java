package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;

public class SettingsForm extends BaseFormWidget {

    private TextField<String> rtuId; 
    private TextField<String> serverURL; 

    private static final String formName = "settings-form";

    public SettingsForm() { 
        super();

        setFormAction(formName);

        rtuId = new TextField<String>();
        setFieldProperties(rtuId, "Remote Terminal ID");

        // TODO: URL validation
        serverURL = new TextField<String>();
        setFieldProperties(
            serverURL, "Server Location <URL>");
    }

    @Override
    protected void send() {
        // TODO: format to json first then send the post request
    }

    private boolean hasValue(TextField<String> field, int len) {
        return field.getValue() != null && 
            field.getValue().length() > len;
    }

    @Override
    protected void validate() {
        submit.setEnabled(hasValue(rtuId, 7) && 
            hasValue(serverURL, 12));
    }
}
