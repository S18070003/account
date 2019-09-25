package com.example.demo.service.impl;

import com.example.demo.dao.WeeklyMapper;
import com.example.demo.entity.Weekly;
import com.example.demo.model.WeeklyModel;
import com.example.demo.service.weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class weeklyImpl implements weekly {
    @Autowired
    public WeeklyMapper weeklyMapper;
    @Override
    public List<WeeklyModel> getAll(){
        return weeklyMapper.selectAll();
    }
    @Override
    public List<WeeklyModel> getWeekly(){
        return weeklyMapper.selectWeek();
    }
    @Override
    public List<WeeklyModel> getMonth(){
        return weeklyMapper.selectMonth();
    }
    @Override
    public int insert(Weekly weekly){
        return weeklyMapper.insertSelective(weekly);
    }
    @Override
    public List<Weekly> getByProjectid(String projectid){
        return weeklyMapper.getByProjectid(projectid);
    }
    @Override
    public int getCountAll(){
        return weeklyMapper.countAll();
    }
    @Override
    public  int getCountWeek(){
        return weeklyMapper.countWeek();
    }
    @Override
    public int getCountMonth(){
        return weeklyMapper.countMonth();
    }
    @Override
    public int delete(int weeklyId){
        return weeklyMapper.deleteByPrimaryKey(weeklyId);
    }
}
