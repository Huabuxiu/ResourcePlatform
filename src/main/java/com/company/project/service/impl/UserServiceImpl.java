package com.company.project.service.impl;

import com.company.project.dao.DepartmentUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.*;
import com.company.project.service.DepartmentService;
import com.company.project.service.DepartmentUserService;
import com.company.project.service.UserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/31.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private DepartmentUserService departmentUserService;

    @Resource
    private DepartmentService departmentService;


    @Override
    public List<UserVo> getUserVoList(List<User> list) {
        List<UserVo> voList = new ArrayList<>();

        for (User ele :
                list) {
            if (ele.getUserRole() == 1 && ele.getState() == 1){//过滤掉管理员 和 未审核人员
                UserVo vo = new UserVo(ele);
                vo.setDepartment(departmentService.findById(departmentUserService.findBy("id", ele.getId()).getDid()).getName());
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public List<UserVo> getExamineVoList(List<User> list) {
        List<UserVo> voList = new ArrayList<>();

        for (User ele :
                list) {
            if (ele.getUserRole() == 1 ){//过滤掉管理员
                UserVo vo = new UserVo(ele);
                vo.setDepartment(departmentService.findById(departmentUserService.findBy("id", ele.getId()).getDid()).getName());
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public UserVoTow getUserVo(User user) {
        UserVoTow userVo = new UserVoTow(user);
        userVo.setDepartment(departmentService.findById(departmentUserService.findBy("id",user.getId()).getDid()).getName());
        return userVo;
    }
}
