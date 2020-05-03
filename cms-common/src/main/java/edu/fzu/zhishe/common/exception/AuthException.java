package edu.fzu.zhishe.common.exception;

/**
 * Unauthorized exception (401)
 * @author liang on 5/1/2020.
 * @version 1.0
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
