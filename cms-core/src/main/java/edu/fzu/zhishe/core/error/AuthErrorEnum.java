package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/30/2020
 */
public enum AuthErrorEnum implements BaseErrorEnum {

    NOT_USER("你未登录，无权访问"),
    NOT_MEMBER("你不是社员，无权访问"),
    NOT_CHIEF("你不是社长，无权访问"),
    NOT_ADMIN("你不是管理员，无权访问"),
    ;

    AuthErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {

        return this.message;
    }
}
