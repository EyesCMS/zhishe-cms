package edu.fzu.zhishe.core.constant;

/**
 * @author yang on 4/24/2020.
 * @version 1.0
 */
public enum ApplyStateEnum {

    /**
     * 未审核
     */
    PENDING(0),

    /**
     * 审核通过
     */
    ACTIVE(1),

    /**
     * 审核未通过
     */
    REJECTED(2);

    private int value;
    ApplyStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static boolean isLegal(int value){
        //System.out.println(value);
        if(value!=PENDING.getValue()&&value!=ACTIVE.getValue()&&value!=REJECTED.getValue()){
            return false;
        }
        else
            return true;
    }
}
