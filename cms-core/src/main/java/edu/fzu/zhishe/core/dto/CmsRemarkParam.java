package edu.fzu.zhishe.core.dto;

/**
 * 发布评论参数
 * @author liang on 4/28/2020.
 * @version 1.0
 */
public class CmsRemarkParam {

    /**
     * uid : 1
     * pid : 33
     * content : 3 days later
     */

    private int uid;
    private int pid;
    private String content;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
