package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.ResourceApplication;
import com.company.project.service.ResourceApplicationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2020/03/31.
*/
@RestController
@RequestMapping("/resource/application")
public class ResourceApplicationController {
    @Resource
    private ResourceApplicationService resourceApplicationService;

    @PostMapping("/add")
    public Result add(ResourceApplication resourceApplication) {
        resourceApplicationService.save(resourceApplication);
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
    public Result detail(@RequestParam Integer id) {
        ResourceApplication resourceApplication = resourceApplicationService.findById(id);
        return ResultGenerator.genSuccessResult(resourceApplication);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<ResourceApplication> list = resourceApplicationService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
