package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public enum PostTypeEnum {
    /**
     * personal post
     */
    PERSONAL(0),

    /**
     * activity post
     */
    ACTIVITY(1)
    ;

    private Integer value;

    PostTypeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
