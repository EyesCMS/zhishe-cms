package edu.fzu.zhishe.common.exception;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/27/2020
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(BaseErrorEnum errorEnum) {
        super(errorEnum.getMessage());
    }
}
