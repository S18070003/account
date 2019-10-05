package com.example.demo.model;

import java.util.Date;

public class WeeklyModel {
    private Integer id;
    private String projectid;
    private String projectname;

    private String department;


    private String projectgeneral;
    private String projectestimateamount;
    private String projectcharger;
   private String bidregion;

    private String projecthistory;
    private String projectcurrent;
    private Date projectstarttime;
    private String projectplan;
    private String projectdifficulty;
    private String projectsuggestion;
    private Date submittime;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProjectgeneral() {
        return projectgeneral;
    }

    public void setProjectgeneral(String projectgeneral) {
        this.projectgeneral = projectgeneral;
    }


    public String getProjectcharger() {
        return projectcharger;
    }

    public void setProjectcharger(String projectcharger) {
        this.projectcharger = projectcharger;
    }

    public String getBidregion() {
        return bidregion;
    }

    public void setBidregion(String bidregion) {
        this.bidregion = bidregion;
    }
    public String getProjectestimateamount() {
        return projectestimateamount;
    }

    public void setProjectestimateamount(String projectestimateamount) {
        this.projectestimateamount = projectestimateamount;
    }
    public String getProjectid() {
        return projectid;
    }
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }
    public String getProjectname() {
        return projectname;
    }
    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getProjecthistory() {
        return projecthistory;
    }
    public void setProjecthistory(String projecthistory) {
        this.projecthistory = projecthistory == null ? null : projecthistory.trim();
    }
    public String getProjectcurrent() {
        return projectcurrent;
    }
    public void setProjectcurrent(String projectcurrent) {
        this.projectcurrent = projectcurrent == null ? null : projectcurrent.trim();
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
        this.projectplan = projectplan == null ? null : projectplan.trim();
    }
    public String getProjectdifficulty() {
        return projectdifficulty;
    }
    public void setProjectdifficulty(String projectdifficulty) {
        this.projectdifficulty = projectdifficulty == null ? null : projectdifficulty.trim();
    }
    public String getProjectsuggestion() {
        return projectsuggestion;
    }
    public void setProjectsuggestion(String projectsuggestion) {
        this.projectsuggestion = projectsuggestion == null ? null : projectsuggestion.trim();
    }
    public Date getSubmittime() {
        return submittime;
    }
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}