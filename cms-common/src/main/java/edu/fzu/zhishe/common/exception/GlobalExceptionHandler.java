package edu.fzu.zhishe.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author liang
 * @date 2020/4/19
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ResponseEntity.badRequest().body(e.getErrorCode());
        }
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
