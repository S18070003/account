package com.example.demo.model;

public class Monthly {
    private Integer id;
    private String projectid;
    private String projectname;
    private String projecthistory;
    private String projectcurrent;

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
}
