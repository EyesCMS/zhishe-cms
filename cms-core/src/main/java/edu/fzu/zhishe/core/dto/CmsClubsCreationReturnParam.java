package edu.fzu.zhishe.core.dto;

public class CmsClubsCreationReturnParam {
    /**
     * id : 1
     * clubName : 羽毛球社
     * createAt : 2018-04-19 18:14:12
     * applicant : 张三
     * reason : 交朋友
     * accessoryUrl : http://xxx/xxx/xx.doc
     * state : 0
     */

    private int id;
    private String clubName;
    private String createAt;
    private String applicant;
    private String reason;
    private String accessoryUrl;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
