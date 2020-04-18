package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsQuitNotice implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer clubId;

    @ApiModelProperty(value = "退社日期")
    private Date qiutDate;

    @ApiModelProperty(value = "原因")
    private String readon;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Date getQiutDate() {
        return qiutDate;
    }

    public void setQiutDate(Date qiutDate) {
        this.qiutDate = qiutDate;
    }

    public String getReadon() {
        return readon;
    }

    public void setReadon(String readon) {
        this.readon = readon;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", clubId=").append(clubId);
        sb.append(", qiutDate=").append(qiutDate);
        sb.append(", readon=").append(readon);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}