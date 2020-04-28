package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CmsClubsChiefChangeParam {
    /**
     * clubId : 5000
     * oldChiefId : 10088
     * newChiefName : test3
     * reason : make friends
     */
    @ApiModelProperty(value = " 社团id ", required = true)
    @NotNull(message = " 社团id不能为空 ")
    private int clubId;
    @ApiModelProperty(value = " 老社长id ", required = true)
    @NotNull(message = " 老社长id不能为空 ")
    private int oldChiefId;
    @ApiModelProperty(value = " 新社长用户名 ", required = true)
    @NotEmpty(message = " 新社长用户名不能为空 ")
    private String newChiefName;
    @ApiModelProperty(value = " 理由 ", required = true)
    @NotEmpty(message = " 理由不能为空 ")
    private String reason;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getOldChiefId() {
        return oldChiefId;
    }

    public void setOldChiefId(int oldChiefId) {
        this.oldChiefId = oldChiefId;
    }

    public String getNewChiefName() {
        return newChiefName;
    }

    public void setNewChiefName(String newChiefName) {
        this.newChiefName = newChiefName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
