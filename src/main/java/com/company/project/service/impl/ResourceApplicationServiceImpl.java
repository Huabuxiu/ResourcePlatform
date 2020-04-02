package com.company.project.service.impl;

import com.company.project.dao.ResourceApplicationMapper;
import com.company.project.model.ResourceApplication;
import com.company.project.service.ResourceApplicationService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/31.
 */
@Service
@Transactional
public class ResourceApplicationServiceImpl extends AbstractService<ResourceApplication> implements ResourceApplicationService {
    @Resource
    private ResourceApplicationMapper resourceApplicationMapper;

}
