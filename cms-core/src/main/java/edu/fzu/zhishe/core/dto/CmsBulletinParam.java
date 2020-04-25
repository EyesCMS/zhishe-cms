package edu.fzu.zhishe.core.dto;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public class CmsBulletinParam {


    /**
     * title : this is a bulletin
     * content : 3 days later
     */

    private String title;
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
