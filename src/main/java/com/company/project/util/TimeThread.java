package com.company.project.util;

import com.company.project.model.HostInformation;
import com.company.project.service.HostInformationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TimeThread extends Thread {

    public int count;

    @Resource
    HostInformationService hostInformationService;

    public Map<Integer,Integer> hostqueuetime = new HashMap<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Map<Integer, Integer> getHostqueuetime() {
        return hostqueuetime;
    }

    public void setHostqueuetime(Map<Integer, Integer> hostqueuetime) {
        this.hostqueuetime = hostqueuetime;
    }


    public HostInformationService getHostInformationService() {
        return hostInformationService;
    }

    public void setHostInformationService(HostInformationService hostInformationService) {
        this.hostInformationService = hostInformationService;
    }

    @Override
    public void run() {
        System.out.println(count);
        List<HostInformation> hostInformations = hostInformationService.findAll();
        System.out.println(hostInformations.get(0).toString());

        count = count - 1;
        if (count != 0){
            TimeThread child = new TimeThread();
            child.setCount(getCount());
            child.setHostInformationService(getHostInformationService());
            child.start();
        }
    }
}
