package edu.fzu.zhishe.cms.model;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;


public class CmsClub implements Serializable {
    private Integer id;

    private String name;

    @ApiModelProperty(value = "社长 id")
    private Integer chiefId;

    @ApiModelProperty(value = "成员数量")
    private Integer memberCount;

    @ApiModelProperty(value = "QQ 群号码")
    private String qqGroup;

    @ApiModelProperty(value = "标语")
    private String slogan;

    @ApiModelProperty(value = "头像链接")
    private String avatarUrl;

    @ApiModelProperty(value = "官方状态: 0 -> 非官方; 1 -> 官方认证;")
    private Integer officialState;

    @ApiModelProperty(value = "社团类型")
    private String type;

    @ApiModelProperty(value = "等级")
    private Integer grade;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    private static final long serialVersionUID = 1L;

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

    public Integer getChiefId() {
        return chiefId;
    }

    public void setChiefId(Integer chiefId) {
        this.chiefId = chiefId;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getQqGroup() {
        return qqGroup;
    }

    public void setQqGroup(String qqGroup) {
        this.qqGroup = qqGroup;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getOfficialState() {
        return officialState;
    }

    public void setOfficialState(Integer officialState) {
        this.officialState = officialState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", chiefId=").append(chiefId);
        sb.append(", memberCount=").append(memberCount);
        sb.append(", qqGroup=").append(qqGroup);
        sb.append(", slogan=").append(slogan);
        sb.append(", avatarUrl=").append(avatarUrl);
        sb.append(", officialState=").append(officialState);
        sb.append(", type=").append(type);
        sb.append(", grade=").append(grade);
        sb.append(", createAt=").append(createAt);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}