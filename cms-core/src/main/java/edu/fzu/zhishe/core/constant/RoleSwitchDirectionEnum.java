package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 5/2/2020.
 * @version 1.0
 */
public enum RoleSwitchDirectionEnum {

    /**
     * from personal center to club
     */
    USER_TO_CLUB(0),

    /**
     * from club to personal center
     */
    CLUB_TO_USER(1)
    ;

    private int value;

    RoleSwitchDirectionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
