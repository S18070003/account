package com.example.demo.service.impl;

import com.example.demo.Util.ExcalUtils;
import com.example.demo.common.BusinessException;
import com.example.demo.common.EmBusinessError;
import com.example.demo.dao.ContractLedgerMapper;
import com.example.demo.dao.ProjectMapper;
import com.example.demo.dao.RelationProjectLedgerMapper;
import com.example.demo.dao.RelationProjectWeeklyMapper;
import com.example.demo.entity.ContractLedger;
import com.example.demo.entity.RelationProjectLedger;
import com.example.demo.entity.RelationProjectWeekly;
import com.example.demo.entity.Weekly;
import com.example.demo.model.HomeAllLedger;
import com.example.demo.model.LedgerSelect;
import com.example.demo.service.ledger;
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
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ledgerImpl implements ledger {
    @Autowired
    public ContractLedgerMapper contractLedgerMapper;
    @Autowired
    public ProjectMapper projectMapper;
    @Autowired
    public RelationProjectLedgerMapper relationProjectLedgerMapper;
    @Override
    public List<ContractLedger> getAll(){
        return contractLedgerMapper.getAll();
    }
    @Override
    public int insert(ContractLedger contractLedger){
        return contractLedgerMapper.insertSelective(contractLedger);
    }
    @Override
    public List<ContractLedger> getByProjectId(String id){
        return contractLedgerMapper.getByProjectid(id);
    }
    @Override
    public  int update(ContractLedger contractLedger){
        return contractLedgerMapper.updateByPrimaryKeySelective(contractLedger);
    }
    @Override
    public  int delete(int id){
        return contractLedgerMapper.deleteByPrimaryKey(id);
    }
    @Override
    public List<HomeAllLedger> getHomeLedgerAll(LedgerSelect ledgerSelect){
        return contractLedgerMapper.HomeLedgerAll(ledgerSelect);
    }
    @Override
    public List<HomeAllLedger> ProjectLedgerAll(String id){
        return contractLedgerMapper.ProjectLedgerAll(id);
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

                    ContractLedger contractLedger=new ContractLedger();
                    contractLedger.setContractid(ExcalUtils.handleStringXSSF(row.getCell(1)));
                    contractLedger.setContractid2(ExcalUtils.handleStringXSSF(row.getCell(2)));
                    contractLedger.setContractsigndate(ExcalUtils.handleDateXSSF(row.getCell(3)));
                    contractLedger.setConstructionstartdate(ExcalUtils.handleDateXSSF(row.getCell(4)));
                    contractLedger.setConstructionenddate(ExcalUtils.handleDateXSSF(row.getCell(5)));
                    contractLedger.setContractmoneypredict0cny(ExcalUtils.handleDoubleXSSF(row.getCell(6)));
                    contractLedger.setContractmoneypredict0usd(ExcalUtils.handleDoubleXSSF(row.getCell(7)));
                    contractLedger.setContractmoneypredicttotal0rmb(ExcalUtils.handleDoubleXSSF(row.getCell(8)));
                    contractLedger.setContractmoneypredicttotal0usd(ExcalUtils.handleDoubleXSSF(row.getCell(9)));
                    contractLedger.setContractmoneypredict1rmb(ExcalUtils.handleDoubleXSSF(row.getCell(10)));
                    contractLedger.setContractmoneypredict2rmb(ExcalUtils.handleDoubleXSSF(row.getCell(11)));
                    contractLedger.setContractmoneypredict3rmb(ExcalUtils.handleDoubleXSSF(row.getCell(12)));
                    contractLedger.setContractmoneypredict4rmb(ExcalUtils.handleDoubleXSSF(row.getCell(13)));
                    contractLedger.setContractmoneypredict5rmb(ExcalUtils.handleDoubleXSSF(row.getCell(14)));
                    contractLedger.setDailycostrmb(ExcalUtils.handleDoubleXSSF(row.getCell(15)));
                    contractLedger.setContractsignperson(ExcalUtils.handleStringXSSF(row.getCell(16)));
                    contractLedger.setContractbelongcnooc(ExcalUtils.handleStringXSSF(row.getCell(17)));
                    contractLedger.setContractinprocess(ExcalUtils.handleStringXSSF(row.getCell(18)));
                    contractLedger.setContractsignificant(ExcalUtils.handleStringXSSF(row.getCell(19)));
                    contractLedger.setContractyearlycnooc(ExcalUtils.handleStringXSSF(row.getCell(20)));
                    contractLedger.setComments(ExcalUtils.handleStringXSSF(row.getCell(21)));
                    contractLedger.setSubmittime(new Date());



                    contractLedgerMapper.insertSelective(contractLedger);

                    int id=contractLedger.getId();
                    String name=ExcalUtils.handleStringXSSF(row.getCell(0));
                    String projectid=projectMapper.getProjectIdByProjectname(name);
                    if (projectid ==null){
                        continue; //如果查出结果为空，跳出继续操作
                    }
                    RelationProjectLedger relationProjectLedger=new RelationProjectLedger();
                    relationProjectLedger.setProjectid(projectid);
                    relationProjectLedger.setLedgerid(id);

                    relationProjectLedgerMapper.insertSelective(relationProjectLedger);

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

                    ContractLedger contractLedger=new ContractLedger();
                    contractLedger.setContractid(ExcalUtils.handleStringHSSF(row.getCell(1)));
                    contractLedger.setContractid2(ExcalUtils.handleStringHSSF(row.getCell(2)));
                    contractLedger.setContractsigndate(ExcalUtils.handleDateHSSF(row.getCell(3)));
                    contractLedger.setConstructionstartdate(ExcalUtils.handleDateHSSF(row.getCell(4)));
                    contractLedger.setConstructionenddate(ExcalUtils.handleDateHSSF(row.getCell(5)));
                    contractLedger.setContractmoneypredict0cny(ExcalUtils.handleDoubleHSSF(row.getCell(6)));
                    contractLedger.setContractmoneypredict0usd(ExcalUtils.handleDoubleHSSF(row.getCell(7)));
                    contractLedger.setContractmoneypredicttotal0rmb(ExcalUtils.handleDoubleHSSF(row.getCell(8)));
                    contractLedger.setContractmoneypredicttotal0usd(ExcalUtils.handleDoubleHSSF(row.getCell(9)));
                    contractLedger.setContractmoneypredict1rmb(ExcalUtils.handleDoubleHSSF(row.getCell(10)));
                    contractLedger.setContractmoneypredict2rmb(ExcalUtils.handleDoubleHSSF(row.getCell(11)));
                    contractLedger.setContractmoneypredict3rmb(ExcalUtils.handleDoubleHSSF(row.getCell(12)));
                    contractLedger.setContractmoneypredict4rmb(ExcalUtils.handleDoubleHSSF(row.getCell(13)));
                    contractLedger.setContractmoneypredict5rmb(ExcalUtils.handleDoubleHSSF(row.getCell(14)));
                    contractLedger.setDailycostrmb(ExcalUtils.handleDoubleHSSF(row.getCell(15)));
                    contractLedger.setContractsignperson(ExcalUtils.handleStringHSSF(row.getCell(16)));
                    contractLedger.setContractbelongcnooc(ExcalUtils.handleStringHSSF(row.getCell(17)));
                    contractLedger.setContractinprocess(ExcalUtils.handleStringHSSF(row.getCell(18)));
                    contractLedger.setContractsignificant(ExcalUtils.handleStringHSSF(row.getCell(19)));
                    contractLedger.setContractyearlycnooc(ExcalUtils.handleStringHSSF(row.getCell(20)));
                    contractLedger.setComments(ExcalUtils.handleStringHSSF(row.getCell(21)));
                    contractLedger.setSubmittime(new Date());



                    contractLedgerMapper.insertSelective(contractLedger);

                    int id=contractLedger.getId();
                    String name=ExcalUtils.handleStringHSSF(row.getCell(0));
                    String projectid=projectMapper.getProjectIdByProjectname(name);
                    if (projectid ==null){
                        continue; //如果查出结果为空，跳出继续操作
                    }
                    RelationProjectLedger relationProjectLedger=new RelationProjectLedger();
                    relationProjectLedger.setProjectid(projectid);
                    relationProjectLedger.setLedgerid(id);

                    relationProjectLedgerMapper.insertSelective(relationProjectLedger);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"文件错误");
        }
    }
}
