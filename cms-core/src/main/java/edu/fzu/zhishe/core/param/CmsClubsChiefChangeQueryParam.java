package edu.fzu.zhishe.core.param;

public class CmsClubsChiefChangeQueryParam {
    /**
     * id : 1
     * clubName : wangs
     * oldChiefName : want
     * newChiefName : zhang
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */

    private Integer id;
    private String clubName;
    private String oldChiefName;
    private String newChiefName;
    private String createAt;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getOldChiefName() {
        return oldChiefName;
    }

    public void setOldChiefName(String oldChiefName) {
        this.oldChiefName = oldChiefName;
    }

    public String getNewChiefName() {
        return newChiefName;
    }

    public void setNewChiefName(String newChiefName) {
        this.newChiefName = newChiefName;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
