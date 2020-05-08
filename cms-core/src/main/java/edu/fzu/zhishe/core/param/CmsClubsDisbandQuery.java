package edu.fzu.zhishe.core.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;


/**
 * *社团解散申请列表查询数据
 * @author Yang on 4/29/2020.
 * @version 1.0
 */
public class CmsClubsDisbandQuery {
    /**
     * id:1
     * clubName : 羽毛球社
     * createAt : 2018-04-19 18:14:12
     * applicant : 张三
     * reason : 本社团没有存在意义
     * state : 0
     */
    @ApiModelProperty(value = "申请id")
    private Integer id;
    @ApiModelProperty(value = "社团名")
    private String clubName;
    @ApiModelProperty(value = "创建时间")
    private String createAt;
    @ApiModelProperty(value = "理由")
    private String reason;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
