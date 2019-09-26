package com.example.demo.dao;

import com.example.demo.entity.Weekly;
import com.example.demo.model.Monthly;
import com.example.demo.model.WeeklyModel;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WeeklyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insert(Weekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insertSelective(Weekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    Weekly selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKeySelective(Weekly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Weekly
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKey(Weekly record);

    List<Weekly> getByProjectid(String projectid);
    List<WeeklyModel> selectAll();
    List<WeeklyModel> selectWeek();
    List<WeeklyModel> selectMonth();

    int countAll();
    int countWeek();
    int countMonth();
    List<Monthly> getMonthly();
}