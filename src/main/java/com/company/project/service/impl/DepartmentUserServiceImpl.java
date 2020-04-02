package com.company.project.service.impl;

import com.company.project.dao.DepartmentUserMapper;
import com.company.project.model.DepartmentUser;
import com.company.project.service.DepartmentUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/02.
 */
@Service
@Transactional
public class DepartmentUserServiceImpl extends AbstractService<DepartmentUser> implements DepartmentUserService {
    @Resource
    private DepartmentUserMapper departmentUserMapper;

}
