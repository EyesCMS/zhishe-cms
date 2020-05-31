package edu.fzu.zhishe.core.schedule;

import edu.fzu.zhishe.core.schedule.task.ExpireActivityTask;
import edu.fzu.zhishe.core.schedule.task.CreditTodayTask;
import edu.fzu.zhishe.core.schedule.task.ExpireApplyTask;
import edu.fzu.zhishe.core.schedule.task.LikeTask;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author liang on 5/18/2020.
 * @version 1.0
 */
@Component
public class ScheduleAllTask {

    private static final String LIKE_TASK_IDENTITY = "LikeTaskQuartz";
    private static final String ACTIVITY_TASK_IDENTITY = "ActivityTaskQuartz";
    private static final String CREDIT_TASK_IDENTITY = "CreditTaskQuartz";
    private static final String EXPIRE_APPLY_TASK_IDENTITY = "ExpireApplyTaskQuartz";

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }

    public void run() throws SchedulerException {

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        likeTask(scheduler);
        expireActivityTask(scheduler);
        creditTask(scheduler);
        expireApplyTaskIdentity(scheduler);
    }

    private void likeTask(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(LikeTask.class).withIdentity("job1")
            .storeDurably().build();
        // every 2 hours
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 */2 ? * *");
        // start every 5 min (used for test)
         CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */2 * ? * *");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
            .withIdentity(LIKE_TASK_IDENTITY)
            .withSchedule(scheduleBuilder)
            .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private void expireActivityTask(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(ExpireActivityTask.class).withIdentity("job2")
            .storeDurably().build();
        // start at 1:00 am
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 1 * * ?");
        // start every 2 min (used for test)
//         CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * ? * *");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
            .withIdentity(ACTIVITY_TASK_IDENTITY)
            .withSchedule(scheduleBuilder)
            .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private void creditTask(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(CreditTodayTask.class).withIdentity("job3")
            .storeDurably().build();
        // start at 12:05 am
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 5 0 * * ?");
        // start every 1 min (used for test)
//         CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */1 * ? * *");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
            .withIdentity(CREDIT_TASK_IDENTITY)
            .withSchedule(scheduleBuilder)
            .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    private void expireApplyTaskIdentity(Scheduler scheduler) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(ExpireApplyTask.class).withIdentity("job4")
            .storeDurably().build();
        // start every 12 hours
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 */12 ? * *");

//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 */2 * ? * *");

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
            .withIdentity(EXPIRE_APPLY_TASK_IDENTITY)
            .withSchedule(scheduleBuilder)
            .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
