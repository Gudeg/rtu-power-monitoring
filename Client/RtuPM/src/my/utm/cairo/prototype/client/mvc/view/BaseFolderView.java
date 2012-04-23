package my.utm.cairo.prototype.client.mvc.view;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.View;

import com.extjs.gxt.ui.client.store.TreeStore;

import com.extjs.gxt.ui.client.util.IconHelper;

import com.extjs.gxt.ui.client.widget.treepanel.TreePanel;

import my.utm.cairo.prototype.client.mvc.model.Folder;

public class BaseFolderView extends View { 
    protected TreeStore<Folder> store; 
    protected TreePanel<Folder> tree; 

    public BaseFolderView(Controller controller) {
        super(controller);
    }

    @Override 
    protected void handleEvent(AppEvent e) {}

    protected void setupTree(Folder... folders){
        store = new TreeStore<Folder>();
        
        for (int i = 0; i < folders.length; i++) {
            store.add(folders[i], false);
        }

        tree = new TreePanel<Folder>(store);
        tree.getStyle().setLeafIcon(IconHelper.createStyle("cog"));
        tree.setDisplayProperty("name");
        tree.setAutoSelect(true);
        tree.setWidth(300);
        tree.setHeight(300);
    }
}
