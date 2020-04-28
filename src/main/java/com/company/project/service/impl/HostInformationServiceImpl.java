package com.company.project.service.impl;

import com.company.project.dao.HostInformationMapper;
import com.company.project.model.HostInformation;
import com.company.project.model.HostInformationVo;
import com.company.project.service.HostInformationService;
import com.company.project.core.AbstractService;
import com.company.project.service.ResourceTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
@Service
@Transactional
public class HostInformationServiceImpl extends AbstractService<HostInformation> implements HostInformationService {
    @Resource
    private HostInformationMapper hostInformationMapper;
    
    @Resource
    private ResourceTypeService resourceTypeService;

    @Override
    public List<HostInformationVo> getVoList(List<HostInformation> list) {
        List<HostInformationVo> voList = new ArrayList<>();
        if (list.size() == 0){
            return voList;
        }

        for (HostInformation ele :
                list) {
            HostInformationVo vo = new HostInformationVo(ele);
            vo.setResourceType(resourceTypeService.findById(ele.getRtid()).getResourceName());
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public List<HostInformation> getHadoopList() {
        return hostInformationMapper.getHadoopList();
    }

    @Override
    public int insertHostInformation(HostInformation hostInformation) {
        return hostInformationMapper.insertHostInformation(hostInformation);
    }
}
