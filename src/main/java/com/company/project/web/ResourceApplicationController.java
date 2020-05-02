package com.company.project.web;
import com.alibaba.fastjson.JSON;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.*;
import com.company.project.service.*;
import com.company.project.service.impl.MailService;
import com.company.project.util.*;
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

    @Resource
    private HostQueueService hostQueueService;

    @Resource
    private UserService userService;

    @Resource
    private StartTimeService startTimeService;

    @Resource
    private MailService mailService;

    @Resource
    private FaileService faileService;

    private Map<Integer,Thread> threadMap = new HashMap<>();

    private static  Integer STIPULATED = 2; //同时最多使用人数

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
    public Result examine(@RequestBody Map<String,String> data) {
        ResourceApplication resourceApplication = resourceApplicationService.findById(Integer.parseInt(data.get("raid")));
        ResourceType resourceType = resourceTypeService.findById(resourceApplication.getRtid());
        ApplicationUser applicationUser = applicationUserService.findById(resourceApplication.getRaid());
        User user = userService.findById(resourceApplication.getUid());
        MailVo mailVo = new MailVo();
        mailVo.setSubject("资源审核结果");
        String mail = "";
        if (data.get("result").equals("refuse")){   //拒绝申请
            resourceApplication.setProgress("已拒绝");
            Faile faile = new Faile();
            faile.setRaid(resourceApplication.getRaid());
            faile.setReason(data.get("refuse_reason"));
            faileService.save(faile);
            resourceApplicationService.update(resourceApplication);
            ResourceApplicationVo vo = resourceApplicationService.getVo(resourceApplication);
            vo.setRefuse_reason(data.get("refuse_reason"));
            mail = "资源审核拒绝理由是：" + vo.getRefuse_reason();
            mailVo.setText(mail);
            mailVo.setTo(user.geteMail());
            mailService.sendMail(mailVo);
            return ResultGenerator.genSuccessResult(vo);
        }else if (data.get("result").equals("pass")){   //通过
            if(resourceType.getResourceName().equals("Hadoop大数据处理平台（20台服务器集群）")){
                //第1类型，在主节点上开户
                Condition hostInformationCondition = new Condition(HostInformation.class);
                hostInformationCondition.createCriteria().andEqualTo("rtid",resourceApplication.getRtid());
                List<HostInformation> hostInformations = hostInformationService.findByCondition(hostInformationCondition);
//                分配主机
                HostInformation hostInformation = null;
                if (hostInformations.size() == 1){
                     hostInformation = hostInformations.get(0);
                }else if (hostInformations.size() == 0){
                     return ResultGenerator.genFailResult("该资源类型暂无主机");
                }else {
                    hostInformation = hostInformations.get(new Random().nextInt(hostInformations.size()));
                }
                resourceApplication.setHiid(hostInformation.getHiid());
                applicationUser.setState("可用");
                String password = UUID.randomUUID().toString().replace("-","");
                applicationUser.setPassword(password);
                resourceApplication.setPort(hostInformation.getPort());
                resourceApplication.setPassDate(new Date());

                resourceApplication.setProgress("使用中");
                resourceApplicationService.update(resourceApplication);
                applicationUserService.update(applicationUser);
                //服务端去开通账号


                mail = "Hadoop大数据处理平台（20台服务器集群）资源审核通过\n资源申请编号:"+resourceApplication.getRaid()
                        +"\n主机IP:" + hostInformation.getAddress()
                        +"\n用户名:"+applicationUser.getUsername()
                        +"\n密码:"+applicationUser.getPassword();
                mailVo.setText(mail);
                mailVo.setTo(user.geteMail());
                mailService.sendMail(mailVo);
                //设置计时线程，到时间关闭账号
                HadoopTimeThread springtemThread = SpringUtil.getBean(HadoopTimeThread.class);
                HadoopTimeThread timeThread = new HadoopTimeThread();

                timeThread.setResourceApplicationService(springtemThread.getResourceApplicationService());
                timeThread.setApplicationUserService(springtemThread.getApplicationUserService());


                timeThread.setTime(resourceApplication.getTime());  //设置使用时间
                timeThread.setRaid(resourceApplication.getRaid());
                timeThread.start();
                return ResultGenerator.genSuccessResult(resourceApplicationService.getVo(resourceApplication));
            }
//            第四类型
            if(resourceType.getResourceName().equals("浪潮服务器（单台独占使用）")){
                //第四类型资源开户
                HostInformation hostInformation = hostInformationService.findById(Integer.parseInt(data.get("hiid")));
                resourceApplication.setHiid(hostInformation.getHiid());
                applicationUser.setState("可用");
                applicationUser.setPassword(data.get("password"));
                applicationUser.setUsername(data.get("username"));
                resourceApplication.setPort(hostInformation.getPort());
                resourceApplication.setPassDate(new Date());
                resourceApplication.setProgress("使用中");
                resourceApplicationService.update(resourceApplication);
                applicationUserService.update(applicationUser);
                //服务端去开通账号
                mail = "浪潮服务器（单台独占使用）资源审核通过\n资源申请编号:"+resourceApplication.getRaid()
                        +"\n主机IP:" + hostInformation.getAddress()
                        +"\n用户名:"+applicationUser.getUsername()
                        +"\n密码:"+applicationUser.getPassword();
                mailVo.setText(mail);
                mailVo.setTo(user.geteMail());
                mailService.sendMail(mailVo);
                HadoopTimeThread springtemThread = SpringUtil.getBean(HadoopTimeThread.class);
                HadoopTimeThread timeThread = new HadoopTimeThread();

                timeThread.setResourceApplicationService(springtemThread.getResourceApplicationService());
                timeThread.setApplicationUserService(springtemThread.getApplicationUserService());


                timeThread.setTime(resourceApplication.getTime());  //设置使用时间
                timeThread.setRaid(resourceApplication.getRaid());
                timeThread.start();
                return ResultGenerator.genSuccessResult(resourceApplicationService.getVo(resourceApplication));
            }
//            第二种类型,四需要排队的类型
            if(resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Linux单机）")
                    || resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Windows单机）")){
                //先查主机列表,选择最短的
                Condition hostqueueCondition = new Condition(HostQueue.class);
                hostqueueCondition.createCriteria().andEqualTo("rtid",resourceApplication.getRtid());
                hostqueueCondition.orderBy("totalTime").asc();
                List<HostQueue> hostQueueList = hostQueueService.findByCondition(hostqueueCondition);
                HostQueue hostQueue = hostQueueList.get(0);
//                分配主机
                HostInformation hostInformation = hostInformationService.findById(hostQueue.getHiid());
                resourceApplication.setHiid(hostInformation.getHiid());
//                判断入队还是立即执行
                if (hostQueue.getTotalUser() < STIPULATED) { //少于规定的人数,立即执行
//                   设置开始使用时间
                    StartTime startTime = new StartTime();
                    startTime.setRaid(resourceApplication.getRaid());
                    startTime.setStarttime(new Date());

                    applicationUser.setState("可用");
                    resourceApplication.setPort(hostInformation.getPort());
                    resourceApplication.setPassDate(new Date());
                    resourceApplication.setProgress("使用中");

//                  服务器去开户
                    String com = "useradd -m "+applicationUser.getUsername() +"&&(echo \""+ applicationUser.getPassword() +"\"; echo \""+ applicationUser.getPassword() +"\") |passwd "+applicationUser.getUsername();
                    Boolean result= ConnectLinuxCommand.connectLinux("123.206.255.202","root","Huabuxiu3817",com);
                    System.out.println(result);

                    //总时间和总用户
                    hostQueue.setTotalTime(hostQueue.getTotalTime()+resourceApplication.getTime());
                    hostQueue.setTotalUser(hostQueue.getTotalUser()+1);

                    hostQueueService.update(hostQueue);
                    resourceApplicationService.update(resourceApplication);
                    applicationUserService.update(applicationUser);
                    startTimeService.save(startTime);

                    GPUTimeThread SpringThread  = SpringUtil.getBean(GPUTimeThread.class);
                    GPUTimeThread gpuTimeThread = new GPUTimeThread();
                    System.out.println(resourceApplication.getRaid() + ":" + gpuTimeThread.getName() );
//                    设置spring service
                    gpuTimeThread.setStartTimeService(SpringThread.getStartTimeService());
                    gpuTimeThread.setResourceApplicationService(SpringThread.getResourceApplicationService());
                    gpuTimeThread.setHostQueueService(SpringThread.getHostQueueService());
                    gpuTimeThread.setApplicationUserService(SpringThread.getApplicationUserService());


                    gpuTimeThread.setTime(resourceApplication.getTime());  //设置使用时间
                    gpuTimeThread.setRaid(resourceApplication.getRaid());
                    gpuTimeThread.setHiid(hostInformation.getHiid());
                    gpuTimeThread.start();
                }else { //  排队
                    Queue<Integer> timeQueue = (Queue<Integer>) JSON.parseObject(hostQueue.getQueueElement(), LinkedList.class);
                    timeQueue.add(resourceApplication.getRaid());//入队

                    hostQueue.setTotalUser(hostQueue.getTotalUser()+1);
                    hostQueue.setTotalTime(hostQueue.getTotalTime()+resourceApplication.getTime());

                    hostQueue.setQueueSize(timeQueue.size());
                    hostQueue.setQueueElement(JSON.toJSONString(timeQueue));
                    //去服务器开户
//                    排队进入队尾
                    applicationUser.setState("禁用");
                    applicationUser.setPassword(data.get("password"));
                    applicationUser.setUsername(data.get("username"));
                    resourceApplication.setPort(hostInformation.getPort());
                    resourceApplication.setPassDate(new Date());
                    resourceApplication.setProgress("排队中");
                    resourceApplicationService.update(resourceApplication);
                    applicationUserService.update(applicationUser);
                    hostQueueService.update(hostQueue);
                }

                if (resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Linux单机）") ){
                    //                    设置邮件信息
                    mail = "GPU服务器（K80高速处理显卡，Linux单机）资源审核通过\n资源申请编号:"+resourceApplication.getRaid()
                            +"\n主机IP:" + hostInformation.getAddress()
                            +"\n用户名:"+applicationUser.getUsername()
                            +"\n密码:"+applicationUser.getPassword();
                }else {
                    mail = "GPU服务器（K80高速处理显卡，Windows单机）资源审核通过\n资源申请编号:"+resourceApplication.getRaid()
                            +"\n主机IP:" + hostInformation.getAddress()
                            +"\n用户名:"+applicationUser.getUsername()
                            +"\n密码:"+applicationUser.getPassword();
                }
                mailVo.setText(mail);
                mailVo.setTo(user.geteMail());
                mailService.sendMail(mailVo);
                return ResultGenerator.genSuccessResult(resourceApplicationService.getVo(resourceApplication));
            }

        }

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
//        Condition condition = new Condition(ResourceApplication.class);
//        condition.createCriteria().andEqualTo("progress","排队中");
        List<ResourceApplication> list = resourceApplicationService.findAll();
//        List<ResourceApplication> list = resourceApplicationService.findByCondition(condition);
//        condition.clear();
//        condition.createCriteria().andEqualTo("progress","使用中");
//        list.addAll(resourceApplicationService.findByCondition(condition));
//        condition.clear();
//        condition.createCriteria().andEqualTo("progress","待审核");
//        list.addAll(resourceApplicationService.findByCondition(condition));
//        condition.clear();
//        condition.createCriteria().andEqualTo("progress","已到期");
//        list.addAll(resourceApplicationService.findByCondition(condition));
        List<ResourceApplicationVo> voList = resourceApplicationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }


    //管理员申请记录
    @PostMapping("/rt_list")
    public Result rt_list(@RequestBody Map<String,Integer> data) {
        Integer rtid = data.get("rtid");
        Condition condition = new Condition(ResourceApplication.class);
        condition.createCriteria().andEqualTo("progress","排队中");
        condition.and().andEqualTo("rtid",rtid);
        List<ResourceApplication> list = resourceApplicationService.findByCondition(condition);
        condition.clear();
        condition.createCriteria().andEqualTo("progress","使用中");
        condition.and().andEqualTo("rtid",rtid);
        list.addAll(resourceApplicationService.findByCondition(condition));

        condition.clear();
        condition.createCriteria().andEqualTo("progress","待审核");
        condition.and().andEqualTo("rtid",rtid);
        list.addAll(resourceApplicationService.findByCondition(condition));

        condition.clear();
        condition.createCriteria().andEqualTo("progress","已到期");
        condition.and().andEqualTo("rtid",rtid);
        list.addAll(resourceApplicationService.findByCondition(condition));
        List<ResourceApplicationVo> voList = resourceApplicationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }


    //管理员申请记录
    @PostMapping("/statistics")
    public Result statistics() {
        StatisticsVo statisticsVo = new StatisticsVo();
        Integer waitExamine = 0;

        Integer examined = 0;

        Integer refuse= 0;


        Integer Hadoop= 0;

        Integer Linux= 0;

        Integer windows= 0;

        Integer langChao= 0;

        List<ResourceApplication> list = resourceApplicationService.findAll();
        for (ResourceApplication ele :
               list ) {
            ResourceType resourceType = resourceTypeService.findById(ele.getRtid());
            if (ele.getProgress().equals("审核中")){
                waitExamine++;
            }
            if (ele.getProgress().equals("已拒绝")){
                refuse++;
            }

            if (resourceType.getResourceName().equals("Hadoop大数据处理平台（20台服务器集群）")){
                Hadoop++;
            }else if (resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Linux单机）")){
                Linux++;
            }else if (resourceType.getResourceName().equals("GPU服务器（K80高速处理显卡，Windows单机）")){
                windows++;
            }else if (resourceType.getResourceName().equals("浪潮服务器（单台独占使用）")){
                langChao++;
            }
        }
        examined = list.size() - waitExamine;

        statisticsVo.setTotal(list.size());
        statisticsVo.setUserApplication(list.size());
        statisticsVo.setWaitExamine(waitExamine);
        statisticsVo.setRefuse(refuse);
        statisticsVo.setExamined(examined);
        statisticsVo.setHadoop(Hadoop);
        statisticsVo.setLinux(Linux);
        statisticsVo.setWindows(windows);
        statisticsVo.setLangChao(langChao);
        return ResultGenerator.genSuccessResult(statisticsVo);
    }



}
