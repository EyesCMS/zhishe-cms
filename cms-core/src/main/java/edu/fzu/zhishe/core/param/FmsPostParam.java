package edu.fzu.zhishe.core.param;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public class FmsPostParam {

    /**
     * title : 这是一个帖子
     * content : 这是内容
     */

    @NotEmpty(message = "帖子标题不能为空")
    private String title;

    @NotEmpty(message = "帖子内容不能为空")
    private String content;

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

}
