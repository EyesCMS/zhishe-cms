package edu.fzu.zhishe.common.api;

import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.data.domain.Page;

/**
 * @author liang on 4/28/2020.
 */
public class CommonPage<T> {
    private Long totalCount;
    private List<T> items;

    /**
     * 将 PageHelper 分页后的 list 转为分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        result.setTotalCount(pageInfo.getTotal());
        result.setItems(pageInfo.getList());
        return result;
    }

    /**
     * 将 SpringData 分页后的 list 转为分页信息
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalCount(pageInfo.getTotalElements());
        result.setItems(pageInfo.getContent());
        return result;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
