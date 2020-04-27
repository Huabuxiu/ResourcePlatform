package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "host_information")
public class HostInformationVo {
    @Id
    private Integer hiid;

    private String name;

    @Column(name = "reg_time")
    private Date regTime;

    private String resourceType;

    private String address;

    private String port;


    public HostInformationVo(HostInformation hostInformation) {
        this.hiid = hostInformation.getHiid();
        this.name = hostInformation.getName();
        this.regTime = hostInformation.getRegTime();
        this.address = hostInformation.getAddress();
        this.port = hostInformation.getPort();
    }

    /**
     * @return hiid
     */
    public Integer getHiid() {
        return hiid;
    }

    /**
     * @param hiid
     */
    public void setHiid(Integer hiid) {
        this.hiid = hiid;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return reg_time
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }


    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}