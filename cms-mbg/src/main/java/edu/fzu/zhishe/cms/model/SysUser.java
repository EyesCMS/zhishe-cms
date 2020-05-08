package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class SysUser implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像链接")
    private String avatarUrl;

    @ApiModelProperty(value = "专业")
    private String major;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "宿舍地址")
    private String address;

    @ApiModelProperty(value = "标语")
    private String slogan;

    @ApiModelProperty(value = "密保问题")
    private String loginQuestion;

    @ApiModelProperty(value = "密保答案")
    private String loginAnswer;

    @ApiModelProperty(value = "管理状态：0 -> 普通用户；1 -> 管理员；")
    private Integer isAdmin;

    @ApiModelProperty(value = "当前用户角色，可能随着前端路由改变而切换")
    private Integer currentRole;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getLoginQuestion() {
        return loginQuestion;
    }

    public void setLoginQuestion(String loginQuestion) {
        this.loginQuestion = loginQuestion;
    }

    public String getLoginAnswer() {
        return loginAnswer;
    }

    public void setLoginAnswer(String loginAnswer) {
        this.loginAnswer = loginAnswer;
    }

    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getCurrentRole() {
        return currentRole;
    }

    public void setCurrentRole(Integer currentRole) {
        this.currentRole = currentRole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", major=").append(major);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", slogan=").append(slogan);
        sb.append(", loginQuestion=").append(loginQuestion);
        sb.append(", loginAnswer=").append(loginAnswer);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", currentRole=").append(currentRole);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}