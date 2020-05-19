package edu.fzu.zhishe.cms.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CmsMemberHonor implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "头衔值")
    private String name;

    @ApiModelProperty(value = "积分上限")
    private Integer lowerLimit;

    @ApiModelProperty(value = "积分下限")
    private Integer upperLimit;

    @ApiModelProperty(value = "描述")
    private String description;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", lowerLimit=").append(lowerLimit);
        sb.append(", upperLimit=").append(upperLimit);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}