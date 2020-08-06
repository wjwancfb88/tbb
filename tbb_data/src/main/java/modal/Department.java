package modal;

import java.util.List;

/**
 * Created by HP on 2017/6/22.
 */
public class Department {

    private String id;

    private String parentId;

    private String name;

    private String weights;


    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeights() {
        return weights;
    }

    public void setWeights(String weights) {
        this.weights = weights;
    }

    private String department;

    private  String todepartment;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTodepartment() {
        return todepartment;
    }

    public void setTodepartment(String todepartment) {
        this.todepartment = todepartment;
    }
}
