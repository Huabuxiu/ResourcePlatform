package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "resource_application")
public class ResourceApplicationVo {
    @Id
    private Integer raid;

    private String resourceType;

    private String resourceTypeImage;


    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "pass_date")
    private Date passDate;

    private String host_name;

    private String user_name;

    private String purpose; //用途

    private String port;

    private Integer time;

    private Integer remaining_time; //剩余时间

    private String host_username;

    private String host_password;

    private String refuse_reason;

    @Column(name = "operating_system")
    private String operatingSystem;

    private String progress;    //状态


    public ResourceApplicationVo(ResourceApplication resourceApplication) {
        this.raid = resourceApplication.getRaid();
        this.createDate = resourceApplication.getCreateDate();
        this.passDate = resourceApplication.getPassDate();
        this.purpose = resourceApplication.getPurpose();
        this.port = resourceApplication.getPort();
        this.time = resourceApplication.getTime();
        this.operatingSystem = resourceApplication.getOperatingSystem();
        this.progress = resourceApplication.getProgress();

//        resourceType
//        user_name
//        host_name
//        this.remaining_time = remaining_time;
//        this.host_username = host_username;
//        this.host_password = host_password;
//        this.refuse_reason = refuse_reason;
    }

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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {
        this.host_name = host_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Integer getRemaining_time() {
        return remaining_time;
    }

    public void setRemaining_time(Integer remaining_time) {
        this.remaining_time = remaining_time;
    }

    public String getResourceTypeImage() {
        return resourceTypeImage;
    }

    public void setResourceTypeImage(String resourceTypeImage) {
        this.resourceTypeImage = resourceTypeImage;
    }

    public String getHost_username() {
        return host_username;
    }

    public void setHost_username(String host_username) {
        this.host_username = host_username;
    }

    public String getHost_password() {
        return host_password;
    }

    public void setHost_password(String host_password) {
        this.host_password = host_password;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
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