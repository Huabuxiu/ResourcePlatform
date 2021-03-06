package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.Department;
import com.company.project.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @PostMapping("/add")
    public Result add(@RequestBody Map<String,String> data) {
        Department department = new Department();
        department.setName(data.get("name"));
        department.setIntroduce(data.get("introduce"));
        department.setRegTime(new Date());
        departmentService.save(department);
        return ResultGenerator.genSuccessResult().setMessage("部门新增成功");
    }

    @PostMapping("/update")
    public Result update(@RequestBody Map<String,String> data) {
        Department department = departmentService.findById(Integer.parseInt(data.get("did")));
        if (department== null){
            return ResultGenerator.genFailResult("部门不存在");
        }
        department.setName(data.get("name"));
        department.setIntroduce(data.get("introduce"));
        department.setRegTime(new Date());
        departmentService.update(department);
        return ResultGenerator.genSuccessResult().setMessage("部门更新成功");
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Map<String,Integer> data) {
        departmentService.deleteById(data.get("did"));
        return ResultGenerator.genSuccessResult().setMessage("删除成功");
    }

    @PostMapping("/list")
    public Result list() {
        List<Department> list = departmentService.findAll();
        return ResultGenerator.genSuccessResult(list);
    }
}
