package com.company.project.service.impl;

import com.company.project.dao.ResourceApplicationMapper;
import com.company.project.model.ResourceApplication;
import com.company.project.model.ResourceApplicationVo;
import com.company.project.model.ResourceType;
import com.company.project.service.*;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/04/20.
 */
@Service
@Transactional
public class ResourceApplicationServiceImpl extends AbstractService<ResourceApplication> implements ResourceApplicationService {
    @Resource
    private ResourceApplicationMapper resourceApplicationMapper;

    @Resource
    private ResourceTypeService resourceTypeService;

    @Resource
    private UserService userService;

    @Resource
    private HostInformationService hostInformationService;

    @Resource
    private ApplicationUserService applicationUserService;

    @Resource
    private FaileService faileService;

    @Resource
    private StartTimeService startTimeService;

    @Override
    public ResourceApplication getResourceApplication(Date createDate) {
        return resourceApplicationMapper.getResourceApplication(createDate);
    }

    @Override
    public Integer insertResourceApplication(ResourceApplication resourceApplication) {
        return resourceApplicationMapper.insertResourceApplication(resourceApplication);
    }

    @Override
    public List<ResourceApplicationVo> getVoList(List<ResourceApplication> list) {
        List<ResourceApplicationVo> voList = new ArrayList<>();
        if (list.size() == 0){
            return voList;
        }
        for (ResourceApplication ele :
                list) {
            ResourceApplicationVo vo = new ResourceApplicationVo(ele);
            vo.setResourceType(resourceTypeService.findById(ele.getRtid()).getResourceName());
            vo.setUser_name(userService.findById(ele.getUid()).getName());
            vo.setHost_name(hostInformationService.findById(ele.getHiid()).getName());
            vo.setHost_username(applicationUserService.findById(ele.getRaid()).getUsername());
            vo.setHost_password(applicationUserService.findById(ele.getRaid()).getPassword());
            vo.setResourceTypeImage(resourceTypeService.findById(ele.getRtid()).getImage());
            //剩余时间
            if (ele.getProgress().equals("已拒绝")){
                vo.setRefuse_reason(faileService.findById(ele.getRaid()).getReason());
                vo.setRemaining_time(0);
            }
            if (ele.getProgress().equals("已到期")){
                vo.setRemaining_time(0);
            }
            if (ele.getProgress().equals("审核中") || ele.getProgress().equals("排队中") ){
                vo.setRemaining_time(ele.getTime());
            }
            if (ele.getProgress().equals("使用中")){
                if (vo.getResourceType().equals("Hadoop大数据处理平台（20台服务器集群）")
                        || vo.getResourceType().equals("浪潮服务器（单台独占使用）")){
                   Date start =   ele.getPassDate();
                   Date end = addDays(start,ele.getTime());
                   vo.setRemaining_time(daysBetween(new Date(),end));   //剩余天数
                }else { //需要排队的两种
                    Date start  = startTimeService.findBy("raid",ele.getRaid()).getStarttime();
                    Date end = addDays(start,ele.getTime());
                    vo.setRemaining_time(daysBetween(new Date(),end));   //剩余天数
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public ResourceApplicationVo getVo(ResourceApplication ele) {

        ResourceApplicationVo vo = new ResourceApplicationVo(ele);
        vo.setResourceType(resourceTypeService.findById(ele.getRtid()).getResourceName());
        vo.setUser_name(userService.findById(ele.getUid()).getName());
        vo.setHost_name(hostInformationService.findById(ele.getHiid()).getName());
        vo.setHost_username(applicationUserService.findById(ele.getRaid()).getUsername());
        vo.setHost_password(applicationUserService.findById(ele.getRaid()).getPassword());
        vo.setResourceTypeImage(resourceTypeService.findById(ele.getRtid()).getImage());
        //剩余时间
        if (ele.getProgress().equals("已拒绝")){
            vo.setRefuse_reason(faileService.findById(ele.getRaid()).getReason());
            vo.setRemaining_time(0);
        }
        if (ele.getProgress().equals("已到期")){
            vo.setRemaining_time(0);
        }
        if (ele.getProgress().equals("审核中") || ele.getProgress().equals("排队中") ){
            vo.setRemaining_time(ele.getTime());
        }
        if (ele.getProgress().equals("使用中")){
            if (vo.getResourceType().equals("Hadoop大数据处理平台（20台服务器集群）")
                    || vo.getResourceType().equals("浪潮服务器（单台独占使用）")){
                Date start =   ele.getPassDate();
                Date end = addDays(start,ele.getTime());
                vo.setRemaining_time(daysBetween(new Date(),end));   //剩余天数
            }else { //需要排队的两种
                Date start  = startTimeService.findBy("raid",ele.getRaid()).getStarttime();
                Date end = addDays(start,ele.getTime());
                vo.setRemaining_time(daysBetween(new Date(),end));   //剩余天数
            }
        }
        return vo;
    }


    //   加上多少天后的时间
    public  Date addDays(Date start ,Integer time){
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.DAY_OF_MONTH,+time);
       return cal.getTime();
    }


    //计算两个日期之间相差的天数
    public  int daysBetween(Date date1,Date date2)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }
}
