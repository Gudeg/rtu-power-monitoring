public class FirmwareUploadDialog extends Dialog { 

    private FileUploadField fupload; 

    public FirmwareUploadDialog() {
        FormLayout layout = new FormLayout();
        layout.setLabelWidth(90);
        layout.setDefaultWidth(155);

        FormPanel form = new FormPanel();
        form.setLayout(false);
        form.setHeader("Firmware Upload");
        form.setEncoding(Encoding.MULTIPART);
        







