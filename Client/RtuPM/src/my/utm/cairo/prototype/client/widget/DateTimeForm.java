package my.utm.cairo.prototype.client.widget;

import java.util.Calendar;
import java.util.Date;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;

import com.extjs.gxt.ui.client.util.IconHelper;

import com.extjs.gxt.ui.client.widget.button.Button;

import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TimeField;

import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.URL;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;

public class DateTimeForm extends BaseFormWidget { 

    private TimeField time;
    private DateField date; 
    private NumberField deleteLogInterval; 

    private LabelField serverTime;
    private LabelField timeZone; 
    private LabelField lastUpdate; 

    public DateTimeForm() {
        super(true);

        serverTime = new LabelField();
        serverTime.setFieldLabel("Current Server Time: ");

        timeZone = new LabelField();
        timeZone.setFieldLabel("Time Zone: ");

        lastUpdate = new LabelField();
        lastUpdate.setFieldLabel("Last Time Update: ");
        
        form.add(serverTime);
        form.add(timeZone);
        form.add(lastUpdate);

        Button autoTimeSync = new Button("Time Sync");
        SelectionListener<ButtonEvent> autoSyncListener = 
            new SelectionListener<ButtonEvent>() {

            public void componentSelected(ButtonEvent e) {

                send();

            }
        };

        autoTimeSync.addSelectionListener(autoSyncListener);
        autoTimeSync.setIcon(IconHelper.createStyle("sync-button"));

        form.setButtonAlign(HorizontalAlignment.LEFT);
        form.addButton(autoTimeSync);

        postInitialize("datetime-form");
        Timer t = new Timer() {
            @Override 
            public void run() {
                DateTimeForm.this.fetchInitialValue();
            }
        };
        t.scheduleRepeating(1000);
    }

    private void defaultTimeSync() {

    }

    @Override
    protected void validate() {
        submit.setEnabled(
            time.getValue() != null && 
            date.getValue() != null //&&
            //deleteLogInterval.getValue() != null
        );
    }

    @Override
    protected void send() {

        JSONObject obj = new JSONObject();

        Date now = new Date();
        obj.put("unix_time", new JSONString(now.getTime() + ""));
        obj.put("timezone", new JSONString(plusOrMinusGMT(now)));
        obj.put("last_update", new JSONString(now.toString())); 

        serverTime.setText(now.toString());
        timeZone.setText("GMT " + plusOrMinusGMT(now));
        lastUpdate.setText(now.toString()); 

        sendJSONPost(obj);
    }

    private String plusOrMinusGMT(Date date) {

        int timezone_offset = date.getTimezoneOffset() / -60;

        if (timezone_offset >= 0) {
            return "+" + timezone_offset;
        } else {
            return "-" + timezone_offset;
        }
    }

    @Override 
    protected void assignInitialValue() {

        serverTime.setText(getFieldValue("server_time"));
        timeZone.setText(getFieldValue("timezone"));
        lastUpdate.setText(getFieldValue("last_update"));
    }
}
