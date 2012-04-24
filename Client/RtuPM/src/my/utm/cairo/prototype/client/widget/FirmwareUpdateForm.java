package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.FileUploadField;

import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.FormPanel.Method;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Window;

public class FirmwareUpdateForm extends BaseFormWidget {

    // TODO: Use editable grid on the next version to ease the user 
    //       input
    
    private TextField<String> firmwareName; 
    private NumberField version; 
    private FileUploadField fupload; 
    private TextArea comment; 

    private static final String targetURL = "http://" + 
        Window.Location.getHost() + 
        "/index.php/post/upload/firmwareupdate-form";

    public FirmwareUpdateForm() {
        super();

        firmwareName = new TextField<String>();
        setFieldProperties(firmwareName, "Firmware Name", true, 4, false);
        firmwareName.setName("firmware_name");

        version = new NumberField();
        setFieldProperties(version, "Version", true, 2, false);
        version.setName("version");

        fupload = new FileUploadField();
        fupload.setFieldLabel("Firmware Upload:");
        fupload.setAllowBlank(false);
        fupload.setName("userfile");
        form.add(fupload);

        comment = new TextArea();
        comment.setPreventScrollbars(true);
        comment.setHeight(200);
        comment.setName("comment");

        setFieldProperties(comment, "Firmware Info"); 

        form.setAction(targetURL);
        form.setEncoding(Encoding.MULTIPART);
        form.setMethod(Method.POST);

        postInitialize("firmwareupdate-form");
    }

    @Override
    protected void validate() {

        submit.setEnabled(true);

    }

    @Override 
    protected void send() {
        form.submit();
    }

    @Override 
    protected void assignInitialValue() {

        firmwareName.setValue(getFieldValue("firmware_name"));
        version.setValue(Integer.parseInt(getFieldValue("version")));
    }
}
