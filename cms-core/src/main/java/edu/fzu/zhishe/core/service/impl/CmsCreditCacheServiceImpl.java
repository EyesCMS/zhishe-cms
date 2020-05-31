package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.cms.mapper.CmsClubGradeMapper;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubGrade;
import edu.fzu.zhishe.core.service.CmsCreditCacheService;
import edu.fzu.zhishe.core.util.CreditUtil;
import edu.fzu.zhishe.core.util.RedisKeyUtil;
import edu.fzu.zhishe.security.service.RedisService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/25/2020
 */
@Service
public class CmsCreditCacheServiceImpl implements CmsCreditCacheService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CmsCreditCacheServiceImpl.class);

    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    private String redisDatabase;

    @Value("${redis.key.credit.today}")
    private String redisKeyCreditToday;

    @Autowired
    CmsClubMapper clubMapper;
    @Autowired
    CmsClubGradeMapper clubGradeMapper;

    @Override
    public boolean incrTodayCredit(Integer clubId, Integer userId, Long credit) {

        String key = redisDatabase + ":" + redisKeyCreditToday;
        String creditTodayKey = RedisKeyUtil.getCreditTodayKey(clubId, userId);
        return redisService.hIncr(key, creditTodayKey, credit).equals(credit);
    }

    @Override
    public Integer getTodayCredit(Integer clubId, Integer userId) {
        String key = redisDatabase + ":" + redisKeyCreditToday;
        String creditTodayKey = RedisKeyUtil.getCreditTodayKey(clubId, userId);
        Integer credit = (Integer) redisService.hGet(key,creditTodayKey);
        return credit;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void storeToDatabase() {

        List<Integer> lowerBounds = clubGradeMapper.selectByExample(null)
            .stream()
            .map(CmsClubGrade::getLowerLimit)
            .collect(Collectors.toList());
        final int[] count = {0};
        String redisKey = redisDatabase + ":" + redisKeyCreditToday;
        Map<Object, Object> creditTodayMap = redisService.hGetAll(redisKey);
        creditTodayMap.forEach((k, v) -> {
            String key = (String) k;
            String[] tokens = key.split(RedisKeyUtil.SEPARATOR);
            Integer clubId = Integer.valueOf(tokens[0]);
            // Integer userId = Integer.valueOf(tokens[1]);
            Integer creditDelta = (Integer) v;

            CmsClub oldClub = clubMapper.selectByPrimaryKey(clubId);
            if (oldClub != null) {
                CmsClub newRecord = new CmsClub();
                newRecord.setId(clubId);
                int newCredit = oldClub.getCredit() + creditDelta;
                newRecord.setCredit(newCredit);
                newRecord.setGradeId(CreditUtil.getGradeByCredit(lowerBounds, newCredit));
                count[0] += clubMapper.updateByPrimaryKeySelective(newRecord);
            }
            // 鸵鸟策略：忽略对错误的处理

            // delete processed entry
            redisService.hDel(redisKey, key);
        });

        LOGGER.info("write {} records from redis to database", count[0]);
    }
}
