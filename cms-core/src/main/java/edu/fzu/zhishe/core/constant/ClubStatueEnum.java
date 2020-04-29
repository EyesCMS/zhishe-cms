package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/29/2020.
 * @version 1.0
 */
public enum ClubStatueEnum {

    /**
     * 非社团成员
     */
    NONE(0),

    /**
     * 社团普通成员
     */
    MEMBER(1),

    /**
     * 社长
     */
    CHIEF(2);

    private Integer value;

    ClubStatueEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
