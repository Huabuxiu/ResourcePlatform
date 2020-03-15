package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "resource_type")
public class ResourceType {
    @Id
    private Integer rtid;

    @Column(name = "resource_name")
    private String resourceName;

    private String introduction;

    private String image;

    @Column(name = "reg_time")
    private Date regTime;

    private Integer deid;

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
     * @return resource_name
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return introduction
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * @param introduction
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
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
     * @return deid
     */
    public Integer getDeid() {
        return deid;
    }

    /**
     * @param deid
     */
    public void setDeid(Integer deid) {
        this.deid = deid;
    }
}