package edu.fzu.zhishe.core.dto;

import java.util.Date;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsChiefChangeDTO {
    /**
     * id : 1
     * clubName : wangs
     * oldChiefName : want
     * newChiefName : zhang
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */

    private int id;
    private String clubName;
    private String oldChiefName;
    private String newChiefName;
    private Date createAt;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
