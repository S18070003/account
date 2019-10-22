package com.example.demo.service.impl;

import com.example.demo.dao.BiddingMapper;
import com.example.demo.entity.Bidding;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import com.example.demo.service.bidding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class biddingImpl implements bidding {
    @Autowired
    public BiddingMapper biddingMapper;
    @Override
    public List<Bidding> getAll(){ return  biddingMapper.getAll();}
    @Override
    public int delete(int id){
        return biddingMapper.deleteByPrimaryKey(id);
    }
    @Override
    public Bidding getByProjectid(String projectid){
        return biddingMapper.getByProjectid(projectid);
    }
    @Override
    public int insert(Bidding bidding){
        return biddingMapper.insertSelective(bidding);
    }
    @Override
    public int update(Bidding bidding){
        return  biddingMapper.updateByPrimaryKeySelective(bidding);
    }
    @Override
    public List<HomeAllBidding> getHomeBiddingAll(){
        return biddingMapper.getHomeBiddingAll();
    }
    @Override
    public List<BiddingAll>  AllBiddingAll(BiddingSelect record){ return biddingMapper.AllBiddingAll(record);}
    @Override
    public List<BiddingAll> ProjectBiddingAll(String id){
        return biddingMapper.ProjectBiddingAll(id);
    }
}
