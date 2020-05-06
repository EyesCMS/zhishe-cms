package edu.fzu.zhishe.core.param;

import java.util.Date;

public class CmsBulletinQuery {
    /**
     * title : 公告1
     * content : 这是内容
     * createAt : 2018-04-19 18:14:12
     * updateAt : 2018-04-19 19:14:12
     */

    private Integer id;
    private String title;
    private String body;
    private Date createAt;
    private Date updateAt;

    public Integer getId() { return id;}

    public void setId() { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
