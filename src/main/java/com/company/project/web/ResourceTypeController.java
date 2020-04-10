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
@RequestMapping("/resource/type")
public class ResourceTypeController {
    @Resource
    private ResourceTypeService resourceTypeService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        ResourceType resourceType = new ResourceType();
        resourceType.setImage(data.get("imageurl"));
        resourceType.setIntroduction(data.get("introduction"));
        resourceType.setResourceName(data.get("name"));
        resourceType.setFile(data.get("fileurl"));
        resourceType.setRegTime(new Date());
        resourceTypeService.save(resourceType);
        return ResultGenerator.genSuccessResult();
    }

//    删除
    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Integer> data) {
        resourceTypeService.deleteById(data.get("rtid"));
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/update")
    public Result update(@RequestBody Map<String,String> data) {
        ResourceType resourceType = resourceTypeService.findById(Integer.parseInt(data.get("rtid")));
        if (!data.get("imageurl").equals("")){
            resourceType.setImage(data.get("imageurl"));
        }
        resourceType.setIntroduction(data.get("introduction"));
        resourceType.setResourceName(data.get("name"));
        if (!data.get("fileurl").equals("")){
            resourceType.setFile(data.get("fileurl"));
        }
        resourceType.setRegTime(new Date());
        resourceTypeService.update(resourceType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ResourceType resourceType = resourceTypeService.findById(id);
        return ResultGenerator.genSuccessResult(resourceType);
    }

    @PostMapping("/list")
    public Result list() {
        List<ResourceType> list = resourceTypeService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
