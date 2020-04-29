package edu.fzu.zhishe.core.dto;

public class CmsCertificationsReturnParam {
    /**
     * id : 1
     * clubName : 羽毛球社
     * applicant : 张三
     * reason : 没为什么
     * createAt : 2018-04-19 18:14:12
     * state : 1
     */

    private int id;
    private String clubName;
    private String applicant;
    private String reason;
    private String createAt;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
