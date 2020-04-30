package edu.fzu.zhishe.common.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import cn.hutool.json.JSONObject;
import edu.fzu.zhishe.common.api.AjaxResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author liang
 * @date 2020/4/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> handle(ApiException e) {
        JSONObject jsonObject = new JSONObject();
        if (e.getErrorCode() != null) {
            jsonObject.put("message", e.getErrorCode());
            return ResponseEntity.badRequest().body(e.getErrorCode());
        }
        jsonObject.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(jsonObject);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<JSONObject> handleException(EntityNotFoundException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
    }
}
