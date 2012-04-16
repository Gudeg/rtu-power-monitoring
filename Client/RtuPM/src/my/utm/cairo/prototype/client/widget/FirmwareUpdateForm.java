package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.FileUploadField;

import com.extjs.gxt.ui.client.widget.form.FormPanel.Encoding;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;

public class FirmwareUpdateForm extends BaseFormWidget {

    // TODO: Use editable grid on the next version to ease the user 
    //       input
    
    private TextField<String> firmwareName; 
    private NumberField version; 
    private FileUploadField fupload; 
    private TextArea comment; 

    public FirmwareUpdateForm() {
        super();

        form.setEncoding(Encoding.MULTIPART);

        firmwareName = new TextField<String>();
        setFieldProperties(firmwareName, "Firmware Name");

        version = new NumberField();
        setFieldProperties(version, "Version");

        fupload = new FileUploadField();
        setFieldProperties(fupload, "Firmware Upload");

        comment = new TextArea();
        comment.setPreventScrollbars(true);
        comment.setHeight(200);
        setFieldProperties(comment, "Firmware Info"); 
    }

    @Override
    protected void send() {

    }

    @Override 
    protected void validate() {

    }
}
