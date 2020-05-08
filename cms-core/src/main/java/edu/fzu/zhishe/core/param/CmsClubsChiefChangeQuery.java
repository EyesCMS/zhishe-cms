package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsChiefChangeQuery {
    /**
     * id : 1
     * clubName : wangs
     * oldChiefName : want
     * newChiefName : zhang
     * createAt : 2018-04-19 18:14:12
     * state : 0
     */
    @ApiModelProperty(value = "申请id")
    private Integer id;
    @ApiModelProperty(value = "社团名")
    private String clubName;
    @ApiModelProperty(value = "老社长名")
    private String oldChiefName;
    @ApiModelProperty(value = "新社长名")
    private String newChiefName;
    @ApiModelProperty(value = "创建时间")
    private String createAt;
    @ApiModelProperty(value = "状态")
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
