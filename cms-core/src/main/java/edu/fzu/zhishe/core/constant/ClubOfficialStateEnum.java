package edu.fzu.zhishe.core.constant;
/**
 * @author yang on 4/24/2020.
 * @version 1.0
 */
public enum ClubOfficialStateEnum {
    /**
     * 正式
     */
    OFFICIAL(1),

    /**
     * 非正式
     */
    UNOFFICIAL(0);

    private int value;
    ClubOfficialStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static boolean isLegal(int value){
        if(value!=OFFICIAL.getValue()&&value!=UNOFFICIAL.getValue()){
            return false;
        }
        else {
            return true;
        }
    }

}
