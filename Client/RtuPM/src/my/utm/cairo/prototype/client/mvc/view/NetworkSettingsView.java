package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.event.EventType;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.widget.NetworkForm;
import my.utm.cairo.prototype.client.widget.PortForwardingForm;

public class NetworkSettingsView extends BaseWidgetView { 
    private NetworkForm networkSettingsForm; 
    private PortForwardingForm portForwardingForm; 

    public NetworkSettingsView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {
        super.initialize();

        networkSettingsForm = new NetworkForm();
        portForwardingForm = new PortForwardingForm();
    }

    private void setupNetworkSettingsForm() {

        networkSettingsForm.fetchInitialValue();
        container.setHeading("Network Settings");
        container.add(networkSettingsForm, center);
        container.setIconStyle("net-icon");

    }

    private void setupPortForwardingForm() {
        
        portForwardingForm.fetchInitialValue();
        container.setHeading("Port Forwarding");
        container.add(portForwardingForm, center);
        container.setIconStyle("port-icon");

    }

    @Override 
    protected void handleEvent(AppEvent e) {

        LayoutContainer wrapper = loadWrapper();

        EventType et = e.getType();

        if (et == AppEvents.NAV_NET_SETTINGS) {

            setupNetworkSettingsForm();

        } else if (et == AppEvents.NAV_PORT_FORWARDING) {

            setupPortForwardingForm();

        }

        attachContainer(wrapper);
    }
}
