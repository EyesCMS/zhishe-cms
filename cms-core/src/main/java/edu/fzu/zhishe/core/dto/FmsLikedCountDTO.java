package edu.fzu.zhishe.core.dto;

import java.io.Serializable;

public class FmsLikedCountDTO implements Serializable {

    private static final long serialVersionUID = -2856160546081194945L;

    private Long id;

    private Integer count;

    public FmsLikedCountDTO() {
    }

    public FmsLikedCountDTO(Long id, Integer count) {
        this.id = id;
        this.count = count;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
