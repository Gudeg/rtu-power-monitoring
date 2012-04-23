package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.Registry;

import com.extjs.gxt.ui.client.Style.LayoutRegion;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.event.WindowEvent;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;

import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HtmlContainer;
import com.extjs.gxt.ui.client.widget.LayoutContainer;

import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.Viewport;

import com.extjs.gxt.ui.client.widget.button.Button;

import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;

import com.google.gwt.user.client.Window;

import com.google.gwt.user.client.ui.RootPanel;

import my.utm.cairo.prototype.client.AppEvents;

import my.utm.cairo.prototype.client.mvc.controller.AppController;

import my.utm.cairo.prototype.client.mvc.model.User;

import my.utm.cairo.prototype.client.widget.LoginDialog;

public class AppView extends View { 

    public static final String WEST_PANEL = "1";
    public static final String VIEWPORT = "2"; 
    public static final String CENTER_PANEL = "4";
    public static final String USER = "8";

    private static final User user = new User();

    private Viewport vport; 
    private ContentPanel west; 
    private LayoutContainer center; 

    private static LoginDialog dialog;

    public AppView(AppController controller) { 
        super(controller);
    }

    @Override
    protected void initialize() {
        Registry.register(USER, user);
    }

    private void onLogin() {
        // Maybe change to onLogin
        dialog = new LoginDialog();
        dialog.setClosable(false);

        // TODO: Check the security of this 
        dialog.addListener(Events.Hide, new Listener<WindowEvent>() {
            @Override
            public void handleEvent(WindowEvent e) { 
                
                AppView.user.set("password", 
                    AppView.dialog.getPassword());

                AppView.user.set("userid", 
                    AppView.dialog.getUserId());

                Dispatcher.get().dispatch(AppEvents.AUTH);
            }
        });

        dialog.show();
    }


    private void createNorth() {
        StringBuffer sb = new StringBuffer();
        sb.append("<div id='rtu-header' class='x-small-editor'>");
        sb.append("<div id='rtu-theme'></div>");
        sb.append("<div id='rtu-title'>CAIRO: Power Monitoring System");
        sb.append("</div></div>");

        HtmlContainer northPanel = new HtmlContainer(sb.toString());
        northPanel.setStateful(false);
        
        BorderLayoutData ndata = new BorderLayoutData(
            LayoutRegion.NORTH, 33);

        ndata.setMargins(new Margins());
        vport.add(northPanel, ndata);
    }

    private void createWest() {
        //TODO: Logout functionality 
       
        BorderLayoutData wdata = new BorderLayoutData(LayoutRegion.WEST,
            200, 150, 350);
        wdata.setMargins(new Margins(5, 0, 5, 5));

        west = new ContentPanel();
        west.setBodyBorder(false); 
        west.setLayout(new AccordionLayout());
        west.setLayoutOnChange(true);
        west.setHeading("RTU Power Monitoring");

        final ToolBar toolbar = new ToolBar(); 

        final Button logOut = new Button("Log Out");
        logOut.setIcon(IconHelper.createStyle("logout"));

        ToolTipConfig logOutToolTipConfig = new ToolTipConfig();
        logOutToolTipConfig.setTitle("Logout"); 
        logOutToolTipConfig.setText("Exit from the application");

        logOut.setToolTip(logOutToolTipConfig);

        logOut.addSelectionListener(new SelectionListener<ButtonEvent>(){
            @Override 
            public void componentSelected(ButtonEvent e) {
                Dispatcher.get().dispatch(AppEvents.LOGOUT);
            }
        });

        toolbar.add(logOut);
        west.setBottomComponent(toolbar);

        vport.add(west, wdata); 
    }

    private void createCenter() { 
        center = new LayoutContainer();
        center.setLayout(new FitLayout());

        BorderLayoutData cdata = new BorderLayoutData(LayoutRegion.CENTER);
        cdata.setMargins(new Margins(5, 5, 5, 5));

        vport.add(center, cdata);
    }

    private void initUI() {
        vport = new Viewport(); 
        vport.setLayout(new BorderLayout());

        createNorth();
        createWest();
        createCenter();

        Registry.register(VIEWPORT, vport);
        Registry.register(WEST_PANEL, west);
        Registry.register(CENTER_PANEL, center);

        RootPanel.get().add(vport);
    }

    @Override
    protected void handleEvent(AppEvent e) { 
        if (e.getType() == AppEvents.INIT) {
            initUI();
        } else if (e.getType() == AppEvents.LOGIN) {
            onLogin();
        }
    }
}


