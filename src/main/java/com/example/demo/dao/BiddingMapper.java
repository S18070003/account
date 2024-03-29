package com.example.demo.dao;

import com.example.demo.entity.Bidding;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BiddingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insert(Bidding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int insertSelective(Bidding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    Bidding selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKeySelective(Bidding record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table Bidding
     *
     * @mbg.generated Wed Jul 17 22:42:39 CST 2019
     */
    int updateByPrimaryKey(Bidding record);

    List<Bidding> getAll();

    Bidding getByProjectid(String projectid);

    List<HomeAllBidding> getHomeBiddingAll();
    List<BiddingAll> AllBiddingAll(@Param("record") BiddingSelect record,@Param("list") List<Integer> list);
    List<BiddingAll> ProjectBiddingAll(String id);

}