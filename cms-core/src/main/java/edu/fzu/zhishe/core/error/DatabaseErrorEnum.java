package edu.fzu.zhishe.core.error;

import edu.fzu.zhishe.common.exception.BaseErrorEnum;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/30/2020
 */
public enum DatabaseErrorEnum implements BaseErrorEnum {

    CREATE_ERROR("系统插入数据出现错误"),
    READ_ERROR("系统读取数据出现错误"),
    UPDATE_ERROR("系统更新数据出现错误"),
    DELETE_ERROR("系统删除数据出现错误"),
    ;

    DatabaseErrorEnum(String message) {
        this.message = message;
    }

    private final String message;

    @Override
    public String getMessage() {
        return this.message;
    }

}
