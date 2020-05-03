package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Min;

@ApiModel("排序参数")
public class OrderByParam {

    @ApiModelProperty(value = "排序字段", notes = "默认以主键id")
    private String sort = "id";

    @ApiModelProperty(value = "排序方向", notes = "升序", example = "asc, desc")
    private String order = "asc";

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
