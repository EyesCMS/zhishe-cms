package edu.fzu.zhishe.common.util;

import lombok.Data;

import java.util.List;


public class CommonList {
    /**
     * 通用列表返回封装类
     *
     * @param totalCount 整个列表的size
     * @param items 分好页！！！！！！！！！！的列表
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
