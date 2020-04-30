package edu.fzu.zhishe.core.dto;

import java.util.Date;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsJoinDTO {
    /**
     * id : 1
     * applicant : wangs
     * reason : 锻炼自己
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */

    private int id;
    private String applicant;
    private String reason;
    private Date createAt;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
