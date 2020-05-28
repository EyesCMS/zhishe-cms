package edu.fzu.zhishe.core.param;

import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public class FmsPostParam {

    /**
     * title : 这是一个帖子
     * content : 这是内容
     * image： bin
     */

    @NotEmpty(message = "帖子标题不能为空")
    private String title;

    @NotEmpty(message = "帖子内容不能为空")
    private String content;

    MultipartFile image;

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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
