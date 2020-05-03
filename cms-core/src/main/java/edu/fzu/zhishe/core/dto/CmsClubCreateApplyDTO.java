package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CmsClubCreateApplyDTO {

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "申请时间")
    private Date createAt;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "申请原因")
    private String reason;

    @ApiModelProperty(value = "申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;")
    private Integer state;

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
