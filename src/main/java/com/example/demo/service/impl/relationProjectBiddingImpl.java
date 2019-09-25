package com.example.demo.service.impl;

import com.example.demo.dao.RelationProjectBiddingMapper;
import com.example.demo.entity.RelationProjectBidding;
import com.example.demo.service.relationProjectBidding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class relationProjectBiddingImpl implements relationProjectBidding {
    @Autowired
    public RelationProjectBiddingMapper relationProjectBiddingMapper;
    @Override
    public int insert(RelationProjectBidding relationProjectBidding){
        return relationProjectBiddingMapper.insertSelective(relationProjectBidding);
    }
    @Override
    public int delete(int biddingid,String projectid){
        return relationProjectBiddingMapper.deleteByIdProjectId(biddingid,projectid);
    }
}
