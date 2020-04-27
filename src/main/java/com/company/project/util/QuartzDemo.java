package com.company.project.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuartzDemo implements Job {



    /**
     * 任务被触发时所执行的方法
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("定时任务执行时间"+dateFormat.format(new Date()));
    }
}
