package com.example.act.java.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CronScheduler {
    public static void main(String[] args) throws Exception {
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob").build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger")
                //cron表达式 这里定义的是4月16日早上9点21分开始执行
                .withSchedule(CronScheduleBuilder.cronSchedule("0 10 15 26 12 ? *"))
                .build();
        SchedulerFactory factory = new StdSchedulerFactory();
        //创建调度器
        Scheduler scheduler = factory.getScheduler();
        //启动调度器
        scheduler.start();
        //jobDetail和trigger加入调度
        scheduler.scheduleJob(jobDetail, trigger);
    }
}

