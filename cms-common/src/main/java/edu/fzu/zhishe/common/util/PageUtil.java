package edu.fzu.zhishe.common.util;

import java.util.List;

/**
 * 自定义List分页工具
 * 使用样例：
 * 直接调用PageUtil.startPage(列表名称,页码（0开始）,每页大小);
 * 返回值即为指定页码和页面大小的子列表
 * @author yang
 */
public class PageUtil {

    /**
     * 开始分页
     *
     * @param list
     * @param pageNum  页码 从0开始
     * @param pageSize 每页多少条数据
     * @return
     */
    public static List startPage(List list, Integer pageNum,
                                 Integer pageSize) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        if (pageSize <= 0) {
            return null;
        }
        if (pageNum < 0) {
            return null;
        }

        Integer count = list.size(); // 记录总数
        Integer pageCount = 0; // 页数
        if (count % pageSize == 0) {
            pageCount = count / pageSize;
        } else {
            pageCount = count / pageSize + 1;
        }

        int fromIndex = 0; // 开始索引
        int toIndex = 0; // 结束索引

        if (pageNum < pageCount - 1) {
            fromIndex = pageNum * pageSize;
            toIndex = fromIndex + pageSize;
        } else if (pageNum == pageCount - 1) {
            fromIndex = pageNum * pageSize;
            toIndex = count;
        } else {
            //如果一共2页，因为是从第零页开始，pagenum最多到1，超过的话直接返回空
            return null;
        }

        List pageList = list.subList(fromIndex, toIndex);

        return pageList;
    }
}