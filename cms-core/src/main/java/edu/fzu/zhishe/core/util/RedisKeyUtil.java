package edu.fzu.zhishe.core.util;

/**
 * @author liang on 5/15/2020.
 * @version 1.0
 */
public class RedisKeyUtil {

    public static final String DATABASE = "zhishe";

    public static final String BIZ_POST = "post";
    public static final String BIZ_UPDATED = "updated";
    public static final String BIZ_LIKE = "like";
    public static final String BIZ_LIKE_COUNT = "count";
    public static final String BIZ_CREDIT = "credit";


    public static final String SEPARATOR = ":";

    public static String getUpdatedPostLikeKey() {
        // zhishe:like:updated:post ->  set:{postId}
        return DATABASE
            + SEPARATOR
            + BIZ_LIKE
            + SEPARATOR
            + BIZ_UPDATED
            + SEPARATOR
            + BIZ_POST;
    }

    public static String getLikeKey(Long postId) {
        // zhishe:like:post:{postId}  ->  set:{userId}
        return DATABASE
            + SEPARATOR
            + BIZ_LIKE
            + SEPARATOR
            + BIZ_POST
            + SEPARATOR
            + postId;
    }

    public static String getLikedCountKey() {
        // zhishe:like:count -> postId -> int
        return DATABASE
            + SEPARATOR
            + BIZ_LIKE
            + SEPARATOR
            + BIZ_LIKE_COUNT;
    }

    public static String getCreditTodayKey(Integer clubId, Integer userId) {
        // zhishe:credit:{clubId}:{userId}
        return DATABASE
            + SEPARATOR
            + BIZ_CREDIT
            + SEPARATOR
            + clubId
            + SEPARATOR
            + userId;
    }

    public static void main(String[] args) {
        System.out.println(getLikeKey(11L));
        System.out.println(getLikedCountKey());
        System.out.println(getCreditTodayKey(10012, 7));
    }
}
