package edu.fzu.zhishe.core.dto;

import java.util.Date;

public class CmsMyClubCertificationDTO {
    /**
     * id : 1
     * reason : 书法社
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */

    private int id;
    private String reason;
    private Date createAt;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
