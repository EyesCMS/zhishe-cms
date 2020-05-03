package edu.fzu.zhishe.core.param;

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

    private Long postId;

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
