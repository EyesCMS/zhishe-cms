package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsJoinQuery {
    /**
     * id : 1
     * applicant : wangs
     * reason : 锻炼自己
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */
    @ApiModelProperty(value = "申请id")
    private Integer id;
    @ApiModelProperty(value = "申请人")
    private String applicant;
    @ApiModelProperty(value = "理由")
    private String reason;
    @ApiModelProperty(value = "创建时间")
    private String createAt;
    @ApiModelProperty(value = "状态")
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
