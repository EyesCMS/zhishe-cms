package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/29/2020.
 * @version 1.0
 */
public enum ClubStatueEnum {

    /**
     * 非社团成员
     */
    NONE(0, "student"),

    /**
     * 社团普通成员
     */
    MEMBER(1, "member"),

    /**
     * 社长
     */
    CHIEF(2, "chief");

    private Integer value;
    private String msg;

    ClubStatueEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
