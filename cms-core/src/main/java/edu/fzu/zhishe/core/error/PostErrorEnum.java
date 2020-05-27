package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/27/2020
 */
public enum PostErrorEnum implements BaseErrorEnum {

    // 400
    ALREADY_LIKED("You Have Liked Already"),
    DOES_NOT_LIKED("You Have Not Liked Before"),

    // 403
    NOT_POSTER("You Are Not Poster"),
    CAN_NOT_DELETE_ACTIVITY_POST("Can Not Delete Activity Post"),
    CAN_NOT_UPDATE_ACTIVITY_POST("Can Not Update Activity Post"),

    // 404
    POST_NOT_EXIST("Post Is Not Exist"),
    REMARK_NOT_EXIST("Remark Is Not Exist"),

    ;

    private PostErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
