package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.table.ProjectProgressWord;
import com.example.demo.entity.RelationProjectWeekly;
import com.example.demo.entity.Weekly;
import com.example.demo.model.Monthly;
import com.example.demo.model.MonthlyDownload;
import com.example.demo.model.NewProjectInfo;
import com.example.demo.model.WeeklyModel;
import com.example.demo.service.project;
import com.example.demo.service.relationProjectWeekly;
import com.example.demo.service.weekly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/web/weekly")
public class WeeklyController {
    @Autowired
    private weekly weeklyService;
    @Autowired
    private relationProjectWeekly relationProjectWeeklyService;
    @Autowired
    private project projectService;
    @RequestMapping("/get")
    public CommonReturnType get(String mode){
        List<WeeklyModel> data=new ArrayList<WeeklyModel>();
        if (mode.equals("Week")){
            data= weeklyService.getWeekly();
        }else if (mode.equals("Month")){
            data= weeklyService.getMonth();
        }else if (mode.equals("All")){
            data= weeklyService.getAll();
        }
        return CommonReturnType.create(data);
    }
    @RequestMapping("/getWeek")
    public List<WeeklyModel> getWeek(){
        return weeklyService.getWeekly();
    }
    @RequestMapping("/insert")
    public CommonReturnType insert(@RequestBody JSONObject json){
//        JSONObject data=new JSONObject();
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
        return CommonReturnType.create(relationProjectWeeklyService.insert(record));
    }
    @RequestMapping("/getByProjectid")
    public CommonReturnType getByProjectid(String projectid){
        return CommonReturnType.create(weeklyService.getByProjectid(projectid));
    }
    @RequestMapping("/Count")
    public CommonReturnType getCount(){
        JSONObject data=new JSONObject();
        int x=weeklyService.getCountWeek();
        int y=weeklyService.getCountMonth();
        int z=weeklyService.getCountAll();
        data.put("Week",x);
        data.put("Month",y);
        data.put("All",z);
        return CommonReturnType.create(data);
    }
    @RequestMapping("/delete")
    @Transactional
    public CommonReturnType delete(int id){
        int x=weeklyService.delete(id);
        int y=relationProjectWeeklyService.delete(id);
        return CommonReturnType.create(x*y);
    }
    @RequestMapping("/downloadAllWeekly")
    public CommonReturnType download(String start,String end,String name,HttpServletResponse response) throws Exception{
      if (start.equals("null")){
          start=null;
      }
      if (end.equals("null")){
          end=null;
      }
        com.example.demo.controller.table.Weekly.getExcel(weeklyService.selectAllByTime(start,end),name,response);
      return (null);
    }
//月报
    @RequestMapping("/getMonthly")
    public CommonReturnType getMonthly(){
        return CommonReturnType.create(weeklyService.getMonthly());
    }
    @RequestMapping("/downloadMonthly")
    public CommonReturnType getMonthlyForDownload(String start,String end,HttpServletResponse response) throws Exception {
//        if (start.equals("null")){    //弃用 的版本
//            start=null;
//        }
//        if(end.equals("null")){
//            end=null;
//        }
//        List<MonthlyDownload> list =weeklyService.getMonthlyForDownload(start,end);
//        List<NewProjectInfo>  list1=projectService.getNewProjectInfo(start,end);

        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(calendar.MONTH, -2); //设置为前2月，可根据需求进行修改
        Date date = calendar.getTime();//获取2个月前的时间
        System.out.println(date);
        List<MonthlyDownload> list =weeklyService.selectBeforeTwoMonth(date);
        List<MonthlyDownload> list1 =weeklyService.selectBehindTwoMonth(date);
        List<MonthlyDownload> list2 =weeklyService.getAboardAndSighCon();
       com.example.demo.controller.table.Monthly.getExcel(list,list1,list2,response);
       return CommonReturnType.create(null);
    }
    @RequestMapping("/test")
    public List<MonthlyDownload> test(String start,String end) throws Exception {
        List<MonthlyDownload> list =weeklyService.getMonthlyForDownload(start,end);
      return list;
    }

    @RequestMapping("/exportWord")
    @Transactional
    public CommonReturnType exportWord(String start,String end,HttpServletResponse response) throws Exception {
        if (start.equals("null")){
            start=null;
        }
        if (end.equals("null")){
            end=null;
        }
        ProjectProgressWord.getWord(weeklyService.selectAllByTime(start,end),response);
        return (null);
    }

    @RequestMapping("/importExcel")//导入excel
    @ResponseBody
    @Transactional
    public CommonReturnType importExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.matches("^.+\\.(?i)(xls)$")){//03版本excel,xls
            weeklyService.importExcel(file,3);
        }else if (fileName.matches("^.+\\.(?i)(xlsx)$")){//07版本,xlsx
            weeklyService.importExcel(file,7);
        }
        return CommonReturnType.create(null);
    }
}
