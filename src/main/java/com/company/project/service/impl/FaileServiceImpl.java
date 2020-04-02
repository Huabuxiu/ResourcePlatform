package com.company.project.service.impl;

import com.company.project.dao.FaileMapper;
import com.company.project.model.Faile;
import com.company.project.service.FaileService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/31.
 */
@Service
@Transactional
public class FaileServiceImpl extends AbstractService<Faile> implements FaileService {
    @Resource
    private FaileMapper faileMapper;

}
