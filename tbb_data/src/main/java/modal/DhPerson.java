package modal;

public class DhPerson {
    private String id;
    private String gender;
    private String openId;
    private String jobTitle;
    private String jobNo;
    private String name;
    private String globalId;
    private String isAdmin;
    private String department;
    private String orgId;
    private String status;

    @Override
    public String toString() {
        return "DhPerson{" +
                "id='" + id + '\'' +
                ", gender='" + gender + '\'' +
                ", openId='" + openId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobNo='" + jobNo + '\'' +
                ", name='" + name + '\'' +
                ", globalId='" + globalId + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", department='" + department + '\'' +
                ", orgId='" + orgId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
