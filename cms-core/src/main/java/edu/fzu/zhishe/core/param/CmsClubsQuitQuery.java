package edu.fzu.zhishe.core.param;

import java.util.Date;

public class CmsClubsQuitQuery {
    /**
     * id : 1
     * username : wangs
     * reason : 没意思
     * createAt : 2018-04-19 18:14:12
     */

    private Integer id;
    private String username;
    private String reason;
    private String createAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
