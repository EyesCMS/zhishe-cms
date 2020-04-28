package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CmsCertificationsParam {
    /**
     * clubId : 1
     * applicant : wangping
     * accessoryUrl : htttps://xxx/xxx.doc
     */

    @ApiModelProperty(value = " 社团id ", required = true)
    @NotNull(message = " 社团id不能为空 ")
    private Integer clubId;
    @ApiModelProperty(value = " 理由 ", required = true)
    @NotBlank(message = " 理由不能为空 ")
    private String reason;
    @ApiModelProperty(value = " 申请人 ", required = true)
    @NotBlank(message = " 申请人不能为空 ")
    private String applicant;
//    @ApiModelProperty(value = " 附件 ", required = true)
//    @NotBlank(message = " 附件不能为空？ ")
    private String accessoryUrl;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getReason(){
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}
