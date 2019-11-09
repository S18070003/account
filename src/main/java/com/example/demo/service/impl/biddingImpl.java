package com.example.demo.service.impl;

import com.example.demo.Util.ExcalUtils;
import com.example.demo.common.BusinessException;
import com.example.demo.common.EmBusinessError;
import com.example.demo.dao.BiddingMapper;
import com.example.demo.dao.ProjectMapper;
import com.example.demo.dao.RelationProjectBiddingMapper;
import com.example.demo.entity.Bidding;
import com.example.demo.entity.ContractLedger;
import com.example.demo.entity.RelationProjectBidding;
import com.example.demo.entity.RelationProjectLedger;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingBase;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import com.example.demo.service.bidding;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class biddingImpl implements bidding {
    @Autowired
    public BiddingMapper biddingMapper;
    @Autowired
    public ProjectMapper projectMapper;
    @Autowired
    public RelationProjectBiddingMapper relationProjectBiddingMapper;
    @Override
    public List<Bidding> getAll(){ return  biddingMapper.getAll();}
    @Override
    public int delete(int id){
        return biddingMapper.deleteByPrimaryKey(id);
    }
    @Override
    public Bidding getByProjectid(String projectid){
        return biddingMapper.getByProjectid(projectid);
    }
    @Override
    public int insert(Bidding bidding){
        return biddingMapper.insertSelective(bidding);
    }
    @Override
    public int update(Bidding bidding){
        return  biddingMapper.updateByPrimaryKeySelective(bidding);
    }
    @Override
    public List<HomeAllBidding> getHomeBiddingAll(){
        return biddingMapper.getHomeBiddingAll();
    }

    @Override
    public List<BiddingAll> AllBiddingAll(BiddingSelect record) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        List<Integer> checkRange=(List<Integer>)session.getAttribute("Fellow");
        return biddingMapper.AllBiddingAll(record,checkRange);
    }
    @Override
    public List<BiddingAll> ProjectBiddingAll(String id){
        return biddingMapper.ProjectBiddingAll(id);
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
                    Bidding bidding=new Bidding();
                    bidding.setBidtargetmoney(ExcalUtils.handleDoubleXSSF(row.getCell(1)));
                    bidding.setBidreview(ExcalUtils.handleStringXSSF(row.getCell(2)));
                    bidding.setBidopenresult(ExcalUtils.handleStringXSSF(row.getCell(3)));
                    bidding.setBiddiscardreason(ExcalUtils.handleStringXSSF(row.getCell(4)));
                    bidding.setBidlossreason(ExcalUtils.handleStringXSSF(row.getCell(5)));
                    bidding.setSubmittime(new Date());

                    String name=ExcalUtils.handleStringXSSF(row.getCell(0));
                    int x=relationProjectBiddingMapper.getBiddingIdByProjectName(name);
                    if (x==0){
                        biddingMapper.insertSelective(bidding);

                        int id=bidding.getId();

                        String projectid=projectMapper.getProjectIdByProjectname(name);
                        if (projectid ==null){
                            continue; //如果查出结果为空，跳出继续操作
                        }
                        RelationProjectBidding relationProjectBidding=new RelationProjectBidding();

                        relationProjectBidding.setProjectid(projectid);
                        relationProjectBidding.setBiddingid(id);

                        relationProjectBiddingMapper.insertSelective(relationProjectBidding);
                    }else {
                        bidding.setId(x);
                        biddingMapper.updateByPrimaryKeySelective(bidding);
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
                    HSSFRow row = sheet0.getRow(j);
                    Bidding bidding=new Bidding();
                    bidding.setBidtargetmoney(ExcalUtils.handleDoubleHSSF(row.getCell(1)));
                    bidding.setBidreview(ExcalUtils.handleStringHSSF(row.getCell(2)));
                    bidding.setBidopenresult(ExcalUtils.handleStringHSSF(row.getCell(3)));
                    bidding.setBiddiscardreason(ExcalUtils.handleStringHSSF(row.getCell(4)));
                    bidding.setBidlossreason(ExcalUtils.handleStringHSSF(row.getCell(5)));
                    bidding.setSubmittime(new Date());

                    String name=ExcalUtils.handleStringHSSF(row.getCell(0));
                    int x=relationProjectBiddingMapper.getBiddingIdByProjectName(name);
                    if (x==0){
                        biddingMapper.insertSelective(bidding);
                        int id=bidding.getId();

                        String projectid=projectMapper.getProjectIdByProjectname(name);
                        if (projectid ==null){
                            continue; //如果查出结果为空，跳出继续操作
                        }
                        RelationProjectBidding relationProjectBidding=new RelationProjectBidding();
                        relationProjectBidding.setProjectid(projectid);
                        relationProjectBidding.setBiddingid(id);
                        relationProjectBiddingMapper.insertSelective(relationProjectBidding);
                    }else {
                        bidding.setId(x);
                        biddingMapper.updateByPrimaryKeySelective(bidding);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"文件错误");
        }
    }
}
