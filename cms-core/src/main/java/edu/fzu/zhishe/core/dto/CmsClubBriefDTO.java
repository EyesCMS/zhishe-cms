package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

public class CmsClubBriefDTO {

    private Integer id;

    private String name;

    @ApiModelProperty(value = "社长 name")
    private String chiefName;

    @ApiModelProperty(value = "类型")
    private String type;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "头像链接")
    private String avatarUrl;

    @ApiModelProperty(value = "加入状态")
    private String joinState;

    @ApiModelProperty(value = "身份")
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getJoinState() {
        return joinState;
    }

    public void setJoinState(String joinState) {
        this.joinState = joinState;
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }
}
