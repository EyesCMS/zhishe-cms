package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 *社团创建申请表单数据
 *
 * @author yang
 */
public class CmsClubsCreationsParam {

    /**
     * clubName : test
     * applicant : 张三
     * reason : make friends
     * type : 运动类
     * officialState : true
     * accessoryUrl : https://xxx/xxx/xx.doc
     */
    @ApiModelProperty(value = " 社团名 ", required = true)
    @NotEmpty(message = " 社团名不能为空 ")
    private String clubName;
    @ApiModelProperty(value = " 申请人 ", required = true)
    @NotEmpty(message = " 申请人不能为空 ")
    private String applicant;
    @ApiModelProperty(value = " 理由 ", required = true)
    @NotEmpty(message = " 理由不能为空 ")
    private String reason;
    @ApiModelProperty(value = " 社团类型 ", required = true)
    @NotEmpty(message = " 社团类型不能为空 ")
    private String type;

    private boolean officialState;

    private String accessoryUrl;

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isOfficialState() {
        return officialState;
    }

    public void setOfficialState(boolean officialState) {
        this.officialState = officialState;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}
