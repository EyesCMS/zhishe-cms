package edu.fzu.zhishe.core.util;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
public class RedisKeyUtils {

    public static String getLikedKey(Integer userId, Long postId) {
        return userId + "::" + postId;
    }
}
