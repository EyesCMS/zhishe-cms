package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class FmsPost implements Serializable {
    private Long id;

    @ApiModelProperty(value = "发帖人 id，可以是用户或者社团")
    private Integer posterId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片链接")
    private String imgUrl;

    @ApiModelProperty(value = "发帖时间")
    private Date createAt;

    @ApiModelProperty(value = "帖子类别：0 -> 个人帖；1 -> 社团活动帖")
    private Integer type;

    @ApiModelProperty(value = "删除状态：0 -> 未删除；1 -> 已删除")
    private Integer deleteState;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosterId() {
        return posterId;
    }

    public void setPosterId(Integer posterId) {
        this.posterId = posterId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(Integer deleteState) {
        this.deleteState = deleteState;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", posterId=").append(posterId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", imgUrl=").append(imgUrl);
        sb.append(", createAt=").append(createAt);
        sb.append(", type=").append(type);
        sb.append(", deleteState=").append(deleteState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}