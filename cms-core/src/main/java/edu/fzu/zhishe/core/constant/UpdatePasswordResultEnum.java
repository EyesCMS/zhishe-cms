package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/19/2020.
 * @version 1.0
 */
public enum UpdatePasswordResultEnum {

    SUCCESS(0, "修改成功"),
    USER_NOT_FOUND(-1, "找不到该用户"),
    ERROR_OLD_PASSWORD(-2, "旧密码错误"),
    ANSWER_ERROR(-3, "密保答案错误");

    private int state;
    private String message;

    UpdatePasswordResultEnum(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public String getMessage() {
        return message;
    }
}
