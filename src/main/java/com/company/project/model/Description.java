package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Description {
    @Id
    private Integer deid;

    @Column(name = "reg_time")
    private Date regTime;

    private String html;

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