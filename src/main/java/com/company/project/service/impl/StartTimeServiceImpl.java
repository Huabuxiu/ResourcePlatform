package com.company.project.service.impl;

import com.company.project.dao.StartTimeMapper;
import com.company.project.model.StartTime;
import com.company.project.service.StartTimeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/25.
 */
@Service
@Transactional
public class StartTimeServiceImpl extends AbstractService<StartTime> implements StartTimeService {
    @Resource
    private StartTimeMapper startTimeMapper;

}
