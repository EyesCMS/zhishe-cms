package edu.fzu.zhishe.core.dto;

/**
 *社团解散申请表单数据
 *
 * @author yang
 */
public class CmsClubsDisbandParam {
    /**
     * clubId : 10001
     * applicant : 张三
     * reason : 没为什么
     * accessoryUrl : https://xxx/xxx/xx.doc
     */

    private int clubId;
    private String applicant;
    private String reason;
    private String accessoryUrl;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
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
}
