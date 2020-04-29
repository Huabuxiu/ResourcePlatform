package com.company.project.util;

import com.company.project.model.ApplicationUser;
import com.company.project.model.HostInformation;
import com.company.project.model.ResourceApplication;
import com.company.project.service.ApplicationUserService;
import com.company.project.service.HostInformationService;
import com.company.project.service.ResourceApplicationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class HadoopTimeThread extends Thread {


    public int time;    //单位是天

    public int raid;

    @Resource
    ResourceApplicationService resourceApplicationService;

    @Resource
    ApplicationUserService applicationUserService;

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

    @Override
    public  void run() {
        synchronized(this){
            try {
                long times  = time * 60 * 60 * 24;
                System.out.println(times);
                for (int i = 0; i < 1000 ;i++){
                    wait(times); //运行单位，天
                }

//                wait(time  * 1000 ); //测试单位，秒级
                //资源到期后的操作
                ResourceApplication  resourceApplication = resourceApplicationService.findById(getRaid());
                ApplicationUser applicationUser = applicationUserService.findById(getRaid());
                applicationUser.setState("禁用");
                resourceApplication.setProgress("已到期"); // 该条申请状态
                applicationUserService.update(applicationUser);
                resourceApplicationService.update(resourceApplication);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
