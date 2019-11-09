package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.table.ProjectProgressWord;
import com.example.demo.dao.BiddingMapper;
import com.example.demo.dao.ContractLedgerMapper;
import com.example.demo.dao.WeeklyMapper;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.LedgerBase;
import com.example.demo.model.WeeklyBase;
import com.example.demo.service.project;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/web/project")
public class ProjectController {
    @Autowired
    private project projectService;
    @Autowired
    private WeeklyMapper weeklyMapper;
    @Autowired
    private ContractLedgerMapper contractLedgerMapper;
    @Autowired
    private BiddingMapper biddingMapper;
    @RequestMapping("/getAll")
    public CommonReturnType getAll(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        List<Integer> checkRange=(List<Integer>)session.getAttribute("Fellow");
        return CommonReturnType.create(projectService.getAll(checkRange));
    }
    @RequestMapping("/submit")
    public CommonReturnType insert(@RequestBody Project project){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user=(User)session.getAttribute("LOGIN_USER");
        int id=user.getId();
        project.setProjectbuilder(id);
        project.setProjectbuildtime(new Date());

        return CommonReturnType.create(projectService.insert(project));
    }
    @RequestMapping("/delete")
    @Transactional
    public CommonReturnType delete(@RequestBody JSONObject jsonObject){
        String projectid=jsonObject.getString("projectid");
        return CommonReturnType.create(projectService.delete(projectid));
    }
    @RequestMapping("/select")
    public CommonReturnType select( @RequestBody JSONObject jsonObject){
        String projectid =jsonObject.getString("projectid");
        return CommonReturnType.create(projectService.select(projectid));
    }
    @RequestMapping("/update")
    public CommonReturnType update(@RequestBody Project project){
        return CommonReturnType.create(projectService.update(project));
    }
    @RequestMapping("/getWeeklyBase")
    public CommonReturnType getWeeklyBase(String projectid){
        return CommonReturnType.create(projectService.getWeeklyBase(projectid));
    }
    @RequestMapping("/getBiddingBase")
    public CommonReturnType getBiddingBase(String projectid){
        return CommonReturnType.create(projectService.getBiddingBase(projectid));
    }
    @RequestMapping("/getLedgerBase")
    public CommonReturnType getLedgerBase(String projectid){
        return CommonReturnType.create(projectService.getLedgerBase(projectid));
    }
    @RequestMapping("/getCode")
    public CommonReturnType getCode(){
        return CommonReturnType.create(projectService.getCode());
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
}
