package com.example.demo.service.impl;

import com.example.demo.dao.ProjectMapper;
import com.example.demo.entity.Project;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.LedgerBase;
import com.example.demo.model.WeeklyBase;
import com.example.demo.service.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Transactional
@Service
public class projectImpl implements project {
    @Autowired
    public ProjectMapper projectMapper;
    @Override
    public List<Project> getAll(){
        return projectMapper.selectAll();
    }
    @Override
    public int insert(Project project){
        return  projectMapper.insertSelective(project);
    }
    @Override
    public int delete(String projectid){
        return projectMapper.deleteByPrimaryKey(projectid);
    }
    @Override
    public Project select(String projectid){
        return projectMapper.selectByPrimaryKey(projectid);
    }
    @Override
    public int update(Project project){
        return projectMapper.updateByPrimaryKeySelective(project);
    }
    @Override
    public WeeklyBase getWeeklyBase(String projectid){
        return projectMapper.selectWeeklyBase(projectid);
    }
    @Override
    public BiddingBase getBiddingBase(String projectid){
        return projectMapper.selectBiddingBase(projectid);
    }
    @Override
    public LedgerBase getLedgerBase(String projectid){
        return projectMapper.selectLedgerBase(projectid);
    }
    @Override
    public String getCode(){
        String code;
        String getNum=projectMapper.getCode();
        int num=Integer.parseInt(getNum)+1;
        String num1=Integer.toString(10000+num).substring(1,5);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM"); // 时间字符串产生方式
        String uidTime = format.format(new Date()); // 组合流水号前一部分，时间字符串，如：1601
        code="YS-"+uidTime+"-"+num1;
        return code;
    }
}
