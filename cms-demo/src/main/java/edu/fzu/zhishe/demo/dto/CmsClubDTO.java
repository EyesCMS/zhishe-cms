package edu.fzu.zhishe.demo.dto;

import edu.fzu.zhishe.demo.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
public class CmsClubDTO {

    private Integer userId;

    @ApiModelProperty(value = "申请人")
    @NotNull(message = "申请人不能为空")
    private String applicant;

    @ApiModelProperty(value = "社团名称")
    @NotNull(message = "社团名称不能为空")
    private String clubName;

    @ApiModelProperty(value = "官方状态: 0 -> 非正式; 1 -> 正式")
    @FlagValidator(value = {"0", "1"}, message = "官方状态不正确")
    private Integer officialState;

    @ApiModelProperty(value = "社团类别")
    @NotNull(message = "社团类别不能为空")
    private String type;

    @ApiModelProperty(value = "申请原因")
    @NotNull(message = "申请原因不能为空")
    private String reason;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getOfficialState() {
        return officialState;
    }

    public void setOfficialState(Integer officialState) {
        this.officialState = officialState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
