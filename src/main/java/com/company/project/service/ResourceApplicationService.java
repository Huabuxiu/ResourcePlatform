package com.company.project.service;
import com.company.project.model.News;
import com.company.project.model.NewsVo;
import com.company.project.model.ResourceApplication;
import com.company.project.core.Service;
import com.company.project.model.ResourceApplicationVo;

import java.util.Date;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/04/20.
 */
public interface ResourceApplicationService extends Service<ResourceApplication> {
    public ResourceApplication getResourceApplication(Date createDate);

    public Integer insertResourceApplication(ResourceApplication resourceApplication);

    public List<ResourceApplicationVo> getVoList(List<ResourceApplication> list);


    public ResourceApplicationVo getVo(ResourceApplication resourceApplication);
}
