package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ResourceType;
import com.company.project.service.ResourceTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* Created by CodeGenerator on 2020/03/15.
*/
@RestController
@RequestMapping("/resource_type")
public class ResourceTypeController {
    @Resource
    private ResourceTypeService resourceTypeService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        ResourceType resourceType = new ResourceType();
        resourceType.setImage(data.get("image_url"));
        resourceType.setIntroduction(data.get("introduction"));
        resourceType.setResourceName(data.get("resource_name"));
        resourceType.setFile(data.get("file_url"));
        resourceType.setRegTime(new Date());
        resourceTypeService.save(resourceType);
        return ResultGenerator.genSuccessResult().setMessage("新增资源类型成功");
    }

//    删除
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Integer> data) {
        resourceTypeService.deleteById(data.get("rtid"));
        return ResultGenerator.genSuccessResult().setMessage("删除成功");
    }


    @PostMapping("/update")
    public Result update(@RequestBody Map<String,String> data) {
        ResourceType resourceType = resourceTypeService.findById(Integer.parseInt(data.get("rtid")));
        if (resourceType == null){
            return ResultGenerator.genFailResult("资源类型不存在");
        }
        if (!data.get("image_url").equals("")){
            resourceType.setImage(data.get("image_url"));
        }
        resourceType.setIntroduction(data.get("introduction"));
        resourceType.setResourceName(data.get("resource_name"));
        if (!data.get("file_url").equals("")){
            resourceType.setFile(data.get("file_url"));
        }
        resourceType.setRegTime(new Date());
        resourceTypeService.update(resourceType);
        return ResultGenerator.genSuccessResult().setMessage("更新成功");
    }


    @PostMapping("/list")
    public Result list() {
        List<ResourceType> list = resourceTypeService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
