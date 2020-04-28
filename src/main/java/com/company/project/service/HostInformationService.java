package com.company.project.service;
import com.company.project.model.HostInformation;
import com.company.project.core.Service;
import com.company.project.model.HostInformationVo;

import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/15.
 */
public interface HostInformationService extends Service<HostInformation> {

    public List<HostInformationVo> getVoList(List<HostInformation> list);

    public List<HostInformation> getHadoopList();

    public int insertHostInformation(HostInformation hostInformation);

}
