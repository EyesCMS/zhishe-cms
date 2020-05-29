package edu.fzu.zhishe.core.constant;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

public enum ClubTypeEnum implements BaseErrorEnum {

    STUDY("学习"),
    ART("艺术"),
    SPORT("运动"),
    LEISURE("休闲"),
    OTHER("其他"),

    ;

    private ClubTypeEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
