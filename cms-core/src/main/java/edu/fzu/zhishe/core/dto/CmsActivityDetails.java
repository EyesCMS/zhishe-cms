package edu.fzu.zhishe.core.dto;

import java.util.List;

/**
 * @author liang on 4/26/2020.
 * @version 1.0
 */
public class CmsActivityDetails {

    /**
     * title : 活动1
     * content : 这是内容
     * createAt : 2018-04-19 18:14:12
     * remarks : [{"username":"张三","content":"好玩","createAt":"2018-04-19 18:14:12"}]
     */

    private String title;
    private String content;
    private String createAt;
    private List<CmsRemarkDTO> remarks;

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

    public List<CmsRemarkDTO> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<CmsRemarkDTO> remarks) {
        this.remarks = remarks;
    }
}
