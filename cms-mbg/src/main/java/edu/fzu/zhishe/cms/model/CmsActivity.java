package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class CmsActivity implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "社团 id")
    private Integer clubId;

    private String name;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String body;

    @ApiModelProperty(value = "图片 url 链接")
    private String imgUrl;

    @ApiModelProperty(value = "开始日期")
    private Date starDate;

    @ApiModelProperty(value = "结束日期")
    private Date endData;

    @ApiModelProperty(value = "活动地址")
    private String location;

    @ApiModelProperty(value = "参与人数")
    private Integer memberCount;

    @ApiModelProperty(value = "创建时间")
    private Date createAt;

    @ApiModelProperty(value = "处理时间")
    private Date handleAt;

    @ApiModelProperty(value = "活动状态: 0 -> “未审核”; 1 -> '审核通过'; 2 -> '已发布'; 3 -> '审核未通过'; 4 -> '已结束'")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndData() {
        return endData;
    }

    public void setEndData(Date endData) {
        this.endData = endData;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
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
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", body=").append(body);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", starDate=").append(starDate);
        sb.append(", endData=").append(endData);
        sb.append(", location=").append(location);
        sb.append(", memberCount=").append(memberCount);
        sb.append(", createAt=").append(createAt);
        sb.append(", handleAt=").append(handleAt);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}