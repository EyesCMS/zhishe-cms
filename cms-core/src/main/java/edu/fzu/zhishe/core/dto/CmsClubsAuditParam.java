package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

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
    private int id;
    @ApiModelProperty(value = " 审核状态 ", required = true)
    private int state;

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
