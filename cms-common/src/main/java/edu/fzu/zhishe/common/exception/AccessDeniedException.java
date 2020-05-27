package edu.fzu.zhishe.common.exception;

/**
 * Access denied exception (403)
 * @author liang on 5/3/2020.
 * @version 1.0
 */
public class AccessDeniedException extends BaseException {

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(BaseErrorEnum errorEnum) {
        super(errorEnum);
    }
}
