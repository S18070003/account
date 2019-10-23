package com.example.demo.service;

import com.example.demo.entity.Project;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.LedgerBase;
import com.example.demo.model.NewProjectInfo;
import com.example.demo.model.WeeklyBase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface project {
    List<Project> getAll();
    int insert(Project project);
    int delete(String projectid);
    Project select(String projectid);
    int update(Project project);
    WeeklyBase getWeeklyBase(String projectid);
    BiddingBase getBiddingBase(String id);
    LedgerBase getLedgerBase(String id);
    String getCode();
    List<NewProjectInfo> getNewProjectInfo(String starttime,String endtime);
    void importExcel(MultipartFile file, Integer type);
    String getProjecrIdByProjectName(String projectname);
}
