package my.utm.cairo.prototype.client.widget;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TimeField;

public class DateTimeForm extends BaseFormWidget { 

    private TimeField time;
    private DateField date; 
    private NumberField deleteLogInterval; 

    private static final String formName = "datetime-form";

    public DateTimeForm() {
        super();

        setFormAction(formName);

        time = new TimeField();
        time.setFieldLabel("Server Time");
        time.setAllowBlank(false);
        time.addKeyListener(getValidateKeyListener());
        form.add(time);

        date = new DateField();
        date.setFieldLabel("Server Date (URL)");
        date.setAllowBlank(false);
        date.addKeyListener(getValidateKeyListener());
        form.add(date);

        deleteLogInterval = new NumberField();
        deleteLogInterval.setFieldLabel("Delete Log Record After");
        deleteLogInterval.addKeyListener(getValidateKeyListener());
        form.add(deleteLogInterval);
    }

    @Override
    protected void validate() {
        submit.setEnabled(
            time.getValue() != null && 
            date.getValue() != null &&
            deleteLogInterval.getValue() != null
        );
    }

    @Override
    protected void send() {
        // TODO:format to json first then send the post request
    }
}
