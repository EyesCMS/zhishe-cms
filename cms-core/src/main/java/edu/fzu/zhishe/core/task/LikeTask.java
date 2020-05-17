package edu.fzu.zhishe.core.task;

import edu.fzu.zhishe.core.service.FmsUserLikeService;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author liang
 */
@Slf4j
public class LikeTask extends QuartzJobBean {

    @Autowired
    FmsUserLikeService likeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("--------- start LikeTask ----------");

        // 将 Redis 里的点赞信息同步到数据库里
        likeService.transLikedFromRedis2DB();
        likeService.transLikedCountFromRedis2DB();
        log.info("--------- end LikeTask ----------");
    }
}
