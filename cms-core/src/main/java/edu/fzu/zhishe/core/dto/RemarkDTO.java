package edu.fzu.zhishe.core.dto;

public class RemarkDTO {

    /**
     * username : 张三 content : 好玩 createAt : 2018-04-19 18:14:12
     */

    private String username;
    private String content;
    private String createAt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "RemarkDTO{" +
            "username='" + username + '\'' +
            ", content='" + content + '\'' +
            ", createAt='" + createAt + '\'' +
            '}';
    }
}
