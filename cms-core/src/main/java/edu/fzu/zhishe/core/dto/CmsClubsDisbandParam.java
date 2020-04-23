package edu.fzu.zhishe.core.dto;

/**
 *社团解散申请表单数据
 *
 * @author yang
 */
public class CmsClubsDisbandParam {
    /**
     * club_id : 10001
     * applicant : 张三
     * reason : 没为什么
     * accessory_url : https://xxx/xxx/xx.doc
     */

    private int club_id;
    private String applicant;
    private String reason;
    private String accessory_url;

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
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

    public String getAccessory_url() {
        return accessory_url;
    }

    public void setAccessory_url(String accessory_url) {
        this.accessory_url = accessory_url;
    }
}
