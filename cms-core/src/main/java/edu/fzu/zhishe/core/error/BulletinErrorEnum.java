package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author zou
 */

public enum BulletinErrorEnum implements BaseErrorEnum {

     //403
    NOT_PRESIDENT("You Are Not President"),
    CAN_NOT_DELETE_BULLETIN("Can Not Delete Bulletin"),
    CAN_NOT_UPDATE_BULLETIN("Can Not Update Bulletin"),

     //404
    BULLETIN_NOT_EXIST("Bulletin Is Not Exist"),
    CLUB_NOT_EXIST("Club Not Exist"),

    ;
    private BulletinErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }
}
