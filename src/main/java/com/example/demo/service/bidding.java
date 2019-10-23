package com.example.demo.service;

import com.example.demo.entity.Bidding;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface bidding {
    List<Bidding> getAll();
    int delete(int projectid);
    Bidding getByProjectid(String id);
    int insert(Bidding bidding);
    int update(Bidding bidding);
    List<HomeAllBidding> getHomeBiddingAll();
    List<BiddingAll> AllBiddingAll(BiddingSelect record);
    List<BiddingAll> ProjectBiddingAll(String id);
    void importExcel(MultipartFile file, Integer type);
}
