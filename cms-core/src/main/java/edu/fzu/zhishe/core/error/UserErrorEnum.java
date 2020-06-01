package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/30/2020
 */
public enum UserErrorEnum implements BaseErrorEnum {

    // 400
    AUTH_CODE_MISMATCH("验证码不匹配"),
    DUPLICATE_USERNAME("该用户名已经存在"),
    DUPLICATE_EMAIL("该邮箱已经注册过"),
    BAD_PASSWORD("密码错误"),
    BAD_USERNAME_OR_PASSWORD("用户名或密码错误"),
    NO_LOGIN_ANSWER("该用户未设置密保答案"),

    // 401

    // 403


    // 404
    USERNAME_NOT_FOUND("用户名不存在")
    ;

    UserErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
