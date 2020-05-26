package edu.fzu.zhishe.core.service;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/25/2020
 */
public interface CmsCreditCacheService {

    /**
     * 增加用户在某个社团的积分
     * @param clubId 待统计的社团 id
     * @param userId 待统计的用户 id
     * @param credit 积分数
     * @return 0 if success
     */
    boolean incrTodayCredit(Integer clubId, Integer userId, Long credit);
    /**
     * 获得用户在某个社团的积分
     * @param clubId 待统计的社团 id
     * @param userId 待统计的用户 id
     * @return 积分
     */
    Integer getTodayCredit(Integer clubId, Integer userId);

        /**
     * 将 cache 中的数据累加到数据库中
     */
    void storeToDatabase();
}
