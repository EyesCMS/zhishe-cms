package edu.fzu.zhishe.core.param;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public class FmsPostParam {


    /**
     * title : 这是一个帖子
     * content : 这是内容
     * imgUrl: https://xxxx/xxx.png
     */

    private String title;
    private String content;
    private String imgUrl;

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
}
