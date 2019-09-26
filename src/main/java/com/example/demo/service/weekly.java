package com.example.demo.service;

import com.example.demo.entity.Weekly;
import com.example.demo.model.Monthly;
import com.example.demo.model.WeeklyModel;

import java.util.List;

public interface weekly {
    List<WeeklyModel> getAll();
    List<WeeklyModel> getWeekly();
    List<WeeklyModel> getMonth();
    int insert(Weekly weekly);
    List<Weekly> getByProjectid(String projectid);
    int getCountAll();
    int getCountWeek();
    int getCountMonth();
    int delete(int weeklyId);
    List<Monthly> getMonthly();
}
