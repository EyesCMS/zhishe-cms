package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CmsClubPicture implements Serializable {
    private Integer id;

    private Integer clubId;

    private String pic1Url;

    private String pic2Url;

    private String pic3Url;

    private String pic4Url;

    private String pic5Url;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public String getPic1Url() {
        return pic1Url;
    }

    public void setPic1Url(String pic1Url) {
        this.pic1Url = pic1Url;
    }

    public String getPic2Url() {
        return pic2Url;
    }

    public void setPic2Url(String pic2Url) {
        this.pic2Url = pic2Url;
    }

    public String getPic3Url() {
        return pic3Url;
    }

    public void setPic3Url(String pic3Url) {
        this.pic3Url = pic3Url;
    }

    public String getPic4Url() {
        return pic4Url;
    }

    public void setPic4Url(String pic4Url) {
        this.pic4Url = pic4Url;
    }

    public String getPic5Url() {
        return pic5Url;
    }

    public void setPic5Url(String pic5Url) {
        this.pic5Url = pic5Url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clubId=").append(clubId);
        sb.append(", pic1Url=").append(pic1Url);
        sb.append(", pic2Url=").append(pic2Url);
        sb.append(", pic3Url=").append(pic3Url);
        sb.append(", pic4Url=").append(pic4Url);
        sb.append(", pic5Url=").append(pic5Url);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}