package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 创建社团申请返回参数
 * @author Yang on 5/1/2020.
 */
public class CmsClubsCreationsDTO {
    @ApiModelProperty(value = "申请id")
    private Integer id;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "理由")
    private String reason;

    @ApiModelProperty(value = "官方状态: 0 -> 非正式; 1 -> 正式;")
    private Integer officialState;

    @ApiModelProperty(value = "申请时间")
    private Date createAt;

    @ApiModelProperty(value = "申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;")
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

    public Integer getOfficialState() {
        return officialState;
    }

    public void setOfficialState(Integer officialState) {
        this.officialState = officialState;
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
