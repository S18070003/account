package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    User selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Users
     *
     * @mbg.generated Thu Oct 31 15:21:55 CST 2019
     */
    int updateByPrimaryKey(User record);

    User selectAllByUsername(String username);

    List<User> selectAll();
    int checkByUsername(@Param("username") String username);
}