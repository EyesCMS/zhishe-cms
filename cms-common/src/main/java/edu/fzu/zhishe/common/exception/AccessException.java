package edu.fzu.zhishe.common.exception;

/**
 * Access denied exception (403)
 * @author liang on 5/3/2020.
 * @version 1.0
 */
public class AccessException extends RuntimeException {

    public AccessException(String message) {
        super(message);
    }
}