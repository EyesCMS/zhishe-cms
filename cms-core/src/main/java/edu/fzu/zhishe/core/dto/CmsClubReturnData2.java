package edu.fzu.zhishe.core.dto;

import io.swagger.annotations.ApiModelProperty;

public class CmsClubReturnData2 extends CmsClubReturnData1{
    @ApiModelProperty(value = "成员数量")
    private Integer memberCount;

    @ApiModelProperty(value = "QQ 群号码")
    private String qqGroup;

    @ApiModelProperty(value = "标语")
    private String slogan;

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getQqGroup() {
        return qqGroup;
    }

    public void setQqGroup(String qqGroup) {
        this.qqGroup = qqGroup;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }
}
