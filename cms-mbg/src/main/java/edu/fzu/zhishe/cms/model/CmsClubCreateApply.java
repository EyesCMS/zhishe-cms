package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsClubCreateApply implements Serializable {
    private Integer id;

    private Integer userId;

    @ApiModelProperty(value = "申请人")
    private String applicant;

    @ApiModelProperty(value = "社团名称")
    private String clubName;

    @ApiModelProperty(value = "官方状态: 0 -> 非正式; 1 -> 正式;")
    private Boolean officialState;

    @ApiModelProperty(value = "社团类别")
    private String type;

    @ApiModelProperty(value = "申请原因")
    private String reason;

    @ApiModelProperty(value = "申请时间")
    private Date createAt;

    @ApiModelProperty(value = "处理时间")
    private Date handleAt;

    @ApiModelProperty(value = "申请状态：0 -> 未审核; 1 -> 审核通过; 2 -> 审核未通过;")
    private Boolean state;

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

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Boolean getOfficialState() {
        return officialState;
    }

    public void setOfficialState(Boolean officialState) {
        this.officialState = officialState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getHandleAt() {
        return handleAt;
    }

    public void setHandleAt(Date handleAt) {
        this.handleAt = handleAt;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", applicant=").append(applicant);
        sb.append(", clubName=").append(clubName);
        sb.append(", officialState=").append(officialState);
        sb.append(", type=").append(type);
        sb.append(", reason=").append(reason);
        sb.append(", createAt=").append(createAt);
        sb.append(", handleAt=").append(handleAt);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}