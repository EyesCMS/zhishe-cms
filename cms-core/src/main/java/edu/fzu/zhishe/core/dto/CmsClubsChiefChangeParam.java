package edu.fzu.zhishe.core.dto;

public class CmsClubsChiefChangeParam {
    /**
     * clubId : 5000
     * oldChiefId : 10088
     * newChiefName : test3
     * reason : make friends
     */

    private int clubId;
    private int oldChiefId;
    private String newChiefName;
    private String reason;

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getOldChiefId() {
        return oldChiefId;
    }

    public void setOldChiefId(int oldChiefId) {
        this.oldChiefId = oldChiefId;
    }

    public String getNewChiefName() {
        return newChiefName;
    }

    public void setNewChiefName(String newChiefName) {
        this.newChiefName = newChiefName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
