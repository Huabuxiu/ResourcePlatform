package com.company.project.service.impl;

import com.company.project.dao.HostInformationMapper;
import com.company.project.model.HostInformation;
import com.company.project.service.HostInformationService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
@Service
@Transactional
public class HostInformationServiceImpl extends AbstractService<HostInformation> implements HostInformationService {
    @Resource
    private HostInformationMapper hostInformationMapper;

}
