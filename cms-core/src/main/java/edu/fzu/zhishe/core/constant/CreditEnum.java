package edu.fzu.zhishe.core.constant;

public enum CreditEnum {
    /**
     * 签到（暂定8分）
     */
    CHECKIN(8),

    /**
     * 评论（暂定5分）
     */
    COMMENT(5),

    /**
     * 社团发布活动（暂定20分）
     */
    ACTIVITY(20);

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
