package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.table.Ledger;
import com.example.demo.controller.table.VehicelTechnology;
import com.example.demo.entity.ContractLedger;
import com.example.demo.entity.RelationProjectLedger;
import com.example.demo.model.HomeAllLedger;
import com.example.demo.model.LedgerSelect;
import com.example.demo.service.ledger;
import com.example.demo.service.relationProjectLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/web/ledger")
public class LedgerControlller {
    @Autowired
    private ledger ledgerSercive;
    @Autowired
    private relationProjectLedger relationProjectLedgerService;
    @RequestMapping("/getAll")
    public List<ContractLedger> getAll() throws Exception{
        return ledgerSercive.getAll();
    }
    @RequestMapping("/insert")
    public CommonReturnType insert(@RequestBody JSONObject json){
        ContractLedger contractLedger = new ContractLedger();
        contractLedger.setContractid(json.getString("contractid"));
        contractLedger.setContractid2(json.getString("contractid2"));
        contractLedger.setContractsigndate(json.getDate("contractsigndate"));
        contractLedger.setConstructionstartdate(json.getDate("constructionstartdate"));
        contractLedger.setConstructionenddate(json.getDate("constructionenddate"));
        contractLedger.setContractmoneypredict0cny(json.getDouble("contractmoneypredict0cny"));
        contractLedger.setContractmoneypredict0usd(json.getDouble("contractmoneypredict0usd"));
        contractLedger.setContractmoneypredicttotal0rmb(json.getDouble("contractmoneypredicttotal0rmb"));
        contractLedger.setContractmoneypredicttotal0usd(json.getDouble("contractmoneypredicttotal0usd"));
        contractLedger.setContractmoneypredict1rmb(json.getDouble("contractmoneypredict1rmb"));
        contractLedger.setContractmoneypredict2rmb(json.getDouble("contractmoneypredict2rmb"));
        contractLedger.setContractmoneypredict3rmb(json.getDouble("contractmoneypredict3rmb"));
        contractLedger.setContractmoneypredict4rmb(json.getDouble("contractmoneypredict4rmb"));
        contractLedger.setContractmoneypredict5rmb(json.getDouble("contractmoneypredict5rmb"));
        contractLedger.setDailycostrmb(json.getDouble("dailycostrmb"));
        contractLedger.setContractsignperson(json.getString("contractsignperson"));
        contractLedger.setContractbelongcnooc(json.getString("contractbelongcnooc"));
        contractLedger.setContractinprocess(json.getString("contractinprocess"));
        contractLedger.setContractsignificant(json.getString("contractsignificant"));
        contractLedger.setContractyearlycnooc(json.getString("contractyearlycnooc"));
        contractLedger.setComments(json.getString("comments"));
        contractLedger.setSubmittime(new Date());

        ledgerSercive.insert(contractLedger);
        int x=contractLedger.getId();

        RelationProjectLedger relation=new RelationProjectLedger();

        String projectid=json.getString("projectid");
        relation.setProjectid(projectid);
        relation.setLedgerid(x);
//        int result=relationProjectLedgerService.insert(relation);
        return CommonReturnType.create(relationProjectLedgerService.insert(relation));
    }
    @RequestMapping("/getByProjectId")
    public CommonReturnType getByProjectId(String projectid){
        return CommonReturnType.create(ledgerSercive.getByProjectId(projectid));
    }
    @RequestMapping("/update")
    public CommonReturnType update(@RequestBody ContractLedger contractLedger){
        return CommonReturnType.create(ledgerSercive.update(contractLedger));
    }
    @RequestMapping("/delete")
    public CommonReturnType delete(int id){
        int x=ledgerSercive.delete(id);
        int y=relationProjectLedgerService.delete(id);
        return CommonReturnType.create(x*y);
    }
    @RequestMapping("/HomeLedger")
    public CommonReturnType getHomeLedgerAll(@RequestBody LedgerSelect ledgerSelect){
        return CommonReturnType.create(ledgerSercive.getHomeLedgerAll(ledgerSelect));
    }
//    @RequestMapping("/download")
//    public void export() throws Exception{
//        List<List<String[]>> tabledataList = new ArrayList<>();
//        List<String[]> sheet1 = new ArrayList<>();
//
//        List<ContractLedger> list=ledgerSercive.getAll();
//        int l=list.size();
//        for (int i = 0; i < 2; i++) {
//            sheet1.add(new String[]{"1", "2019-1-1", "50km", "简单维修", "瞎修", "家里修", "没有合格证", "自己登记的"});
//        }
//        tabledataList.add(sheet1);
//        VehicelTechnology.getXlsx(tabledataList);
//    }
    @RequestMapping("/download1")
    public void export1(String projectid,String projectname,String contractid,String contractyear,String contractid2,String contractsignperson,
            String contractbelongcnooc,String contractinprocess,String contractsignificant,String contractyearlycnooc,String starttime
            ,String endtime,HttpServletResponse response) throws Exception{
        //复杂一点的
//        demoTable.Excel(ledgerSercive.getHomeLedgerAll());
        //复制假数据的，简单
        LedgerSelect ledgerSelect=new LedgerSelect();
        if (!projectid.equals("null")){
            ledgerSelect.setProjectid(projectid);
        }else {
            ledgerSelect.setProjectid(null);
        }
        if (!projectname.equals("null")){
            ledgerSelect.setProjectname(projectname);
        }else {
            ledgerSelect.setProjectname(null);
        }
        if (!contractid.equals("null")){
            ledgerSelect.setContractid(contractid);
        }else {
            ledgerSelect.setContractid(null);
        }
        if (!contractid2.equals("null")){
            ledgerSelect.setContractid2(contractid2);
        }else {
            ledgerSelect.setContractid2(null);
        }
        if (!contractyear.equals("null")){
            ledgerSelect.setContractyear(Integer.parseInt(contractyear));
        }else {
            ledgerSelect.setContractyear(0);
        }
        if (!contractsignperson.equals("null")){
            ledgerSelect.setContractsignperson(contractsignperson);
        }else {
            ledgerSelect.setContractsignperson(null);
        }
        if (!contractbelongcnooc.equals("null")){
            ledgerSelect.setContractbelongcnooc(contractbelongcnooc);
        }else {
            ledgerSelect.setContractbelongcnooc(null);
        }
        if (!contractinprocess.equals("null")){
            ledgerSelect.setContractinprocess(contractinprocess);
        }else {
            ledgerSelect.setContractinprocess(null);
        }
        if (!contractsignificant.equals("null")){
            ledgerSelect.setContractsignificant(contractsignificant);
        }else {
            ledgerSelect.setContractsignificant(null);
        }
        if (!contractyearlycnooc.equals("null")){
            ledgerSelect.setContractyearlycnooc(contractyearlycnooc);
        }else {
            ledgerSelect.setContractyearlycnooc(null);
        }
        if (!starttime.equals("null")){
            ledgerSelect.setStarttime(new Date(Long.parseLong(starttime)));
        }else {
            ledgerSelect.setStarttime(null);
        }
        if (!endtime.equals("null")){
            ledgerSelect.setStarttime(new Date(Long.parseLong(endtime)));
        }else {
            ledgerSelect.setStarttime(null);
        }
        Ledger.getXlsx(ledgerSercive.getHomeLedgerAll(ledgerSelect),response);
//        Ledger.downloadExcelModle(response);
    }
    @RequestMapping("/downloadInProject")
    public void export2(HttpServletResponse response,String projectid) throws Exception{
        //复杂一点的
//        demoTable.Excel(ledgerSercive.getHomeLedgerAll());
        //复制假数据的，简单
        Ledger.getXlsx(ledgerSercive.ProjectLedgerAll(projectid),response);
//        Ledger.downloadExcelModle(response);
    }

    @RequestMapping("/importExcel")//导入excel
    @ResponseBody
    @Transactional
    public CommonReturnType importExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.matches("^.+\\.(?i)(xls)$")){//03版本excel,xls
            ledgerSercive.importExcel(file,3);
        }else if (fileName.matches("^.+\\.(?i)(xlsx)$")){//07版本,xlsx
            ledgerSercive.importExcel(file,7);
        }
        return CommonReturnType.create(null);
    }
}
