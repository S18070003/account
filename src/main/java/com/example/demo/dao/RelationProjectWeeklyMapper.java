package com.example.demo.dao;

import com.example.demo.entity.RelationProjectWeekly;

public interface RelationProjectWeeklyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insert(RelationProjectWeekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insertSelective(RelationProjectWeekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    RelationProjectWeekly selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKeySelective(RelationProjectWeekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RelationProjectWeekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKey(RelationProjectWeekly record);

    int deleteByWeeklyId(int weeklyId);
}