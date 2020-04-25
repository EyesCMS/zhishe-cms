package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/19/2020.
 * @version 1.0
 */
public enum UserRoleEnum {
    /**
     * 普通学生用户
     */
    NORMAL(1),

    /**
     * 社团普通成员
     */
    MEMBER(2),

    /**
     * 社长
     */
    CHIEF(3),

    /**
     * 管理员
     */
    ADMIN(4);

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
