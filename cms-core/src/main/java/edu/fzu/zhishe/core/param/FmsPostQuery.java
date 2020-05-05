package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author liang on 5/5/2020.
 * @version 1.0
 */
public class FmsPostQuery {

    /**
     * posterName : 发帖人
     * title : 这是一个帖子
     * content : 这是内容
     * createAt : 2018-04-19
     */

    @ApiModelProperty(value = "发帖用户名/社团名称")
    private String posterName;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "发帖时间, 形如 2018-04-19")
    private String createAt;

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
