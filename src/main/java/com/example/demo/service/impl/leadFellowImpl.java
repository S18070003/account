package com.example.demo.service.impl;

import com.example.demo.controller.Param.LeadFellowArray;
import com.example.demo.controller.Param.LeaderFellowExtendUser;
import com.example.demo.dao.LeadFellowMapper;
import com.example.demo.entity.LeadFellow;
import com.example.demo.service.leadFellow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class leadFellowImpl implements leadFellow {
    @Autowired
    private LeadFellowMapper leadFellowMapper;


    @Override
    public int add(LeadFellowArray leadFellowArray) {
        return leadFellowMapper.add(leadFellowArray);
    }

    @Override
    public List<LeaderFellowExtendUser> getAllExtendUser() {
        return leadFellowMapper.getAllExtendUser();
    }
    @Transactional
    @Override
    public int deleteByLeaderId(int id) {
        return leadFellowMapper.deleteByLeaderId(id);
    }
    @Transactional
    @Override
    public int deleteByFellowId(int id) {
        return leadFellowMapper.deleteByFellowId(id);
    }

    @Override
    public List<Integer> getFellow(int id) {
        return leadFellowMapper.getFellowList(id);
    }
}
