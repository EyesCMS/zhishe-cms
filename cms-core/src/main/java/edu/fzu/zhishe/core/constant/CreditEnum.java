package edu.fzu.zhishe.core.constant;

public enum CreditEnum {
    /**
     * 签到（暂定一分）
     */
    CHECKIN(100),

    /**
     * 评论（暂定两分）
     */
    COMMENT(2);

    private int value;
    CreditEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
