package edu.fzu.zhishe.core.constant;

public enum CheckinStateEnum {
    /**
     * 已经签到
     */
    DONE(0),
    /**
     * 可以签到
     */
    GRANTED(1),
    /**
     * 不可签到
     */
    DENIED(2);

    private int value;
    CheckinStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
