package com.xxnx.util;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.TimeUnit;

public class MySchedule implements ServletContextListener {
    private Logger logger = LoggerFactory
            .getLogger(MySchedule.class);

    public static Scheduler scheduler = null;

  @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("Web应用开始...");

        /* 注册定时任务 */
        try {
            // 获取Scheduler实例
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            // 具体任务
            JobDetail job  = JobBuilder.newJob(zhouyuebaoJob.class).withIdentity("job1", "group1").build();


            // 触发时间点
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder
                    .simpleSchedule().withIntervalInSeconds(5).repeatForever();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                    .startNow()//立即生效
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 15 10 ? * MON")
                    ).build();//一直执行


            JobDetail job2  = JobBuilder.newJob(qiandaoJob.class).withIdentity("job2", "group2").build();
            Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup2")
                    .startNow()//立即生效
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 0 0/5 * * ? *")
                    ).build();
            // 交由Scheduler安排触发

            JobDetail job3  = JobBuilder.newJob(qiandaoJob.class).withIdentity("job3", "group3").build();
            Trigger trigger3 = TriggerBuilder.newTrigger().withIdentity("trigger3", "triggerGroup3")
                    .startNow()//立即生效
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("0 15 10 L * ?")
                    ).build();

            scheduler.scheduleJob(job, trigger);
            scheduler.scheduleJob(job2, trigger2);
            scheduler.scheduleJob(job3, trigger3);


            logger.info("调度器开始注册：The scheduler register...");
        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Web应用停止...");

        /* 注销定时任务 */
        try {
            // 关闭Scheduler
            scheduler.shutdown();

            logger.info("调度器已关闭：The scheduler shutdown...");
        } catch (SchedulerException se) {
            logger.error(se.getMessage(), se);
        }
    }
}
