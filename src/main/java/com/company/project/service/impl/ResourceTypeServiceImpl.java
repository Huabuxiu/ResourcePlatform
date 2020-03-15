package com.company.project.service.impl;

import com.company.project.dao.ResourceTypeMapper;
import com.company.project.model.ResourceType;
import com.company.project.service.ResourceTypeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
@Service
@Transactional
public class ResourceTypeServiceImpl extends AbstractService<ResourceType> implements ResourceTypeService {
    @Resource
    private ResourceTypeMapper resourceTypeMapper;

}
