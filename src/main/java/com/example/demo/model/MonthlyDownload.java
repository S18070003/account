package com.example.demo.model;

import java.util.Date;

public class MonthlyDownload {
    private Integer id;
    private String projectid;
    private String projectname;

    private String projectgeneral;

    private String projecthistory;
    private String projectcurrent;
    private Date projectstarttime;
    private String projectplan;
    private String projectdifficulty;
    private String projectsuggestion;
    private Date submittime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectgeneral() {
        return projectgeneral;
    }

    public void setProjectgeneral(String projectgeneral) {
        this.projectgeneral = projectgeneral;
    }

    public String getProjecthistory() {
        return projecthistory;
    }

    public void setProjecthistory(String projecthistory) {
        this.projecthistory = projecthistory;
    }

    public String getProjectcurrent() {
        return projectcurrent;
    }

    public void setProjectcurrent(String projectcurrent) {
        this.projectcurrent = projectcurrent;
    }

    public Date getProjectstarttime() {
        return projectstarttime;
    }

    public void setProjectstarttime(Date projectstarttime) {
        this.projectstarttime = projectstarttime;
    }

    public String getProjectplan() {
        return projectplan;
    }

    public void setProjectplan(String projectplan) {
        this.projectplan = projectplan;
    }

    public String getProjectdifficulty() {
        return projectdifficulty;
    }

    public void setProjectdifficulty(String projectdifficulty) {
        this.projectdifficulty = projectdifficulty;
    }

    public String getProjectsuggestion() {
        return projectsuggestion;
    }

    public void setProjectsuggestion(String projectsuggestion) {
        this.projectsuggestion = projectsuggestion;
    }

    public Date getSubmittime() {
        return submittime;
    }

    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}
