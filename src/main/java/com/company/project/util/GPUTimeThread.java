package com.company.project.util;

import com.alibaba.fastjson.JSON;
import com.company.project.model.ApplicationUser;
import com.company.project.model.HostQueue;
import com.company.project.model.ResourceApplication;
import com.company.project.model.StartTime;
import com.company.project.service.ApplicationUserService;
import com.company.project.service.HostQueueService;
import com.company.project.service.ResourceApplicationService;
import com.company.project.service.StartTimeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

@Component
public class GPUTimeThread extends Thread {


    public int time;    //单位是天

    public int raid;

    public int hiid;

    @Resource
    ResourceApplicationService resourceApplicationService;

    @Resource
    ApplicationUserService applicationUserService;

    @Resource
    HostQueueService hostQueueService;

    @Resource
    StartTimeService startTimeService;




    public StartTimeService getStartTimeService() {
        return startTimeService;
    }

    public void setStartTimeService(StartTimeService startTimeService) {
        this.startTimeService = startTimeService;
    }

    public int getHiid() {
        return hiid;
    }

    public void setHiid(int hiid) {
        this.hiid = hiid;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getRaid() {
        return raid;
    }

    public void setRaid(int raid) {
        this.raid = raid;
    }

    public ResourceApplicationService getResourceApplicationService() {
        return resourceApplicationService;
    }

    public void setResourceApplicationService(ResourceApplicationService resourceApplicationService) {
        this.resourceApplicationService = resourceApplicationService;
    }

    public ApplicationUserService getApplicationUserService() {
        return applicationUserService;
    }

    public void setApplicationUserService(ApplicationUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    public HostQueueService getHostQueueService() {
        return hostQueueService;
    }

    public void setHostQueueService(HostQueueService hostQueueService) {
        this.hostQueueService = hostQueueService;
    }

    @Override
    public  void run() {

            try {
                synchronized(this) {
                    long times  = time * 60 * 60 * 24;
                    System.out.println(times);
                    for (int i = 0; i < 1000 ;i++){
                        wait(times); //运行单位，天
                    }
//                    wait(getTime() * 1000); //测试单位，秒级
                }
                //资源到期后的操作
                ResourceApplication  resourceApplication = resourceApplicationService.findById(getRaid());
                ApplicationUser applicationUser = applicationUserService.findById(getRaid());
                HostQueue hostQueue  = hostQueueService.findById(getHiid());
                Queue<Integer> timeQueue = (Queue<Integer>) JSON.parseObject(hostQueue.getQueueElement(), LinkedList.class);



//                到期减人数减时间
                hostQueue.setTotalUser(hostQueue.getTotalUser()-1);
                hostQueue.setTotalTime(hostQueue.getTotalTime()-resourceApplication.getTime());

                applicationUser.setState("禁用");
                resourceApplication.setProgress("已到期"); // 该条申请状态

                applicationUserService.update(applicationUser);
                resourceApplicationService.update(resourceApplication);
                hostQueueService.update(hostQueue);

                System.out.println(Thread.currentThread().getName()+"：到期 raid:" + resourceApplication.getRaid() );

                if (timeQueue.size() != 0){ //有在排队的就出队
                    Integer nextraid = timeQueue.poll();    //出队
                    ResourceApplication  nextApplication = resourceApplicationService.findById(nextraid);
                    ApplicationUser nextUser = applicationUserService.findById(nextraid);

                    hostQueue.setQueueElement(JSON.toJSONString(timeQueue));
                    hostQueue.setQueueSize(timeQueue.size());
                    hostQueueService.update(hostQueue);
                    //队列后面的去执行

                    nextUser.setState("可用");
                    nextApplication.setProgress("使用中");

                    StartTime startTime = new StartTime();
                    startTime.setRaid(resourceApplication.getRaid());
                    startTime.setStarttime(new Date());
                    startTimeService.save(startTime);
                    resourceApplicationService.update(nextApplication);
                    applicationUserService.update(nextUser);
                    //当前队首出队之后去执行结果
                    GPUTimeThread child = new GPUTimeThread();
                    child.setApplicationUserService(getApplicationUserService());
                    child.setHiid(getHiid());
                    child.setHostQueueService(getHostQueueService());
                    child.setRaid(nextApplication.getRaid());
                    child.setResourceApplicationService(getResourceApplicationService());
                    child.setTime(nextApplication.getTime());
                    child.setStartTimeService(getStartTimeService());
                    child.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
