package com.example.demo.service.impl;

import com.example.demo.dao.RelationProjectWeeklyMapper;
import com.example.demo.entity.RelationProjectWeekly;
import com.example.demo.service.relationProjectWeekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class relationProjectWeeklyImpl implements relationProjectWeekly {
    @Autowired
    public RelationProjectWeeklyMapper relationProjectWeeklyMapper;
    @Override
    public int insert(RelationProjectWeekly relationProjectWeekly){
        return relationProjectWeeklyMapper.insertSelective(relationProjectWeekly);
    }
    @Override
    public int delete(int weeklyId){
        return relationProjectWeeklyMapper.deleteByWeeklyId(weeklyId);
    }

}
