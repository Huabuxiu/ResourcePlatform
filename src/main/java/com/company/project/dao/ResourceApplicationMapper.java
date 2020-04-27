package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.ResourceApplication;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface ResourceApplicationMapper extends Mapper<ResourceApplication> {

    public ResourceApplication getResourceApplication(Date createDate);

    public Integer insertResourceApplication(ResourceApplication resourceApplication);
}