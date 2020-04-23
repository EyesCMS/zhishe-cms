package edu.fzu.zhishe.core.dto;

public class CmsClubsCreationsParam {

    /**
     * club_name : test
     * applicant : 张三
     * reason : make friends
     * type : 运动类
     * official_state : true
     * accessory_url : https://xxx/xxx/xx.doc
     */

    private String club_name;
    private String applicant;
    private String reason;
    private String type;
    private boolean official_state;
    private String accessory_url;

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficial_state() {
        return official_state;
    }

    public void setOfficial_state(boolean official_state) {
        this.official_state = official_state;
    }

    public String getAccessory_url() {
        return accessory_url;
    }

    public void setAccessory_url(String accessory_url) {
        this.accessory_url = accessory_url;
    }
}
