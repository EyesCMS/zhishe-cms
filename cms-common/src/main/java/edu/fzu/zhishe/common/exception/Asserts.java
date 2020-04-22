package edu.fzu.zhishe.common.exception;

import edu.fzu.zhishe.common.api.IErrorCode;

/**
 * 断言处理类，用于抛出各种 API 异常
 *
 * @author liang
 * @date 2020/4/22
 */
public class Asserts {
    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
