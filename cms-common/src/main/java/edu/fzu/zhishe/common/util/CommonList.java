package edu.fzu.zhishe.common.util;

import lombok.Data;

import java.util.List;


public class CommonList {
    /**
     * 通用列表返回封装类
     * @param items 存放分页好的列表
     * @param totalCount 存放整个列表的size
     * @return
     */
    private int totalCount;
    private List items;
    public static  CommonList getCommonList(List list,int totalCount) {
        CommonList result = new CommonList();
        result.setItems(list);
        result.setTotalCount(totalCount);
        return result;
    }

    /**
     * 通用列表返回封装类
     * List 传送查询到的整个列表
     * pageNum 页号
     * pageSize 页大小
     * 直接可以返回分好页的CommonList
     */
    public static  CommonList getCommonList(List list,Integer pageNum,
                                            Integer pageSize) {
        CommonList result = new CommonList();
        int totalCount = list.size();
        result.setItems(PageUtil.startPage(list, pageNum, pageSize));
        result.setTotalCount(totalCount);
        return result;
    }

    public int getTotalCount(){
        return this.totalCount;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public List getItems(){
        return this.items;
    }

    public void setItems(List items){
        this.items = items;
    }
}
