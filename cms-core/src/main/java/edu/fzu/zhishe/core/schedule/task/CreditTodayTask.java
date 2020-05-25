package edu.fzu.zhishe.core.schedule.task;

import edu.fzu.zhishe.core.service.CmsCreditCacheService;
import edu.fzu.zhishe.security.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author liang
 */
@Slf4j
public class CreditTodayTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 无法使用自动依赖注入
        CmsCreditCacheService creditCacheService = SpringUtil.getBean(CmsCreditCacheService.class);

        log.info("<<< Start task");
        // 将 Redis 里的点赞信息同步到数据库里
        creditCacheService.storeToDatabase();
        log.info(">>> End task");
    }
}
