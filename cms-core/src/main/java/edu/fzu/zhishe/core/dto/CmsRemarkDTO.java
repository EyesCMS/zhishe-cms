package edu.fzu.zhishe.core.dto;

public class CmsRemarkDTO {

    /**
     * userId : 1
     * nickname : 活动1
     * content : 这是内容
     * createAt : 2018-04-19 18:14:12
     * avatarUrl : e312312312312.jpg
     */

    private Integer userId;
    private String nickname;
    private String avatarUrl;
    private String content;
    private String createAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int useId) {
        this.userId = useId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
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
        return "CmsRemarkDTO{" +
            "useId=" + userId +
            ", nickname='" + nickname + '\'' +
            ", content='" + content + '\'' +
            ", createAt='" + createAt + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            '}';
    }
}
