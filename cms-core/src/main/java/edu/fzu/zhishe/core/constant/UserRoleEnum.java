package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/19/2020.
 * @version 1.0
 */
public enum UserRoleEnum {
    /**
     * 普通用户
     */
    NORMAL(0),

    /**
     * 管理员
     */
    ADMIN(1);

    private int value;
    UserRoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
