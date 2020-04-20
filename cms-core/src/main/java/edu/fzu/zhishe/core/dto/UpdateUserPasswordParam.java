package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 修改用户密码参数
 *
 * @author liang
 */
@Data
public class UpdateUserPasswordParam {

    @ApiModelProperty(value = " 用户名 ", required = true)
    @NotEmpty(message = " 用户名不能为空 ")
    private String username;

    @ApiModelProperty(value = " 旧密码 ", required = true)
    @NotEmpty(message = " 旧密码不能为空 ")
    private String oldPassword;

    @ApiModelProperty(value = " 新密码 ", required = true)
    @NotEmpty(message = " 新密码不能为空 ")
    private String newPassword;
}
