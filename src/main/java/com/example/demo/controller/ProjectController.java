package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.table.ProjectProgressWord;
import com.example.demo.entity.Project;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.LedgerBase;
import com.example.demo.model.WeeklyBase;
import com.example.demo.service.project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private project projectService;
    @RequestMapping("/getAll")
    public List<Project> getAll(){
        return projectService.getAll();
    }
    @RequestMapping("/submit")
    public JSONObject insert(@RequestBody Project project){
        JSONObject data=new JSONObject();
        int x=projectService.insert(project);
        if(x==1){
            data.put("isinsert",true);
        }else {
            data.put("isinsert",false);
        }
        return data;
    }
    @RequestMapping("/delete")
    public JSONObject delete(String projectid){
        JSONObject data=new JSONObject();
        int x=projectService.delete(projectid);
        if(x==1){
            data.put("isDelete",true);
        }else {
            data.put("isDelete",false);
        }
        return data;
    }
    @RequestMapping("/select")
    public Project select(String projectid){
        Project project=new Project();
        project=projectService.select(projectid);
        if(project!=null){
            return project;
        }else {
            Project project1=new Project();
            project1.setProjectid("error");
            return project1;
        }

    }
    @RequestMapping("/update")
    public JSONObject update(@RequestBody Project project){
        JSONObject data=new JSONObject();
        int x=projectService.update(project);
        if(x==1){
            data.put("isUpdate",true);
        }else {
            data.put("isUpdate",false);
        }
        return data;
    }
    @RequestMapping("/getWeeklyBase")
    public WeeklyBase getWeeklyBase(String projectid){
        return projectService.getWeeklyBase(projectid);
    }
    @RequestMapping("/getBiddingBase")
    public BiddingBase getBiddingBase(String projectid){
        return projectService.getBiddingBase(projectid);
    }
    @RequestMapping("/getLedgerBase")
    public LedgerBase getLedgerBase(String projectid){
        return projectService.getLedgerBase(projectid);
    }
    @RequestMapping("/getCode")
    public String getCode(){
        return projectService.getCode();
    }

    @RequestMapping("/importExcel")//导入excel
    @ResponseBody
    @Transactional
    public CommonReturnType importExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.matches("^.+\\.(?i)(xls)$")){//03版本excel,xls
            projectService.importExcel(file,3);
        }else if (fileName.matches("^.+\\.(?i)(xlsx)$")){//07版本,xlsx
            projectService.importExcel(file,7);
        }
        return CommonReturnType.create(null);
    }
    @RequestMapping("/exportWord")
    @Transactional
    public CommonReturnType exportWord(HttpServletResponse response) throws Exception {
        ProjectProgressWord.getWord(projectService.getAll(),response);
        return (null);
    }
}
