package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.TextArea;

public class CronjobForm extends BaseFormWidget {

    // TODO: Use editable grid on the next version to ease the user 
    //       input

    private TextArea cronjobConfig; 
    private static final String formName = "cronjob-form";

    public CronjobForm() {
        super();

        cronjobConfig = new TextArea(); 
        cronjobConfig.setPreventScrollbars(true);
        cronjobConfig.setHeight(200);
        setFieldProperties(cronjobConfig, "Scheduler (Crontab)");

    }

    @Override
    protected void send() {

    }

    @Override 
    protected void validate() {

    }
}
