package com.example.demo.service;

import com.example.demo.entity.Weekly;
import com.example.demo.model.Monthly;
import com.example.demo.model.MonthlyDownload;
import com.example.demo.model.WeeklyModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface weekly {
    List<WeeklyModel> getAll();
    List<WeeklyModel> selectAllByTime(String starttime,String endtime);

    List<WeeklyModel> getWeekly();
    List<WeeklyModel> getMonth();
    int insert(Weekly weekly);
    List<Weekly> getByProjectid(String projectid);
    int getCountAll();
    int getCountWeek();
    int getCountMonth();
    int delete(int weeklyId);
    List<Monthly> getMonthly();
    List<MonthlyDownload> getMonthlyForDownload(String starttime,String endtime);
    void importExcel(MultipartFile file, Integer type);
}
