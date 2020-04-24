package edu.fzu.zhishe.core.dto;

public class CmsClubsJoinParam {

    /**
     * userId : 10001
     * clubId : 5000
     * reason : 没为什么
     */

    private int userId;
    private int clubId;
    private String reason;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
