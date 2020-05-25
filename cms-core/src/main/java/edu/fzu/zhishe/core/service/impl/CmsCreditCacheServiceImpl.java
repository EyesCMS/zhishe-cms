package edu.fzu.zhishe.core.service.impl;

import cn.hutool.core.collection.CollUtil;
import edu.fzu.zhishe.cms.mapper.CmsClubMapper;
import edu.fzu.zhishe.cms.mapper.CmsUserClubRelMapper;
import edu.fzu.zhishe.cms.model.CmsClub;
import edu.fzu.zhishe.cms.model.CmsClubExample;
import edu.fzu.zhishe.cms.model.CmsUserClubRel;
import edu.fzu.zhishe.cms.model.CmsUserClubRelExample;
import edu.fzu.zhishe.core.service.CmsCreditCacheService;
import edu.fzu.zhishe.core.util.RedisKeyUtil;
import edu.fzu.zhishe.security.service.RedisService;
import java.util.List;
import java.util.Map;
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

    @Autowired
    RedisService redisService;

    @Value("${redis.database}")
    private String redisDatabase;

    @Value("${redis.key.credit.today}")
    private String redisKeyCreditToday;

    @Autowired
    CmsClubMapper clubMapper;

    @Override
    public boolean incrTodayCredit(Integer clubId, Integer userId, Long credit) {

        String key = redisDatabase + ":" + redisKeyCreditToday;
        String creditTodayKey = RedisKeyUtil.getCreditTodayKey(clubId, userId);
        return redisService.hIncr(key, creditTodayKey, credit).equals(credit);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void storeToDatabase() {

        String redisKey = redisDatabase + ":" + redisKeyCreditToday;
        Map<Object, Object> creditTodayMap = redisService.hGetAll(redisKey);
        creditTodayMap.forEach((k, v) -> {
            String key = (String) k;
            String[] tokens = key.split(RedisKeyUtil.KEY_SEPARATOR);
            Integer clubId = Integer.valueOf(tokens[0]);
            // Integer userId = Integer.valueOf(tokens[1]);
            Integer creditDelta = (Integer) v;

            CmsClub oldClub = clubMapper.selectByPrimaryKey(clubId);
            if (oldClub != null) {
                CmsClub newRecord = new CmsClub();
                newRecord.setId(clubId);
                newRecord.setCredit(oldClub.getCredit() + creditDelta);
                clubMapper.updateByPrimaryKeySelective(newRecord);
            }
            // 鸵鸟策略：忽略对错误的处理

            // delete processed entry
            redisService.hDel(redisKey, key);
        });
    }
}
