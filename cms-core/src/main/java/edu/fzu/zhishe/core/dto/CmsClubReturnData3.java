package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CmsClubReturnData3 {

    @ApiModelProperty(value = "用户 id")
    private Integer userId;

    @ApiModelProperty(value = "社团 id")
    private Integer clubId;

    @ApiModelProperty(value = "社团 name")
    private String clubName;

    @ApiModelProperty(value = "申请原因")
    private String reason;

    @ApiModelProperty(value = "申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;")
    private Integer state;

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

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
