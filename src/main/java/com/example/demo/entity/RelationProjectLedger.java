package com.example.demo.entity;

public class RelationProjectLedger {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RelationProjectLedger.id
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RelationProjectLedger.projectid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    private String projectid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column RelationProjectLedger.ledgerid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    private Integer ledgerid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RelationProjectLedger.id
     *
     * @return the value of RelationProjectLedger.id
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RelationProjectLedger.id
     *
     * @param id the value for RelationProjectLedger.id
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RelationProjectLedger.projectid
     *
     * @return the value of RelationProjectLedger.projectid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public String getProjectid() {
        return projectid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RelationProjectLedger.projectid
     *
     * @param projectid the value for RelationProjectLedger.projectid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column RelationProjectLedger.ledgerid
     *
     * @return the value of RelationProjectLedger.ledgerid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public Integer getLedgerid() {
        return ledgerid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column RelationProjectLedger.ledgerid
     *
     * @param ledgerid the value for RelationProjectLedger.ledgerid
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    public void setLedgerid(Integer ledgerid) {
        this.ledgerid = ledgerid;
    }
}