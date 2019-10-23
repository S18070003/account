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
import com.example.demo.model.Monthly;
import com.example.demo.model.MonthlyDownload;
import com.example.demo.model.WeeklyModel;
import com.example.demo.service.weekly;
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
public class weeklyImpl implements weekly {
    @Autowired
    public WeeklyMapper weeklyMapper;
    @Autowired
    public ProjectMapper projectMapper;
    @Autowired
    public RelationProjectWeeklyMapper relationProjectWeeklyMapper;
    @Override
    public List<WeeklyModel> getAll(){
        return weeklyMapper.selectAll();
    }
    @Override
    public List<WeeklyModel> selectAllByTime(String starttime,String endtime){
        return weeklyMapper.selectAllByTime(starttime,endtime);
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
    @Override
    public List<Monthly> getMonthly(){
        return weeklyMapper.getMonthly();
    };
    @Override
    public List<MonthlyDownload> getMonthlyForDownload(String starttime,String endtime){
        return weeklyMapper.getMonthlyForDownload(starttime,endtime);
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
                    Weekly weekly=new Weekly();
                    weekly.setProjecthistory(ExcalUtils.handleStringXSSF(row.getCell(1)));
                    weekly.setProjectcurrent(ExcalUtils.handleStringXSSF(row.getCell(2)));
                    weekly.setProjectstarttime(ExcalUtils.handleDateXSSF(row.getCell(3)));
                    weekly.setProjectplan(ExcalUtils.handleStringXSSF(row.getCell(4)));
                    weekly.setProjectdifficulty(ExcalUtils.handleStringXSSF(row.getCell(5)));
                    weekly.setProjectsuggestion(ExcalUtils.handleStringXSSF(row.getCell(6)));
                    weekly.setSubmittime(new Date());

                    weeklyMapper.insertSelective(weekly);

                    int id=weekly.getId();
                    String name=ExcalUtils.handleStringXSSF(row.getCell(0));
                    String projectid=projectMapper.getProjectIdByProjectname(name);
                    if (projectid ==null){
                        continue; //如果查出结果为空，继续操作
                    }
                    RelationProjectWeekly relationProjectWeekly=new RelationProjectWeekly();
                    relationProjectWeekly.setWeeklyid(id);
                    relationProjectWeekly.setProjectid(projectid);

                    relationProjectWeeklyMapper.insertSelective(relationProjectWeekly);

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
                    HSSFRow row = sheet0.getRow(j);
                    Weekly weekly=new Weekly();
                    weekly.setProjecthistory(ExcalUtils.handleStringHSSF(row.getCell(1)));
                    weekly.setProjectcurrent(ExcalUtils.handleStringHSSF(row.getCell(2)));
                    weekly.setProjectstarttime(ExcalUtils.handleDateHSSF(row.getCell(3)));
                    weekly.setProjectplan(ExcalUtils.handleStringHSSF(row.getCell(4)));
                    weekly.setProjectdifficulty(ExcalUtils.handleStringHSSF(row.getCell(5)));
                    weekly.setProjectsuggestion(ExcalUtils.handleStringHSSF(row.getCell(6)));
                    weekly.setSubmittime(new Date());

                    weeklyMapper.insertSelective(weekly);
                    int id=weekly.getId();
                    String projectid=projectMapper.getProjectIdByProjectname(ExcalUtils.handleStringHSSF(row.getCell(0)));

                    RelationProjectWeekly relationProjectWeekly=new RelationProjectWeekly();
                    relationProjectWeekly.setWeeklyid(id);
                    relationProjectWeekly.setProjectid(projectid);

                    relationProjectWeeklyMapper.insertSelective(relationProjectWeekly);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"文件错误");
        }
    }
}
