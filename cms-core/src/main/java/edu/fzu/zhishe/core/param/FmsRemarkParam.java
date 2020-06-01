package edu.fzu.zhishe.core.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 发布评论参数
 * @author liang on 4/28/2020.
 * @version 1.0
 */
public class FmsRemarkParam {

    /**
     * postId : 33
     * content : 3 days later
     */

    @NotNull(message = "帖子 id 不能为空")
    private Long postId;
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
