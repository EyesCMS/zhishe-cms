package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *社团解散申请表单数据
 *
 * @author yang
 */
public class CmsClubsDisbandParam {
    /**
     * clubId : 10001
     * applicant : 张三
     * reason : 没为什么
     * accessoryUrl : https://xxx/xxx/xx.doc
     */
    @ApiModelProperty(value = " 社团id ", required = true)
    @NotNull(message = " 社团id不能为空 ")
    private Integer clubId;

    @ApiModelProperty(value = " 理由 ", required = true)
    @NotBlank(message = " 理由不能为空 ")
    private String reason;
//    @ApiModelProperty(value = " 附件 ", required = true)
//    @NotBlank(message = " 附件不能为空? ")


    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
