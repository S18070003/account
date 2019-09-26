package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.RelationProjectWeekly;
import com.example.demo.entity.Weekly;
import com.example.demo.model.Monthly;
import com.example.demo.model.WeeklyModel;
import com.example.demo.service.relationProjectWeekly;
import com.example.demo.service.weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/weekly")
public class WeeklyController {
    @Autowired
    private weekly weeklyService;
    @Autowired
    private relationProjectWeekly relationProjectWeeklyService;
    @RequestMapping("/get")
    public List<WeeklyModel> get(String mode){
        List<WeeklyModel> data=new ArrayList<WeeklyModel>();
        if (mode.equals("Week")){
            data= weeklyService.getWeekly();
        }else if (mode.equals("Month")){
            data= weeklyService.getMonth();
        }else if (mode.equals("All")){
            data= weeklyService.getAll();
        }
        return data;
    }
    @RequestMapping("/getWeek")
    public List<WeeklyModel> getWeek(){
        return weeklyService.getWeekly();
    }
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody JSONObject json){
        JSONObject data=new JSONObject();
        Weekly weekly=new Weekly();
        weekly.setProjectcurrent(json.getString("projectcurrent"));
        weekly.setProjectdifficulty(json.getString("projectdifficulty"));
        weekly.setProjecthistory(json.getString("projecthistory"));
        weekly.setProjectplan(json.getString("projectplan"));
        weekly.setProjectstarttime(json.getDate("projectstarttime"));
        weekly.setProjectsuggestion(json.getString("projectsuggestion"));
        weekly.setSubmittime(new Date());
        weeklyService.insert(weekly);
        int x=weekly.getId();
        RelationProjectWeekly record=new RelationProjectWeekly();
        record.setProjectid(json.getString("projectid"));
        record.setWeeklyid(x);
        int result=relationProjectWeeklyService.insert(record);
        if(result==1){
            data.put("isinsert",true);
        }else {
            data.put("isinsert",false);
        }
        return data;
    }
    @RequestMapping("/getByProjectid")
    public List<Weekly> getByProjectid(String projectid){
        return weeklyService.getByProjectid(projectid);
    }
    @RequestMapping("/Count")
    public JSONObject getCount(){
        JSONObject data=new JSONObject();
        int x=weeklyService.getCountWeek();
        int y=weeklyService.getCountMonth();
        int z=weeklyService.getCountAll();
        data.put("Week",x);
        data.put("Month",y);
        data.put("All",z);
        return data;
    }
    @RequestMapping("/delete")
    public JSONObject delete(int id){
        System.out.println(id);
        JSONObject data=new JSONObject();
        int x=weeklyService.delete(id);
        int y=relationProjectWeeklyService.delete(id);
        if (y==1&&x==1){
            data.put("isDelete",true);
        }else {
            data.put("idDelete",false);
        }
        return data;
    }
    @RequestMapping("/getMonthly")
    public List<Monthly> getMonthly(){
        return weeklyService.getMonthly();
    }
}
