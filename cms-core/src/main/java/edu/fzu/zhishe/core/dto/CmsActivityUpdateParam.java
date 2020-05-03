package edu.fzu.zhishe.core.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author PSF(52260506 @ qq.com)
 * @
 */
public class CmsActivityUpdateParam {


    @ApiModelProperty(value = " 活动名称 ", required = true)
    @NotNull(message = " 活动名称不能为空 ")
    private String name;

    @ApiModelProperty(value = " 活动标题 ", required = true)
    @NotNull(message = " 活动标题不能为空 ")
    private String title;

    @ApiModelProperty(value = " 活动内容 ", required = true)
    @NotNull(message = " 活动内容不能为空 ")
    private String content;

    @ApiModelProperty(value = " 活动开始时间 ", required = true)
    @NotNull(message = " 活动开始时间不为空 ")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date starDate;

    @ApiModelProperty(value = " 活动结束时间 ", required = true)
    @NotNull(message = " 活动结束时间不为空 ")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty(value = " 活动地点 ", required = true)
    private String location;

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getStarDate() {
        return starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }
}
