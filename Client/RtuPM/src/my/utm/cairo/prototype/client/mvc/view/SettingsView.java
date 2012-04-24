package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.widget.AccountForm;
import my.utm.cairo.prototype.client.widget.DateTimeForm;
import my.utm.cairo.prototype.client.widget.SettingsForm;

public class SettingsView extends BaseWidgetView { 
    private SettingsForm settingsForm;
    private DateTimeForm dateTimeForm; 
    private AccountForm accountForm; 

    public SettingsView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {
        super.initialize();

        settingsForm = new SettingsForm();
        dateTimeForm = new DateTimeForm();
        accountForm = new AccountForm();
    }

    private void setupSettingsForm() {

        settingsForm.fetchInitialValue();
        container.setHeading("General Settings");
        container.add(settingsForm, center);
        container.setIconStyle("general-settings");

    }

    private void setupDateTimeForm() {

        dateTimeForm.fetchInitialValue();
        container.setHeading("Date and Time Settings");
        container.add(dateTimeForm, center);
        container.setIconStyle("date-icon");

    }

    private void setupAccountForm() {

        accountForm.fetchInitialValue();
        container.setHeading("Account Settings");
        container.add(accountForm, center);
        container.setIconStyle("user-icon");
    }

    @Override 
    protected void handleEvent(AppEvent e) {

        LayoutContainer wrapper = loadWrapper();

        EventType et = e.getType();

        if (et == AppEvents.NAV_SETTINGS) {

            setupSettingsForm();

        } else if (et == AppEvents.NAV_DATE_TIME) {

            setupDateTimeForm();

        } else if (et == AppEvents.NAV_ACCOUNT) {

            setupAccountForm();
        }

        attachContainer(wrapper);

    }
}
