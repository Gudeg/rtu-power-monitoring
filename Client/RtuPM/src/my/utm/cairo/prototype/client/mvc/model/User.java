package my.utm.cairo.prototype.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModelData;

@SuppressWarnings("serial")
public class User extends BaseModelData implements Serializable { 
    // fields: userid, email, password, firstname, lastname, permission, 
    //         group, comment

    public User() {}

    public User(int userid, String email, String fname, String lname, 
        int permission, String pwd, int grp, String comment) {

        set("userid", userid);
        set("email", email);
        set("firstname", fname);
        set("lastname", lname);
        set("permission", permission);
        set("password", pwd);
        set("group", grp);
        set("comment", comment);
    }

    public int getUserId() { 
        return (Integer) get("userid");
    }

    public String getEmail() {
        return (String) get("email");
    }

    public String getFirstName() {
        return (String) get("firstname");
    }

    public String getLastName() {
        return (String) get("lastname");
    }

    public int getPermission() {
        return (Integer) get("permission");
    }

    public String getPassword() { 
        return (String) get("password");
    }

    public int getGroup() {
        return (Integer) get("group");
    }

    public String getComment() {
        return (String) get("comment");
    }

    public String toString() {
        return (String) get("email");
    }
}
