package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class CreditForCheckinParam {
    /**
     * userId : 33
     * clubId : 33
     */
    @ApiModelProperty(value = " 用户id ", required = true)
    @NotNull(message = " 用户id不能为空 ")
    private Integer userId;
    @ApiModelProperty(value = " 社团id ", required = true)
    @NotNull(message = " 社团id不能为空 ")
    private Integer clubId;

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
}
