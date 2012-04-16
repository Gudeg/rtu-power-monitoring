package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.Style.LayoutRegion;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;

import com.extjs.gxt.ui.client.util.Margins;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

abstract public class BaseWidgetView extends View {
    protected ContentPanel container; 

    protected BorderLayoutData center; 
    protected BorderLayoutData south; 

    public BaseWidgetView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void initialize() {
        container = new ContentPanel();

        BorderLayout layout = new BorderLayout();
        layout.setEnableState(false);

        container.setLayout(layout);

        center = new BorderLayoutData(LayoutRegion.CENTER);
        south = new BorderLayoutData(LayoutRegion.SOUTH, .5f, 200, 1000);
        south.setSplit(true);
        south.setMargins(new Margins(5, 0, 0, 0));
    }

    @Override
    protected void handleEvent(AppEvent e) {} 

    protected LayoutContainer loadWrapper() {

        LayoutContainer wrapper = (LayoutContainer) 
            Registry.get(AppView.CENTER_PANEL);

        wrapper.removeAll();
        container.removeAll();

        return wrapper;

    }

    protected void attachContainer(LayoutContainer wrapper) {
        wrapper.add(container);
        wrapper.layout();
    }
}
