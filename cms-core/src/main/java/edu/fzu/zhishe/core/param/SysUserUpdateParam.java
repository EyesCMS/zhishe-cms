package edu.fzu.zhishe.core.param;

import edu.fzu.zhishe.core.validator.PhoneValidator;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class SysUserUpdateParam {

    @ApiModelProperty(value = " 昵称 ")
    private String nickname;

    @ApiModelProperty(value = " 专业 ")
    private String major;

    @ApiModelProperty(value = " 宿舍地址 ")
    private String address;

    @ApiModelProperty(value = " 联系方式 ")
    @PhoneValidator
    private String phone;

    @ApiModelProperty(value = " 个人标语 ")
    private String slogan;

    @ApiModelProperty(value = " 邮箱 ")
    @Email(message = " 邮箱格式不合法 ")
    private String email;

    @ApiModelProperty(value = " 头像链接 ")
    private String avatarUrl;

    public String getNickname() {
        return nickname;
    }

    public String getMajor() {
        return major;
    }

    public String getAddress() { return address;}

    public String getPhone() {
        return phone;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() { return avatarUrl; }
}
