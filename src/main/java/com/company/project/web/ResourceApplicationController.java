package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.*;
import com.company.project.service.*;
import com.company.project.util.SpringUtil;
import com.company.project.util.TimeThread;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.*;

/**
* Created by CodeGenerator on 2020/04/20.
*/
@RestController
@RequestMapping("/resource_application")
public class ResourceApplicationController {
    @Resource
    private ResourceApplicationService resourceApplicationService;

    @Resource
    private HostHolder hostHolder;

    @Resource
    private ResourceTypeService resourceTypeService;

    @Resource
    private DepartmentUserService departmentUserService;

    @Resource
    private HostInformationService hostInformationService;

    @Resource
    private ApplicationUserService applicationUserService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        ResourceApplication resourceApplication = new ResourceApplication();
        User user = hostHolder.getUser();
        ResourceType resourceType = resourceTypeService.findById(Integer.parseInt(data.get("rtid")));
        ApplicationUser applicationUser = new ApplicationUser();

        //默认所有都有设置部分
        resourceApplication.setUid(user.getId()); //用户
        resourceApplication.setDid(departmentUserService.findBy("id",user.getId()).getDid()); //部门id
        resourceApplication.setCreateDate(new Date());  //申请时间
        resourceApplication.setPurpose(data.get("purpose"));    //用途
        if (data.get("username")!= null){
            applicationUser.setUsername(data.get("username"));  //用户名
        }
        applicationUser.setState("禁用");     //当前服务器账号状态
        resourceApplication.setTime(Integer.parseInt(data.get("time")));        //使用时长
        resourceApplication.setRtid(resourceType.getRtid());

        //区别部分设置
        resourceApplication.setOperatingSystem("Linux"); //操作系统
        applicationUser.setPassword("default");     // 用户密码
        resourceApplication.setPort("default");     //服务器使用接口
        resourceApplication.setHiid(hostInformationService.findAll().get(0).getHiid());            // 分配的主机
        resourceApplication.setProgress("审核中"); // 该条申请状态
        if(resourceType.getResourceName().equals("Hadoop大数据处理平台（20台服务器集群）")){
            //第1类型
        }
        if (resourceType.getResourceName().equals("浪潮服务器（单台独占使用）")){
            //第4类型
            applicationUser.setUsername("default");
            resourceApplication.setOperatingSystem(data.get("operating_system"));   //操作系统
            resourceApplication.setPort(data.get("port"));     //服务器使用接口
        }
        if (resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Linux单机）")){
//            第2类型
            applicationUser.setPassword(data.get("password"));     // 用户密码
        }
        if (resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Windows单机）")){
//          第3类型
            applicationUser.setPassword(data.get("password"));     // 用户密码
        }
        resourceApplicationService.insertResourceApplication(resourceApplication);  //获取自增主键的值
        System.out.println(resourceApplication.getCreateDate().toString());
        applicationUser.setRaid(resourceApplication.getRaid()); //设置主键
        applicationUserService.save(applicationUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        resourceApplicationService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ResourceApplication resourceApplication) {
        resourceApplicationService.update(resourceApplication);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody Map<String,Integer> data) {
        ResourceApplication resourceApplication = resourceApplicationService.findById(data.get("raid"));
        ResourceApplicationVo vo  =  resourceApplicationService.getVo(resourceApplication);
        return ResultGenerator.genSuccessResult(vo);
    }

    /*
    管理员查看待审核页面
     */
    @PostMapping("/examine_list")
    public Result examine_list() {
        Condition condition = new Condition(ResourceApplication.class);
        condition.createCriteria().andEqualTo("progress","审核中");
        List<ResourceApplication> list = resourceApplicationService.findByCondition(condition);
        List<ResourceApplicationVo> voList = resourceApplicationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }


    /*
管理员审核用户申请
 */
    @PostMapping("/examine")
    public Result examine(@RequestBody Map<String,Integer> data) {
        TimeThread timeThread =  SpringUtil.getBean(TimeThread.class);
        timeThread.setCount(5);
        timeThread.start();
        return ResultGenerator.genSuccessResult();
    }




//用户申请记录
    @PostMapping("/list")
    public Result list() {
        User user = hostHolder.getUser();
        Condition condition = new Condition(ResourceApplication.class);
        condition.createCriteria().andEqualTo("uid",user.getId());
        List<ResourceApplication> list = resourceApplicationService.findByCondition(condition);
        List<ResourceApplicationVo> voList = resourceApplicationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }


    //管理员申请记录
    @PostMapping("/all_list")
    public Result all_list() {
        Condition condition = new Condition(ResourceApplication.class);
        condition.createCriteria().andEqualTo("progress","排队中");
        List<ResourceApplication> list = resourceApplicationService.findByCondition(condition);
        condition.clear();
        condition.createCriteria().andEqualTo("progress","使用中");
        list.addAll(resourceApplicationService.findByCondition(condition));
        List<ResourceApplicationVo> voList = resourceApplicationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }




}
