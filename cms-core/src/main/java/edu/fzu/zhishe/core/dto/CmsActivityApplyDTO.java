package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class CmsActivityApplyDTO {

    /**
     * id : 1
     * name : 活动1
     * location : 青春广场
     * content : 社团团聚
     * member_count : 55
     * start_date : 2018-04-19 18:14:12
     * end_date : 2018-04-19 18:14:12
     * state : 0
     */

    @ApiModelProperty(value = "申请表id")
    private int id;
    private String name;
    private String location;
    private String content;
    private String memberCount;
    private String startDate;
    private String endDate;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStart_date() {
        return startDate;
    }

    public void setStart_date(String start_date) {
        this.startDate = start_date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(String memberCount) {
        this.memberCount = memberCount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
