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
        resourceType.setIntroduction(data.get("fileurl"));
        resourceType.setResourceName(data.get("name"));
        resourceType.setRegTime(new Date());
        resourceTypeService.save(resourceType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        resourceTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(ResourceType resourceType) {
        resourceTypeService.update(resourceType);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        ResourceType resourceType = resourceTypeService.findById(id);
        return ResultGenerator.genSuccessResult(resourceType);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ResourceType> list = resourceTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
