package com.company.project.dao;

import com.company.project.core.Mapper;
import com.company.project.model.HostInformation;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HostInformationMapper extends Mapper<HostInformation> {

    @Select("select hiid, name,host_information.reg_time, host_information.rtid, address, port\n" +
            "    from resource_type,host_information\n" +
            "    where  resources.resource_type.rtid = resources.host_information.rtid\n" +
            "      AND resources.resource_type.resource_name = 'Hadoop大数据处理平台（20台服务器集群）';")
    public List<HostInformation> getHadoopList();


    public Integer insertHostInformation(HostInformation hostInformation);
}