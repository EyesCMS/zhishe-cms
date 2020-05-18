package edu.fzu.zhishe.core.config;

import edu.fzu.zhishe.core.schedule.ScheduleAllTask;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @author liang
 */
@Configuration
public class QuartzConfig implements ApplicationListener<ContextRefreshedEvent> {

   @Autowired
   ScheduleAllTask scheduleAllTask;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            scheduleAllTask.run();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
