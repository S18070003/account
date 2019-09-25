package com.example.demo.model;

public class WeeklyBase {

    private String projectid;

    private String projectname;

    private String department;
    private String projectgeneral;

    private String projectestimateamount;

    private String projectcharger;

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
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }
    public String getProjectgeneral() {
        return projectgeneral;
    }
    public void setProjectgeneral(String projectgeneral) {
        this.projectgeneral = projectgeneral == null ? null : projectgeneral.trim();
    }
    public String getProjectestimateamount() {
        return projectestimateamount;
    }
    public void setProjectestimateamount(String projectestimateamount) {
        this.projectestimateamount = projectestimateamount == null ? null : projectestimateamount.trim();
    }
    public String getProjectcharger() {
        return projectcharger;
    }
    public void setProjectcharger(String projectcharger) {
        this.projectcharger = projectcharger == null ? null : projectcharger.trim();
    }
}
