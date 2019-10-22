package com.example.demo.model;

import java.util.Date;

public class NewProjectInfo {
    private String projectid;
    private String projectname;
    private String projectgeneral;
    private String projectprogress;
    private String projectproblem;
    private Date projectbuildtime;

    public Date getProjectbuildtime() {
        return projectbuildtime;
    }

    public void setProjectbuildtime(Date projectbuildtime) {
        this.projectbuildtime = projectbuildtime;
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

    public String getProjectprogress() {
        return projectprogress;
    }

    public void setProjectprogress(String projectprogress) {
        this.projectprogress = projectprogress;
    }

    public String getProjectproblem() {
        return projectproblem;
    }

    public void setProjectproblem(String projectproblem) {
        this.projectproblem = projectproblem;
    }
}
