package edu.fzu.zhishe.core.dto;

public class CmsCertificationsParam {
    /**
     * clubId : 1
     * applicant : wangping
     * accessoryUrl : htttps://xxx/xxx.doc
     */

    private int clubId;
    private String reason;
    private String applicant;
    private String accessoryUrl;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}
