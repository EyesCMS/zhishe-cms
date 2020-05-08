package edu.fzu.zhishe.common.util;

import java.util.UUID;

/**
 * @author liang
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
