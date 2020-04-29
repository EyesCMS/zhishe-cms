package edu.fzu.zhishe.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *社团解散申请列表返回数据
 *
 * @author yang
 */
public class CmsClubsDisbandReturnParam {
    /**
     * clubName : 羽毛球社
     * createAt : 2018-04-19 18:14:12
     * applicant : 张三
     * reason : 本社团没有存在意义
     * state : 0
     */

    private int id;
    private String clubName;
    private String createAt;
    private String applicant;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
