package edu.fzu.zhishe.core.param;

/**
 * @author liang on 4/25/2020.
 * @version 1.0
 */
public class QueryParam {

    public QueryParam(Integer page, Integer limit, String sort, String order, String keyword) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
        this.order = order;
        this.keyword = keyword;
    }

    public QueryParam(PaginationParam paginationParam, OrderByParam orderByParam, String  keyword) {
        this.page = paginationParam.getPage();
        this.limit = paginationParam.getLimit();
        this.sort = orderByParam.getSort();
        this.order = orderByParam.getOrder();
        this.keyword = keyword;
    }

    public QueryParam(PaginationParam paginationParam, String  keyword) {
        this.page = paginationParam.getPage();
        this.limit = paginationParam.getLimit();
        this.sort = "id";
        this.order = "asc";
        this.keyword = keyword;
    }

    public QueryParam(OrderByParam orderByParam, String  keyword) {
        this.page = 1;
        this.limit = 10;
        this.sort = orderByParam.getSort();
        this.order = orderByParam.getOrder();
        this.keyword = keyword;
    }

    /**
     * 分页: 起始页
     */
    private Integer page;

    /**
     * 分页: 每页大小
     */
    private Integer limit;

    /**
     * 排序: 字段
     */
    private String sort;

    /**
     * 排序: 方向 (asc, desc)
     */
    private String order;

    /**
     * 模糊关键字
     */
    private String keyword;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
