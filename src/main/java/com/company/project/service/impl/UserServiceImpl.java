package com.company.project.service.impl;

import com.company.project.dao.DepartmentUserMapper;
import com.company.project.dao.UserMapper;
import com.company.project.model.Department;
import com.company.project.model.DepartmentUser;
import com.company.project.model.User;
import com.company.project.model.UserVo;
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
            UserVo vo = new UserVo(ele);
            vo.setDepartment(departmentService.findById(departmentUserService.findBy("id",ele.getId()).getDid()).getName());
            voList.add(vo);
        }
        return voList;
    }
}
