package edu.fzu.zhishe.core.schedule.task;

import edu.fzu.zhishe.core.service.CmsActivityService;
import edu.fzu.zhishe.security.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author liang
 */
@Slf4j
public class ExpireActivityTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 无法使用自动依赖注入
        CmsActivityService activityService = SpringUtil.getBean(CmsActivityService.class);

        log.info("<<< Start task");
        int count = activityService.deleteExpiredActivity();
        log.info("close {} activities", count);
        log.info(">>> End tasK");
    }
}
