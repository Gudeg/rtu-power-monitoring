package my.utm.cairo.prototype.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseTreeModel;

@SuppressWarnings("serial")
public class Folder extends BaseTreeModel implements Serializable {

    public Folder() {}

    public Folder(String name) {
        set("name", name);
    }

    public Folder(String name, BaseTreeModel[] children) {
        this(name);
        for (int i = 0; i < children.length; i++) {
            add(children[i]);
        }
    }

    public String getName() {
        return (String) get("name");
    }
}
