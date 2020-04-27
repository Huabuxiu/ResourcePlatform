package com.company.project.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Table(name = "resource_application")
public class ResourceApplication {
    @Id
    private Integer raid;

    private Integer did;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "pass_date")
    private Date passDate;

    private Integer hiid;

    private Integer uid;

    private String purpose;

    private String port;

    private Integer time;

    public Integer getRtid() {
        return rtid;
    }

    public void setRtid(Integer rtid) {
        this.rtid = rtid;
    }

    private Integer rtid;

    @Column(name = "operating_system")
    private String operatingSystem;

    private String progress;

    /**
     * @return raid
     */
    public Integer getRaid() {
        return raid;
    }

    /**
     * @param raid
     */
    public void setRaid(Integer raid) {
        this.raid = raid;
    }

    /**
     * @return did
     */
    public Integer getDid() {
        return did;
    }

    /**
     * @param did
     */
    public void setDid(Integer did) {
        this.did = did;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return pass_date
     */
    public Date getPassDate() {
        return passDate;
    }

    /**
     * @param passDate
     */
    public void setPassDate(Date passDate) {
        this.passDate = passDate;
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
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return purpose
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * @param purpose
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }


    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return operating_system
     */
    public String getOperatingSystem() {
        return operatingSystem;
    }

    /**
     * @param operatingSystem
     */
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return progress
     */
    public String getProgress() {
        return progress;
    }

    /**
     * @param progress
     */
    public void setProgress(String progress) {
        this.progress = progress;
    }
}