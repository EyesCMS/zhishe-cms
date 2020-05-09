package edu.fzu.zhishe.common.api;

/**
 * 通用返回对象
 *
 * @author liang
 * @date 2020/4/22
 */
public class ErrorResponseBody<T> {

    private String message;

    protected ErrorResponseBody() {
    }

    protected ErrorResponseBody(String message) {
        this.message = message;
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> ErrorResponseBody<T> failed(IErrorCode errorCode) {
        return new ErrorResponseBody<T>(errorCode.getMessage());
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ErrorResponseBody<T> failed(String message) {
        return new ErrorResponseBody<T>(message);
    }

    /**
     * 失败返回结果
     */
    public static <T> ErrorResponseBody<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> ErrorResponseBody<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ErrorResponseBody<T> validateFailed(String message) {
        return new ErrorResponseBody<T>(message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ErrorResponseBody<T> unauthorized(String message) {
        return new ErrorResponseBody<T>(message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> ErrorResponseBody<T> unauthorized() {
        return new ErrorResponseBody<T>(ResultCode.UNAUTHORIZED.getMessage());
    }

    /**
     * 未授权返回结果
     */
    public static <T> ErrorResponseBody<T> forbidden(String message) {
        return new ErrorResponseBody<T>(message);
    }

    /**
     * 未授权返回结果
     */
    public static <T> ErrorResponseBody<T> forbidden() {
        return new ErrorResponseBody<T>(ResultCode.FORBIDDEN.getMessage());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
