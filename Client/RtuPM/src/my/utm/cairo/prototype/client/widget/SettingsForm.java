package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextField;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

public class SettingsForm extends BaseFormWidget {

    private TextField<String> rtuId; 
    private TextField<String> serverURL; 

    public SettingsForm() { 
        super();

        rtuId = new TextField<String>();
        setFieldProperties(rtuId, "Remote Terminal ID", false, 2, false);

        // TODO: URL validation
        serverURL = new TextField<String>();
        setFieldProperties(serverURL, "Server Location", false, 3, false);

        postInitialize("settings-form");
    }

    private boolean hasValue(TextField<String> field, int len) {
        return field.getValue() != null && 
            field.getValue().length() > len;
    }

    @Override
    protected void send() {
        JSONObject obj = new JSONObject();

        obj.put("rtu_id", 
            new JSONString(rtuId.getValue()));
        obj.put("server_location", 
            new JSONString(serverURL.getValue()));

        sendJSONPost(obj);
    }
    @Override
    protected void validate() {
        submit.setEnabled(hasValue(rtuId, 1) && 
            hasValue(serverURL, 3));
    }
    @Override
    protected void assignInitialValue() {

        rtuId.setValue(getFieldValue("rtu_id"));
        serverURL.setValue(getFieldValue("server_location"));
    }
}
