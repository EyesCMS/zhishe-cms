package edu.fzu.zhishe.core.schedule.task;

import edu.fzu.zhishe.core.service.CmsApplyAuditService;
import edu.fzu.zhishe.core.service.CmsCreditCacheService;
import edu.fzu.zhishe.security.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author xjliang(gnulxj @ gmail.com)
 * @date 5/31/2020
 */
@Slf4j
public class ExpireApplyTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        // 无法使用自动依赖注入
        CmsApplyAuditService applyAuditService = SpringUtil.getBean(CmsApplyAuditService.class);

        log.info("<<< Start task");
        applyAuditService.updateExpiredApply();
        log.info(">>> End task");
    }
}
