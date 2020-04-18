package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsChiefChangeApply implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "社团 id")
    private Integer clubId;

    @ApiModelProperty(value = "旧社长 id")
    private Integer oldChiefId;

    @ApiModelProperty(value = "新社长 id")
    private Integer newChiefId;

    @ApiModelProperty(value = "换届原因")
    private String reason;

    @ApiModelProperty(value = "创建时间")
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

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public Integer getOldChiefId() {
        return oldChiefId;
    }

    public void setOldChiefId(Integer oldChiefId) {
        this.oldChiefId = oldChiefId;
    }

    public Integer getNewChiefId() {
        return newChiefId;
    }

    public void setNewChiefId(Integer newChiefId) {
        this.newChiefId = newChiefId;
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
        sb.append(", clubId=").append(clubId);
        sb.append(", oldChiefId=").append(oldChiefId);
        sb.append(", newChiefId=").append(newChiefId);
        sb.append(", reason=").append(reason);
        sb.append(", createAt=").append(createAt);
        sb.append(", handleAt=").append(handleAt);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}