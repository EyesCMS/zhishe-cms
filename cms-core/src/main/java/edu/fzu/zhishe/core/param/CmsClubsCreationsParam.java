package edu.fzu.zhishe.core.param;

import edu.fzu.zhishe.core.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *社团创建申请表单数据
 *
 * @author yang
 */
public class CmsClubsCreationsParam {

    /**
     * clubName : test

     * reason : make friends
     * type : 运动类
     * officialState : true

     */
    @ApiModelProperty(value = " 社团名 ", required = true)
    @NotBlank(message = " 社团名不能为空 ")
    private String clubName;
    @ApiModelProperty(value = " 理由 ", required = true)
    @NotBlank(message = " 理由不能为空 ")
    private String reason;
    @ApiModelProperty(value = " 社团类型 ", required = true)
    @NotBlank(message = " 社团类型不能为空 ")
    private String type;
    @ApiModelProperty(value = " 社团官方状态 ", required = true)
    @FlagValidator(value = {"0","1"},message = "社团官方状态不正确")
    @NotNull(message = " 官方状态不能为空 ")
    private Integer officialState;



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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getOfficialState() {
        return officialState;
    }

    public void setOfficialState(Integer officialState) {
        this.officialState = officialState;
    }
}
