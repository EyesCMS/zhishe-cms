package edu.fzu.zhishe.core.dto;

import java.util.Date;

public class CmsClubsQuitDTO {
    /**
     * id : 1
     * username : wangs
     * reason : 没意思
     * createAt : 2018-04-19 18:14:12
     */

    private int id;
    private String username;
    private String reason;
    private Date createAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
