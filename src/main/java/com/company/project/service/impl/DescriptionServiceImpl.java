package com.company.project.service.impl;

import com.company.project.dao.DescriptionMapper;
import com.company.project.model.Description;
import com.company.project.service.DescriptionService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
@Service
@Transactional
public class DescriptionServiceImpl extends AbstractService<Description> implements DescriptionService {
    @Resource
    private DescriptionMapper descriptionMapper;

}
