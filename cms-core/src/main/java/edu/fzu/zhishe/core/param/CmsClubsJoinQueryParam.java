package edu.fzu.zhishe.core.param;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsJoinQueryParam {
    /**
     * id : 1
     * applicant : wangs
     * reason : 锻炼自己
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */

    private Integer id;
    private String applicant;
    private String reason;
    private String createAt;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
