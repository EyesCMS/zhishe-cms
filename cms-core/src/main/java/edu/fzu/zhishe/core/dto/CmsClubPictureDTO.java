package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

public class CmsClubPictureDTO {

    @ApiModelProperty(value = "走马灯图片1")
    private String url1;

    @ApiModelProperty(value = "走马灯图片2")
    private String url2;

    @ApiModelProperty(value = "走马灯图片3")
    private String url3;

    @ApiModelProperty(value = "走马灯图片4")
    private String url4;

    @ApiModelProperty(value = "走马灯图片5")
    private String url5;

    public String getUrl1() { return url1; }

    public void setUrl1(String url) { this.url1 = url; }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url) { this.url2 = url; }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url) { this.url3 = url; }

    public String getUrl4() {
        return url4;
    }

    public void setUrl4(String url) { this.url4 = url; }

    public String getUrl5() {
        return url5;
    }

    public void setUrl5(String url) { this.url5 = url; }

}
