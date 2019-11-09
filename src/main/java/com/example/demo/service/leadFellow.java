package com.example.demo.service;

import com.example.demo.controller.Param.LeadFellowArray;
import com.example.demo.controller.Param.LeaderFellowExtendUser;
import com.example.demo.entity.LeadFellow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface leadFellow {
    int add(LeadFellowArray leadFellowArray);
    List<LeaderFellowExtendUser> getAllExtendUser();
    int deleteByLeaderId(int id);
    int deleteByFellowId(int id);
    List<Integer> getFellow(int id);
}
