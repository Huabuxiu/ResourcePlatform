package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "start_time")
public class StartTime {
    @Id
    private Integer stid;

    private Integer raid;

    private Date starttime;

    /**
     * @return stid
     */
    public Integer getStid() {
        return stid;
    }

    /**
     * @param stid
     */
    public void setStid(Integer stid) {
        this.stid = stid;
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

    /**
     * @return starttime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }
}