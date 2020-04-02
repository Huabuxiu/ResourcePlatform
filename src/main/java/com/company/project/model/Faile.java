package com.company.project.model;

import javax.persistence.*;

public class Faile {
    @Id
    private Integer raid;

    private String reason;

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
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}