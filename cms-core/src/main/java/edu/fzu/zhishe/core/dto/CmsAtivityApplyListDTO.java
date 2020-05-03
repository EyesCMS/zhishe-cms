package edu.fzu.zhishe.core.dto;

import java.util.Date;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class CmsAtivityApplyListDTO {


    /**
     * id : 2
     * clubName : 文艺部
     * name : write
     * title : this is a title
     * content : what content
     * startDate : 2018-04-22
     * endDate : 2018-05-22
     * location : 三区
     * state : 0
     */

    private int id;
    private String clubName;
    private String name;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;
    private String location;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
