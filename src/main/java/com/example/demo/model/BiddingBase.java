package com.example.demo.model;

import java.util.Date;

public class BiddingBase {

    private String projectid;

    private String projectname;


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

    private String projectprogress;

    private String projectproblem;


    public String getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.projectid
     *
     * @param projectid the value for Project.projectid
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.projectname
     *
     * @return the value of Project.projectname
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getProjectname() {
        return projectname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.projectname
     *
     * @param projectname the value for Project.projectname
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getBidabroad() {
        return bidabroad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidabroad
     *
     * @param bidabroad the value for Project.bidabroad
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidabroad(String bidabroad) {
        this.bidabroad = bidabroad == null ? null : bidabroad.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidid
     *
     * @return the value of Project.bidid
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidid() {
        return bidid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidid
     *
     * @param bidid the value for Project.bidid
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidid(String bidid) {
        this.bidid = bidid == null ? null : bidid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidreceivedate
     *
     * @return the value of Project.bidreceivedate
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public Date getBidreceivedate() {
        return bidreceivedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidreceivedate
     *
     * @param bidreceivedate the value for Project.bidreceivedate
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidreceivedate(Date bidreceivedate) {
        this.bidreceivedate = bidreceivedate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidstopdate
     *
     * @return the value of Project.bidstopdate
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public Date getBidstopdate() {
        return bidstopdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidstopdate
     *
     * @param bidstopdate the value for Project.bidstopdate
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidstopdate(Date bidstopdate) {
        this.bidstopdate = bidstopdate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidregion
     *
     * @return the value of Project.bidregion
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidregion() {
        return bidregion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidregion
     *
     * @param bidregion the value for Project.bidregion
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidregion(String bidregion) {
        this.bidregion = bidregion == null ? null : bidregion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidrecordyn
     *
     * @return the value of Project.bidrecordyn
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidrecordyn() {
        return bidrecordyn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidrecordyn
     *
     * @param bidrecordyn the value for Project.bidrecordyn
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidrecordyn(String bidrecordyn) {
        this.bidrecordyn = bidrecordyn == null ? null : bidrecordyn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidsafegrade
     *
     * @return the value of Project.bidsafegrade
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidsafegrade() {
        return bidsafegrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidsafegrade
     *
     * @param bidsafegrade the value for Project.bidsafegrade
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidsafegrade(String bidsafegrade) {
        this.bidsafegrade = bidsafegrade == null ? null : bidsafegrade.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidguaranteetype
     *
     * @return the value of Project.bidguaranteetype
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidguaranteetype() {
        return bidguaranteetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidguaranteetype
     *
     * @param bidguaranteetype the value for Project.bidguaranteetype
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidguaranteetype(String bidguaranteetype) {
        this.bidguaranteetype = bidguaranteetype == null ? null : bidguaranteetype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidguaranteemoney
     *
     * @return the value of Project.bidguaranteemoney
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public Double getBidguaranteemoney() {
        return bidguaranteemoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidguaranteemoney
     *
     * @param bidguaranteemoney the value for Project.bidguaranteemoney
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidguaranteemoney(Double bidguaranteemoney) {
        this.bidguaranteemoney = bidguaranteemoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidoperator
     *
     * @return the value of Project.bidoperator
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidoperator() {
        return bidoperator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidoperator
     *
     * @param bidoperator the value for Project.bidoperator
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidoperator(String bidoperator) {
        this.bidoperator = bidoperator == null ? null : bidoperator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidoprateregion
     *
     * @return the value of Project.bidoprateregion
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidoprateregion() {
        return bidoprateregion;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidoprateregion
     *
     * @param bidoprateregion the value for Project.bidoprateregion
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidoprateregion(String bidoprateregion) {
        this.bidoprateregion = bidoprateregion == null ? null : bidoprateregion.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidservicetype
     *
     * @return the value of Project.bidservicetype
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidservicetype() {
        return bidservicetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidservicetype
     *
     * @param bidservicetype the value for Project.bidservicetype
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidservicetype(String bidservicetype) {
        this.bidservicetype = bidservicetype == null ? null : bidservicetype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.bidworkload
     *
     * @return the value of Project.bidworkload
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getBidworkload() {
        return bidworkload;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.bidworkload
     *
     * @param bidworkload the value for Project.bidworkload
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setBidworkload(String bidworkload) {
        this.bidworkload = bidworkload == null ? null : bidworkload.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.projectprogress
     *
     * @return the value of Project.projectprogress
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getProjectprogress() {
        return projectprogress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.projectprogress
     *
     * @param projectprogress the value for Project.projectprogress
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setProjectprogress(String projectprogress) {
        this.projectprogress = projectprogress == null ? null : projectprogress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column Project.projectproblem
     *
     * @return the value of Project.projectproblem
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getProjectproblem() {
        return projectproblem;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column Project.projectproblem
     *
     * @param projectproblem the value for Project.projectproblem
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setProjectproblem(String projectproblem) {
        this.projectproblem = projectproblem == null ? null : projectproblem.trim();
    }
}