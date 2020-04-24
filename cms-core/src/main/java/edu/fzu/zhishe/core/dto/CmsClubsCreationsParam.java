package edu.fzu.zhishe.core.dto;
/**
 *社团创建申请表单数据
 *
 * @author yang
 */
public class CmsClubsCreationsParam {

    /**
     * clubName : test
     * applicant : 张三
     * reason : make friends
     * type : 运动类
     * officialState : true
     * accessoryUrl : https://xxx/xxx/xx.doc
     */

    private String clubName;
    private String applicant;
    private String reason;
    private String type;
    private boolean officialState;
    private String accessoryUrl;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficialState() {
        return officialState;
    }

    public void setOfficialState(boolean officialState) {
        this.officialState = officialState;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}
