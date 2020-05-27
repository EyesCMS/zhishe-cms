package edu.fzu.zhishe.common.exception;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author liang
 * @date 2020/4/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity<Object> handle(AuthException e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(jsonObject);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handle(AccessDeniedException e) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonObject);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<JSONObject> handleException(EntityNotFoundException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<JSONObject> handleException(StorageFileNotFoundException ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonObject);
    }

    @ExceptionHandler({BindException.class, ConstraintViolationException.class})
    public ResponseEntity<JSONObject> validatorExceptionHandler(Exception e) {
        String msg = e instanceof BindException ? msgConverter(((BindException) e).getBindingResult())
            : msgConverter(((ConstraintViolationException) e).getConstraintViolations());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", msg);
        return ResponseEntity.badRequest().body(jsonObject);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JSONObject> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = "method argument does not valid";
        if (CollUtil.isNotEmpty(allErrors)) {
            ObjectError objectError = allErrors.get(0);
            message = objectError.getDefaultMessage();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        return ResponseEntity.badRequest().body(jsonObject);
    }

    /**
     * 校验消息转换拼接
     */
    public static String msgConverter(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        fieldErrors.forEach(fieldError -> sb.append(fieldError.getDefaultMessage()).append(","));

        return sb.deleteCharAt(sb.length() - 1).toString().toLowerCase();
    }

    private String msgConverter(Set<ConstraintViolation<?>> constraintViolations) {
        StringBuilder sb = new StringBuilder();
        constraintViolations.forEach(violation -> sb.append(violation.getMessage()).append(","));

        return sb.deleteCharAt(sb.length() - 1).toString().toLowerCase();
    }
}
