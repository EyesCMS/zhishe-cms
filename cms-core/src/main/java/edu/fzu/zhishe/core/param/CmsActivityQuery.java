package edu.fzu.zhishe.core.param;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class CmsActivityQuery {
    private String clubName;
    private Integer state;
    private String name; //	活动名称
    private String title;//	活动标题
    private String content;//	活动内容
    private String location;//	活动地点
    private String startDate;//	活动开始时间 例如2020-05-01
    private String endDate;//	活动结束时间 例如2020-05-01

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
