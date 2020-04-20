package com.company.project.model;

import javax.persistence.*;

@Table(name = "host_queue")
public class HostQueue {
    @Id
    private Integer hiid;

    private Integer rtid;

    @Column(name = "queue_size")
    private Integer queueSize;

    @Column(name = "total_time")
    private Integer totalTime;

    @Column(name = "total_user")
    private Integer totalUser;

    @Column(name = "head_time")
    private Integer headTime;

    @Column(name = "queue_element")
    private String queueElement;

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
     * @return queue_size
     */
    public Integer getQueueSize() {
        return queueSize;
    }

    /**
     * @param queueSize
     */
    public void setQueueSize(Integer queueSize) {
        this.queueSize = queueSize;
    }

    /**
     * @return total_time
     */
    public Integer getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime
     */
    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @return total_user
     */
    public Integer getTotalUser() {
        return totalUser;
    }

    /**
     * @param totalUser
     */
    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    /**
     * @return head_time
     */
    public Integer getHeadTime() {
        return headTime;
    }

    /**
     * @param headTime
     */
    public void setHeadTime(Integer headTime) {
        this.headTime = headTime;
    }

    /**
     * @return queue_element
     */
    public String getQueueElement() {
        return queueElement;
    }

    /**
     * @param queueElement
     */
    public void setQueueElement(String queueElement) {
        this.queueElement = queueElement;
    }
}