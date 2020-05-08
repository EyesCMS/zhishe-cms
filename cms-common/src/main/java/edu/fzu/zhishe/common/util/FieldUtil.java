package edu.fzu.zhishe.common.util;

import java.lang.reflect.Field;

/**
 * @author liang on 5/4/2020.
 * @version 1.0
 */
public class FieldUtil {

    public static boolean hasNotNullFiled(Object obj) throws IllegalAccessException {
        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (f.get(obj) != null) {
                return true;
            }
        }
        return false;
    }
}
