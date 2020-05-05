package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

public class CmsClubMemberQuery {

    @ApiModelProperty(value = "用户名/昵称")
    private String name;

    @ApiModelProperty(value = "头衔 ID")
    private Integer honorId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHonorId() {
        return honorId;
    }

    public void setHonorId(Integer honorId) {
        this.honorId = honorId;
    }
}
