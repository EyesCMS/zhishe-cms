package edu.fzu.zhishe.common.exception;

/**
 * @author liang on 4/28/2020.
 * @version 1.0
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
