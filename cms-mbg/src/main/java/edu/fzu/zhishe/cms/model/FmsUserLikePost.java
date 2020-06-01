package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class FmsUserLikePost implements Serializable {
    private Long id;

    @ApiModelProperty(value = "点赞用户 id")
    private Integer userId;

    @ApiModelProperty(value = "点赞帖子 id")
    private Long postId;

    @ApiModelProperty(value = "点赞状态: 0 -> 未点赞/取消点赞，1 -> 点赞")
    private Integer status;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", postId=").append(postId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}