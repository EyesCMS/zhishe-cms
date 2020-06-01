package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

public enum CreditErrorEnum implements BaseErrorEnum {

    // 400
    MAPPER_OPERATION_FAILED("mapper operation failed"),
    USER_CLUB_REL_NOT_EXIST(" 该社团已不存在或您已退出（还未加入）该社团 "),

    // 403
    ALREADY_CHECKIN("今天您已签到过，同一天不可重复签到"),

    // 404
    CLUB_NOT_EXIST(" 该社团不存在 "),

    ;

    private CreditErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
