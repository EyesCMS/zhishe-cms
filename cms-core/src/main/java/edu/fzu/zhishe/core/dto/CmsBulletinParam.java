package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public class CmsBulletinParam {

    /**
     * title : this is a bulletin
     * content : 3 days later
     */

    /**
    * @author zou
    */

    @ApiModelProperty(value = "公告标题")
    @NotNull(message = "标题不能为空")
    private String title;

    @ApiModelProperty(value = "公告内容")
    @NotNull(message = "内容不能为空")
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
