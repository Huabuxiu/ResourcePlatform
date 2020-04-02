package com.company.project.model;

import javax.persistence.*;

@Table(name = "department_user")
public class DepartmentUser {
    @Id
    private Integer duid;

    private Integer did;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * @return duid
     */
    public Integer getDuid() {
        return duid;
    }

    /**
     * @param duid
     */
    public void setDuid(Integer duid) {
        this.duid = duid;
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
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
}