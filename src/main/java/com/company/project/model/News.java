package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class News {
    @Id
    private Integer nid;

    private String title;

    @Column(name = "reg_time")
    private Date regTime;

    private String html;

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

    /**
     * @return html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html
     */
    public void setHtml(String html) {
        this.html = html;
    }
}