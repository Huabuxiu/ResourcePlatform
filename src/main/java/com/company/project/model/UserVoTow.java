package com.company.project.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class UserVoTow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;


    private String name;

    @Column(name = "e_mail")
    private String eMail;

    private String phone;

    @Column(name = "reg_time")
    private Date regTime;

    private String token;

    @Column(name = "user_role")
    private Integer userRole;

    private String department;

    private Integer state;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserVoTow(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.name = user.getName();
        this.eMail = user.geteMail();
        this.phone = user.getPhone();
        this.regTime = user.getRegTime();
        this.token = user.getToken();
        this.userRole = user.getUserRole();
        this.state = user.getState();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return e_mail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return user_role
     */
    public Integer getUserRole() {
        return userRole;
    }

    /**
     * @param userRole
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
}