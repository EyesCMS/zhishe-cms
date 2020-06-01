package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class SysUserUpdatePwdByAnswer {

    @ApiModelProperty(value = " 用户名 ")
    @NotEmpty(message = " 用户名不能为空 ")
    private String username;

    @ApiModelProperty(value = " 密码 ")
    @NotEmpty(message = " 新密码不能为空 ")
    @Size(min = 6, max = 20, message = "新密码长度必须介于6~20之间")
    private String password;

    @ApiModelProperty(value = " 密保问题答案 ")
    @NotEmpty(message = " 密保问题答案不能为空 ")
    private String answer;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAnswer() {
        return answer;
    }
}
