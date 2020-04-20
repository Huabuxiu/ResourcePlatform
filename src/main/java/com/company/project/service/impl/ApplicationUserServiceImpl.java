package com.company.project.service.impl;

import com.company.project.dao.ApplicationUserMapper;
import com.company.project.model.ApplicationUser;
import com.company.project.service.ApplicationUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/20.
 */
@Service
@Transactional
public class ApplicationUserServiceImpl extends AbstractService<ApplicationUser> implements ApplicationUserService {
    @Resource
    private ApplicationUserMapper applicationUserMapper;

}
