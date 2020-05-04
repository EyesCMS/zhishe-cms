package edu.fzu.zhishe.common.exception;

import edu.fzu.zhishe.common.api.IErrorCode;
import edu.fzu.zhishe.common.util.FieldUtil;
import io.micrometer.core.lang.Nullable;

/**
 * 断言处理类，用于抛出各种 API 异常
 *
 * @author liang
 * @date 2020/4/22
 */
public class Asserts {

    /**
     * check if an object has at least one non-null filed
     */
    public static void hasFiled(Object object) {
        try {
            if (!FieldUtil.hasNotNullFiled(object)) {
                Asserts.fail("all fields is empty");
            }
        } catch (IllegalAccessException e) {
            Asserts.fail("check field failed");
        }
    }

    public static void notNull(@Nullable Object object) {
        if (object == null) {
            throw new EntityNotFoundException("Object isn't exist");
        }
    }

    public static void unAuthorized(String message) {
        throw new AuthException(message);
    }

    public static void unAuthorized() {
        throw new AuthException("Full authentication is required to access this resource");
    }

    public static void forbidden(String message) {
        throw new AccessException(message);
    }

    public static void forbidden() {
        throw new AccessException("Access is denied");
    }

    public static void notFound(boolean condition) {
        if (!condition) {
            throw new EntityNotFoundException("not found");
        }
    }

    public static void notFound() { throw new EntityNotFoundException("not found"); }

    public static void notFound(String message) { throw new EntityNotFoundException(message); }

    public static void fail() {
        throw new ApiException("操作失败");
    }

    public static void fail(String message) {
        throw new ApiException(message);
    }

    public static void fail(IErrorCode errorCode) {
        throw new ApiException(errorCode);
    }
}
