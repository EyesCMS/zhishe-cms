package edu.fzu.zhishe.core.dto;

import java.util.Date;

/**
 * @author liang on 4/29/2020.
 * @version 1.0
 */
public class CmsBulletinDTO {


    /**
     * title : 公告1
     * content : 这是内容
     * createAt : 2018-04-19 18:14:12
     * updateAt : 2018-04-19 19:14:12
     */

    private String title;
    private String content;
    private Date createAt;
    private Date updateAt;

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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
