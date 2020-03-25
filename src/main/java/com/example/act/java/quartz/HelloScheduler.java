package com.example.act.java.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
    public static void helloSc() throws SchedulerException {
        //创建jobDetail绑定HelloJob
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob","myGroup").build();
        //创建触发器trigger每个2秒执行一次，一直执行
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mtTrigger", "myGroup").startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();
        //创建调度者工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //创建调度者
        Scheduler scheduler = schedulerFactory.getScheduler();
        //启动调度器
        scheduler.start();
        //设置调度任务
        scheduler.scheduleJob(jobDetail, trigger);
    }
}

