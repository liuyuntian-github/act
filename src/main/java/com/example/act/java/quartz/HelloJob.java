package com.example.act.java.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date now = new Date();
        String currentTime = sdf.format(now);
        System.out.println("执行时间为："+currentTime);
    }
}

