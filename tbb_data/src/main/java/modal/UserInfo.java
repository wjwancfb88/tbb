package modal;

/**
 * Created by HP on 2017/7/4.
 * 云学堂同步数据用户实体类
 */
public class UserInfo {

    private String id;

    private String UserName;

    private String Password;

    private String CnName;


    private String Sex;

    private String Mobile;

    private String Mail;


    private String OrgOuCode;


    private String PositionNo;

    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCnName() {
        return CnName;
    }

    public void setCnName(String cnName) {
        CnName = cnName;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getOrgOuCode() {
        return OrgOuCode;
    }

    public void setOrgOuCode(String orgOuCode) {
        OrgOuCode = orgOuCode;
    }

    public String getPositionNo() {
        return PositionNo;
    }

    public void setPositionNo(String positionNo) {
        PositionNo = positionNo;
    }
}
