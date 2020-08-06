package modal;

import java.util.List;

/**
 * Created by HP on 2017/7/14.
 */
public class YxtDepartment {
    private String ID;

    private String ParentID;


    private String OuName;

    private  String Description;


    private List<String> Users;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getOuName() {
        return OuName;
    }

    public void setOuName(String ouName) {
        OuName = ouName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<String> getUsers() {
        return Users;
    }

    public void setUsers(List<String> users) {
        Users = users;
    }
}
