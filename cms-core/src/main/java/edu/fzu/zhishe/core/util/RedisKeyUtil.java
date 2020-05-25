package edu.fzu.zhishe.core.util;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
public class RedisKeyUtil {

    public static final String KEY_SEPARATOR = "::";

    public static String getLikedKey(Integer userId, Long postId) {
        return userId + KEY_SEPARATOR + postId;
    }

    public static String getCreditTodayKey(Integer clubId, Integer userId) {
        return clubId + KEY_SEPARATOR + userId;
    }
}
