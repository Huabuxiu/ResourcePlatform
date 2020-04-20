package com.company.project.service.impl;

import com.company.project.dao.HostQueueMapper;
import com.company.project.model.HostQueue;
import com.company.project.service.HostQueueService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/04/20.
 */
@Service
@Transactional
public class HostQueueServiceImpl extends AbstractService<HostQueue> implements HostQueueService {
    @Resource
    private HostQueueMapper hostQueueMapper;

}
