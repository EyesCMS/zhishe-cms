package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author wjh674
 * @date 5/27/2020
 */
public enum ClubErrorEnum implements BaseErrorEnum {

    // 400
    EMPTY_TYPE("社团类型不能为空"),
    EMPTY_FILLED("输入不能为空"),
    WRONG_FILLED("输入参数有误"),

    // 403


    // 404
    CLUB_NOT_FOUND("社团不存在"),
    USER_NOT_IN("该用户不是社团成员"),

    ;

    private ClubErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
