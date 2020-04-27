package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.HostInformation;
import com.company.project.model.HostInformationVo;
import com.company.project.model.ResourceType;
import com.company.project.service.HostInformationService;
import com.company.project.service.ResourceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/15.
*/
@RestController
@RequestMapping("/host_information")
public class HostInformationController {
    @Resource
    private HostInformationService hostInformationService;

    @Resource
    private ResourceTypeService resourceTypeService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        ResourceType resourceType = resourceTypeService.findBy("resourceName",data.get("resource_name"));
        HostInformation hostInformation = new HostInformation();
        hostInformation.setAddress(data.get("address"));
        hostInformation.setName(data.get("host_name"));
        if (resourceType!= null){
            hostInformation.setRtid(resourceType.getRtid());
        }else {
            return ResultGenerator.genFailResult("资源类型不存在");
        }
        if (resourceType.getResourceName().contains("Windows")){
            hostInformation.setPort("远程桌面3389");
        }else {
            hostInformation.setPort("ssh22");
        }
        if (!data.get("port").equals("默认")){
            hostInformation.setPort(hostInformation.getPort()+","+data.get("port"));
        }
        hostInformation.setRegTime(new Date());
        hostInformationService.save(hostInformation);
        return ResultGenerator.genSuccessResult().setMessage("新增主机信息成功");
    }


//    简单删除
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Integer> data) {
        hostInformationService.deleteById(data.get("hiid"));
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String,String> data) {
        ResourceType resourceType = resourceTypeService.findBy("resourceName",data.get("resource_name"));
        HostInformation hostInformation = hostInformationService.findById(Integer.parseInt(data.get("hiid")));
        hostInformation.setAddress(data.get("address"));
        hostInformation.setName(data.get("host_name"));
        if (resourceType!= null){
            hostInformation.setRtid(resourceType.getRtid());
        }else {
            return ResultGenerator.genFailResult("资源类型不存在");
        }
        hostInformation.setPort(data.get("port"));
        hostInformation.setRegTime(new Date());
        hostInformationService.update(hostInformation);
        return ResultGenerator.genSuccessResult().setMessage("更新成功");
    }


    @PostMapping("/list")
    public Result list() {
        List<HostInformation> list = hostInformationService.findAll();
        List<HostInformationVo> voList = hostInformationService.getVoList(list);
        return ResultGenerator.genSuccessResult(voList);
    }

}
