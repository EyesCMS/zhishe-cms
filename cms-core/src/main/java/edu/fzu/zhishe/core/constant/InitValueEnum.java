package edu.fzu.zhishe.core.constant;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/30/2020
 */
public enum InitValueEnum {

    /**
     * 默认积分
     */
    CREDIT(0),

    /**
     * 默认头衔
     */
    HONOR(1),
    ;



    private final int value;
    InitValueEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
