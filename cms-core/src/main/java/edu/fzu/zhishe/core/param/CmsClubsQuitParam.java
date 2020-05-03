package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author yang on 4/27/2020.
 * @version 1.0
 */
public class CmsClubsQuitParam {
    /**

     * clubId : 5000
     * reason : 没为什么
     */
    @ApiModelProperty(value = " 社团id ", required = true)
    @NotNull(message = " 社团id不能为空 ")
    private Integer clubId;
    @ApiModelProperty(value = " 理由 ", required = true)
    @NotBlank(message = " 理由不能为空 ")
    private String reason;



    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
