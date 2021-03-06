package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsUserClubRel implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer clubId;

    @ApiModelProperty(value = "角色id(默认为2社员)")
    private Integer roleId;

    @ApiModelProperty(value = "积分")
    private Integer credit;

    @ApiModelProperty(value = "用户头衔 id")
    private Integer honorId;

    @ApiModelProperty(value = "上次签到时间")
    private Date checkInDate;

    @ApiModelProperty(value = "加入时间")
    private Date joinDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getHonorId() {
        return honorId;
    }

    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", clubId=").append(clubId);
        sb.append(", roleId=").append(roleId);
        sb.append(", credit=").append(credit);
        sb.append(", honorId=").append(honorId);
        sb.append(", checkInDate=").append(checkInDate);
        sb.append(", joinDate=").append(joinDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}