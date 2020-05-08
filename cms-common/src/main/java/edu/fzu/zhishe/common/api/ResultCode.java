package edu.fzu.zhishe.common.api;

/**
 * 枚举了一些常用API操作码
 *
 * @author liang
 * @date 2020/4/22
 */
public enum ResultCode implements IErrorCode {
    SUCCESS("200", "Success"),
    FAILED("500", "Server error"),
    VALIDATE_FAILED("404", "Validation failed"),
    UNAUTHORIZED("401", "Full authentication is required to access this resource"),
    FORBIDDEN("403", "Access is denied");
    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
