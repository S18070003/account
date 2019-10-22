package com.example.demo.model;

import java.util.Date;

public class BiddingAll {

    private String projectprogress;
    private String projectproblem;

    private Integer id;

    private String projectid;
    private String projectname;
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    private String bidabroad;

    private String bidid;

    private Date bidreceivedate;

    private Date bidstopdate;

    private String bidregion;

    private String bidrecordyn;

    private String bidsafegrade;

    private String bidguaranteeyn;

    public String getBidguaranteeyn() {
        return bidguaranteeyn;
    }

    public void setBidguaranteeyn(String bidguaranteeyn) {
        this.bidguaranteeyn = bidguaranteeyn;
    }

    private String bidguaranteetype;

    private Double bidguaranteemoney;

    private String bidoperator;

    private String bidoprateregion;

    private String bidservicetype;


    private String bidworkload;


    private Double bidtargetmoney;

    private String bidreview;

    private String bidopenresult;


    private String biddiscardreason;


    private String bidlossreason;


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
        this.projectid = projectid == null ? null : projectid.trim();
    }


    public String getProjectname() {
        return projectname;
    }


    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getBidabroad() {
        return bidabroad;
    }

    public void setBidabroad(String bidabroad) {
        this.bidabroad = bidabroad == null ? null : bidabroad.trim();
    }


    public String getBidid() {
        return bidid;
    }


    public void setBidid(String bidid) {
        this.bidid = bidid == null ? null : bidid.trim();
    }

    public Date getBidreceivedate() {
        return bidreceivedate;
    }


    public void setBidreceivedate(Date bidreceivedate) {
        this.bidreceivedate = bidreceivedate;
    }


    public Date getBidstopdate() {
        return bidstopdate;
    }


    public void setBidstopdate(Date bidstopdate) {
        this.bidstopdate = bidstopdate;
    }


    public String getBidregion() {
        return bidregion;
    }

    public void setBidregion(String bidregion) {
        this.bidregion = bidregion == null ? null : bidregion.trim();
    }

    public String getBidrecordyn() {
        return bidrecordyn;
    }

    public void setBidrecordyn(String bidrecordyn) {
        this.bidrecordyn = bidrecordyn == null ? null : bidrecordyn.trim();
    }

    public String getBidsafegrade() {
        return bidsafegrade;
    }


    public void setBidsafegrade(String bidsafegrade) {
        this.bidsafegrade = bidsafegrade == null ? null : bidsafegrade.trim();
    }


    public String getBidguaranteetype() {
        return bidguaranteetype;
    }


    public void setBidguaranteetype(String bidguaranteetype) {
        this.bidguaranteetype = bidguaranteetype == null ? null : bidguaranteetype.trim();
    }


    public Double getBidguaranteemoney() {
        return bidguaranteemoney;
    }


    public void setBidguaranteemoney(Double bidguaranteemoney) {
        this.bidguaranteemoney = bidguaranteemoney;
    }


    public String getBidoperator() {
        return bidoperator;
    }

    public void setBidoperator(String bidoperator) {
        this.bidoperator = bidoperator == null ? null : bidoperator.trim();
    }
    public String getBidoprateregion() {
        return bidoprateregion;
    }

    public void setBidoprateregion(String bidoprateregion) {
        this.bidoprateregion = bidoprateregion == null ? null : bidoprateregion.trim();
    }

    public String getBidservicetype() {
        return bidservicetype;
    }

    public void setBidservicetype(String bidservicetype) {
        this.bidservicetype = bidservicetype == null ? null : bidservicetype.trim();
    }

    public String getBidworkload() {
        return bidworkload;
    }
    public void setBidworkload(String bidworkload) {
        this.bidworkload = bidworkload == null ? null : bidworkload.trim();
    }

    public Double getBidtargetmoney() {
        return bidtargetmoney;
    }
    public void setBidtargetmoney(Double bidtargetmoney) {
        this.bidtargetmoney = bidtargetmoney;
    }
    public String getBidreview() {
        return bidreview;
    }
    public void setBidreview(String bidreview) {
        this.bidreview = bidreview == null ? null : bidreview.trim();
    }
    public String getBidopenresult() {
        return bidopenresult;
    }
    public void setBidopenresult(String bidopenresult) {
        this.bidopenresult = bidopenresult == null ? null : bidopenresult.trim();
    }
    public String getBiddiscardreason() {
        return biddiscardreason;
    }
    public void setBiddiscardreason(String biddiscardreason) {
        this.biddiscardreason = biddiscardreason == null ? null : biddiscardreason.trim();
    }
    public String getBidlossreason() {
        return bidlossreason;
    }
    public void setBidlossreason(String bidlossreason) {
        this.bidlossreason = bidlossreason == null ? null : bidlossreason.trim();
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

    public Date getSubmittime() {
        return submittime;
    }
    public void setSubmittime(Date submittime) {
        this.submittime = submittime;
    }
}
