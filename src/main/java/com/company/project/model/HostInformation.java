package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "host_information")
public class HostInformation {
    @Id
    private Integer hiid;

    private String name;

    @Column(name = "reg_time")
    private Date regTime;

    private Integer rtid;

    private String address;

    private Integer port;

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

    /**
     * @return rtid
     */
    public Integer getRtid() {
        return rtid;
    }

    /**
     * @param rtid
     */
    public void setRtid(Integer rtid) {
        this.rtid = rtid;
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

    /**
     * @return port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port
     */
    public void setPort(Integer port) {
        this.port = port;
    }
}