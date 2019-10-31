package com.example.demo.service.impl;

import com.example.demo.Util.ExcalUtils;
import com.example.demo.common.BusinessException;
import com.example.demo.common.EmBusinessError;
import com.example.demo.dao.ProjectMapper;
import com.example.demo.dao.RelationProjectWeeklyMapper;
import com.example.demo.dao.WeeklyMapper;
import com.example.demo.entity.Project;
import com.example.demo.entity.RelationProjectWeekly;
import com.example.demo.entity.Weekly;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.LedgerBase;
import com.example.demo.model.NewProjectInfo;
import com.example.demo.model.WeeklyBase;
import com.example.demo.service.project;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service
public class projectImpl implements project {
    @Autowired
    public ProjectMapper projectMapper;
    @Autowired
    public WeeklyMapper weeklyMapper;
    @Autowired
    public RelationProjectWeeklyMapper relationProjectWeeklyMapper;
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
        projectMapper.deleteBiddingFromProject(projectid);
        projectMapper.deleteRelationProjectBiddingFromProject(projectid);
        projectMapper.deleteLedgerFromProject(projectid);
        projectMapper.deleteRelationProjectLedgerFromProject(projectid);
        projectMapper.deleteWeeklyFromProject(projectid);
        projectMapper.deleteRelationProjectWeeklyFromProject(projectid);
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
        if (getNum==null){
            getNum="0";
        }
        int num=Integer.parseInt(getNum)+1;
        String num1=Integer.toString(10000+num).substring(1,5);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM"); // 时间字符串产生方式
        String uidTime = format.format(new Date()); // 组合流水号前一部分，时间字符串，如：1601
        code="YS-"+uidTime+"-"+num1;
        return code;
    }
    @Override
    public List<NewProjectInfo> getNewProjectInfo(String starttime,String endtime){
        return projectMapper.getProjectInMonth(starttime,endtime);
    }
    @Override
    public void importExcel(MultipartFile file, Integer type){
        if(type == 7){
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
                XSSFSheet sheet0 = workbook.getSheetAt(0);
                for (int j = 0; j < sheet0.getPhysicalNumberOfRows(); j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    XSSFRow row = sheet0.getRow(j);
                    Project project=new Project();
                    String id=getCode();
                    project.setProjectid(id);
                    project.setProjectname(ExcalUtils.handleStringXSSF(row.getCell(0)));
                    project.setProjectgeneral(ExcalUtils.handleStringXSSF(row.getCell(1)));
                    project.setProjectestimateamount(ExcalUtils.handleDoubleXSSF(row.getCell(2)));
                    project.setProjectcharger(ExcalUtils.handleStringXSSF(row.getCell(3)));
                    project.setDepartment(ExcalUtils.handleStringXSSF(row.getCell(4)));
                    project.setBidid( ExcalUtils.handleStringXSSF(row.getCell(5)));
                    project.setBidreceivedate( ExcalUtils.handleDateXSSF(row.getCell(6)));
                    project.setBidstopdate( ExcalUtils.handleDateXSSF(row.getCell(7)));
                    String region= ExcalUtils.handleStringXSSF(row.getCell(8));
                    if (region =="渤海" || region=="南海东部" ||region=="南海西部"||region=="东海"){
                        project.setBidabroad("国内");
                    }else {
                        project.setBidabroad("海外");
                    }
                    project.setBidregion(region);
                    project.setBidsafegrade( ExcalUtils.handleStringXSSF(row.getCell(9)));
                    project.setBidrecordyn( ExcalUtils.handleStringXSSF(row.getCell(10)));
                    project.setBidguaranteeyn( ExcalUtils.handleStringXSSF(row.getCell(11)));
                    project.setBidguaranteetype( ExcalUtils.handleStringXSSF(row.getCell(12)));
                    project.setBidguaranteemoney( ExcalUtils.handleDoubleXSSF(row.getCell(13)));
                    project.setBidoperator( ExcalUtils.handleStringXSSF(row.getCell(14)));
                    project.setBidoprateregion( ExcalUtils.handleStringXSSF(row.getCell(15)));
                    project.setBidservicetype( ExcalUtils.handleStringXSSF(row.getCell(16)));
                    project.setBidworkload( ExcalUtils.handleStringXSSF(row.getCell(17)));
                    project.setProjectprogress( ExcalUtils.handleStringXSSF(row.getCell(18)));
                    project.setProjectproblem( ExcalUtils.handleStringXSSF(row.getCell(19)));
                    project.setContractyear( ExcalUtils.handleIntegerXSSF(row.getCell(20)));
                    project.setContractdepartment( ExcalUtils.handleStringXSSF(row.getCell(21)));
                    project.setContractopdepartment( ExcalUtils.handleStringXSSF(row.getCell(22)));
                    project.setContractobject( ExcalUtils.handleStringXSSF(row.getCell(23)));

                    project.setProjectbuildtime(new Date());

                    if(!project.getProjectid().equals("")){
                        projectMapper.insertSelective(project);
                    }
                }
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type == 3){
            try {
                HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
                HSSFSheet sheet0 = workbook.getSheetAt(0);
                for (int j = 0; j < sheet0.getPhysicalNumberOfRows(); j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                   HSSFRow row=sheet0.getRow(j);
                    Project project=new Project();

                    String id=getCode();
                    project.setProjectid(id);
                    project.setProjectname(ExcalUtils.handleStringHSSF(row.getCell(0)));
                    project.setProjectgeneral(ExcalUtils.handleStringHSSF(row.getCell(1)));
                    project.setProjectestimateamount(ExcalUtils.handleDoubleHSSF(row.getCell(2)));
                    project.setProjectcharger(ExcalUtils.handleStringHSSF(row.getCell(3)));
                    project.setDepartment(ExcalUtils.handleStringHSSF(row.getCell(4)));
                    project.setBidid( ExcalUtils.handleStringHSSF(row.getCell(5)));
                    project.setBidreceivedate( ExcalUtils.handleDateHSSF(row.getCell(6)));
                    project.setBidstopdate( ExcalUtils.handleDateHSSF(row.getCell(7)));
                    project.setBidregion( ExcalUtils.handleStringHSSF(row.getCell(8)));
                    project.setBidsafegrade( ExcalUtils.handleStringHSSF(row.getCell(9)));
                    project.setBidrecordyn( ExcalUtils.handleStringHSSF(row.getCell(10)));
                    project.setBidguaranteeyn( ExcalUtils.handleStringHSSF(row.getCell(11)));
                    project.setBidguaranteetype( ExcalUtils.handleStringHSSF(row.getCell(12)));
                    project.setBidguaranteemoney( ExcalUtils.handleDoubleHSSF(row.getCell(13)));
                    project.setBidoperator( ExcalUtils.handleStringHSSF(row.getCell(14)));
                    project.setBidoprateregion( ExcalUtils.handleStringHSSF(row.getCell(15)));
                    project.setBidservicetype( ExcalUtils.handleStringHSSF(row.getCell(16)));
                    project.setBidworkload( ExcalUtils.handleStringHSSF(row.getCell(17)));
                    project.setProjectprogress( ExcalUtils.handleStringHSSF(row.getCell(18)));
                    project.setProjectproblem( ExcalUtils.handleStringHSSF(row.getCell(19)));
                    project.setContractyear( ExcalUtils.handleIntegerHSSF(row.getCell(20)));
                    project.setContractdepartment( ExcalUtils.handleStringHSSF(row.getCell(21)));
                    project.setContractopdepartment( ExcalUtils.handleStringHSSF(row.getCell(22)));
                    project.setContractobject( ExcalUtils.handleStringHSSF(row.getCell(23)));
                    project.setProjectbuildtime(new Date());

                    if(!project.getProjectid().equals("")){
                        projectMapper.insertSelective(project);
                    }
                }
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"文件错误");
        }
    }
    @Override
    public String getProjecrIdByProjectName(String projectname){
        return projectMapper.getProjectIdByProjectname(projectname);
    }
}
