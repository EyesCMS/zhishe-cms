package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户注册参数
 *
 * @author liang
 */
public class SysUserRegisterParam {

    @ApiModelProperty(value = " 用户名 ", required = true)
    @NotEmpty(message = " 用户名不能为空 ")
    private String username;

    @ApiModelProperty(value = " 密码 ", required = true)
    @NotEmpty(message = " 密码不能为空 ")
    private String password;

    @ApiModelProperty(value = " 用户昵称 ")
    private String nickname;

    @ApiModelProperty(value = " 用户头像 ")
    private String avatarUrl;

    @ApiModelProperty(value = " 邮箱 ")
    @Email(message = " 邮箱格式不合法 ")
    private String email;

    @ApiModelProperty(value = " 专业 ")
    private String major;

    @ApiModelProperty(value = " 电话 ")
    @Pattern(regexp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\\\d{8}$", message = " 电话格式不合法 ")
    private String phone;

    @ApiModelProperty(value = " 密保问题 ")
    private String question;

    @ApiModelProperty(value = " 密保问题答案 ")
    private String answer;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public String getPhone() {
        return phone;
    }

    public String getQuestion() { return question; }

    public String getAnswer() { return answer; }
}
