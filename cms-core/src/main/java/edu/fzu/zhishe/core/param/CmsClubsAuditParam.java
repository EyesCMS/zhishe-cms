package edu.fzu.zhishe.core.param;

import edu.fzu.zhishe.core.validator.FlagValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *社团创建审核申请表单数据
 *
 * @author yang
 */
public class CmsClubsAuditParam {
    /**
     * id : 1
     * state : 1
     */
    @ApiModelProperty(value = " 申请id ", required = true)
    @NotNull(message = " 申请id不能为空 ")
    private Integer id;
    @ApiModelProperty(value = " 审核状态 ", required = true)
    @FlagValidator(value = {"0", "1", "2"}, message = " 审核状态范围错误 ")
    private Integer state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
