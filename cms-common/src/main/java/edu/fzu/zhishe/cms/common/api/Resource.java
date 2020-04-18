package edu.fzu.zhishe.cms.common.api;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
public enum Resource {

    CLUB("Club"),
    USER("User");

    private String name;

    Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
