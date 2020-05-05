package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

public class CmsClubMemberBriefDTO {

    @ApiModelProperty(value = "用户 id")
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    @ApiModelProperty(value = "头衔")
    private String honor;

    @ApiModelProperty(value = "角色")
    private String role;

    @ApiModelProperty(value = "活跃度")
    private Integer credit;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getCredit() { return credit; }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


}
