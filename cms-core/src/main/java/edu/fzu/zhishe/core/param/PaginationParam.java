package edu.fzu.zhishe.core.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ApiModel("分页参数")
public class PaginationParam {

    @Min(1)
    @ApiModelProperty(value = "页号", notes = "默认值为 1")
    private int page = 1;

    @Min(1) @Max(100)
    @ApiModelProperty(value = "每页条目数", notes = "默认值为 10")
    private int limit = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
