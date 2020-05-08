package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
/**
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsQuitQuery {
    /**
     * id : 1
     * username : wangs
     * reason : 没意思
     * createAt : 2018-04-19 18:14:12
     */
    @ApiModelProperty(value = "申请id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "理由")
    private String reason;
    @ApiModelProperty(value = "创建时间")
    private String createAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
}
