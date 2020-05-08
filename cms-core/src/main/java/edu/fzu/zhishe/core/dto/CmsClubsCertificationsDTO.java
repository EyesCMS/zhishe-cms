package edu.fzu.zhishe.core.dto;

import java.util.Date;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsCertificationsDTO {
    /**
     * id:1
     * clubName : 羽毛球社
     * createAt : 2018-04-19 18:14:12
     * applicant : 张三
     * reason : 认证牛逼一点
     * state : 0
     */

    private int id;
    private String clubName;
    private Date createAt;
    private String reason;
    private int state;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
