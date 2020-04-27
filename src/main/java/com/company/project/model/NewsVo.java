package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class NewsVo {
    @Id
    private Integer nid;

    private String title;

    @Column(name = "reg_time")
    private Date regTime;

    public NewsVo(News news) {
        this.nid = news.getNid();
        this.title = news.getTitle();
        this.regTime = news.getRegTime();
    }

    /**
     * @return nid
     */
    public Integer getNid() {
        return nid;
    }

    /**
     * @param nid
     */
    public void setNid(Integer nid) {
        this.nid = nid;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
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

}