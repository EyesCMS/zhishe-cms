package edu.fzu.zhishe.core.constant;
/**
 * @author yang on 4/26/2020.
 * @version 1.0
 */
public enum DeleteStateEnum {
    /**
     * 删除
     */
    Deleted(1),

    /**
     * 存在
     */
    Existence(0);

    private int value;
    DeleteStateEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static boolean isLegal(int value){
        if(value!=Deleted.getValue()&&value!=Existence.getValue()){
            return false;
        }
        else {
            return true;
        }
    }
}
