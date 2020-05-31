package edu.fzu.zhishe.core.service.impl;

import edu.fzu.zhishe.core.service.FmsLikeCacheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/31/2020
 */
@SpringBootTest
public class RedisServiceImplTest {

    @Autowired
    FmsLikeCacheService likeCacheService;

    @Test
    public void test() {
        likeCacheService.deletePostLikeSet(3L);
    }
}
