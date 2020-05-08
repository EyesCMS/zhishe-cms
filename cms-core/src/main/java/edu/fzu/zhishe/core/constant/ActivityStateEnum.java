package edu.fzu.zhishe.core.constant;

/**
 * @author liang on 4/27/2020.
 * @version 1.0
 */
public enum ActivityStateEnum {

    /**
     * 未审核
     */
    PENDING(0),

    /**
     * 审核通过
     */
    ACTIVE(1),

    /**
     * 已发布
     */
    PUBLISHED(2),

    /**
     * 审核未通过
     */
    REJECTED(3),

    /**
     * 已结束
     */
    FINISHED(4),

    /**
     * 已删除
     */
    DELETED(5);

    private Integer value;
    ActivityStateEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
