package com.example.demo.service;

import com.example.demo.entity.RelationProjectBidding;
import com.example.demo.entity.RelationProjectLedger;

public interface relationProjectBidding {
    int insert(RelationProjectBidding relationProjectBidding);
    int delete(int biddingid,String projectid);
}
