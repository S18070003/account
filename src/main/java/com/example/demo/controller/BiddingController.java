package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.table.Ledger;
import com.example.demo.controller.table.ProjectProgressWord;
import com.example.demo.entity.Bidding;
import com.example.demo.entity.RelationProjectBidding;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import com.example.demo.service.bidding;
import com.example.demo.service.relationProjectBidding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/web/bidding")
public class BiddingController {
    @Autowired
    private bidding biddingService;
    @Autowired
    private relationProjectBidding relationProjectBiddingService;
    @RequestMapping("/getAll")
    public List<Bidding> getAll(){
      return  biddingService.getAll();
    }
    @RequestMapping("/delete")
    public CommonReturnType delete(int id,String projectid){
        int y=biddingService.delete(id);
        int z=relationProjectBiddingService.delete(id,projectid);
        return CommonReturnType.create(y*z);
    }
    @RequestMapping("/selectByProjectid")
    public CommonReturnType select(String projectid){
        return CommonReturnType.create(biddingService.getByProjectid(projectid));
    }

    /**
     *
     * @param json
     * @return
     */
    @RequestMapping("/insert")
    public CommonReturnType insert(@RequestBody JSONObject json){
//        JSONObject data=new JSONObject();
        Bidding bidding=new Bidding();
        bidding.setBiddiscardreason(json.getString("biddiscardreason"));
        bidding.setBidlossreason(json.getString("bidlossreason"));
        bidding.setBidopenresult(json.getString("bidopenresult"));
        bidding.setBidreview(json.getString("bidreview"));
        bidding.setBidtargetmoney(json.getDouble("bidtargetmoney"));
        bidding.setSubmittime(new Date());
        biddingService.insert(bidding);
        int newrow=bidding.getId();
        System.out.println(newrow);
        RelationProjectBidding record = new RelationProjectBidding();
        record.setBiddingid(newrow);
        record.setProjectid(json.getString("projectid"));
        return CommonReturnType.create(relationProjectBiddingService.insert(record));
    }
    @RequestMapping("/update")
    public CommonReturnType update(@RequestBody Bidding bidding){
        return CommonReturnType.create(biddingService.update(bidding));
    }
    @RequestMapping("/getHomeBiddingAll")
    public CommonReturnType getHomeBiddingAll(@RequestBody BiddingSelect biddingSelect){
        return CommonReturnType.create(biddingService.AllBiddingAll(biddingSelect));
    }
    @RequestMapping("/download")
    public void export(@RequestParam("department") String department,@RequestParam("start")String start,@RequestParam("end")String end,HttpServletResponse response) throws Exception{
        BiddingSelect biddingSelect=new BiddingSelect();

        if (!department.equals("null")){
            biddingSelect.setDepartment(department);
        }else {
            biddingSelect.setDepartment(null);
        }
        if (!start.equals("null")){
            biddingSelect.setBidreceivedate(new Date(Long.parseLong(start)));
        }else {
            biddingSelect.setBidreceivedate(null);
        }
       if (!end.equals("null")){
           biddingSelect.setBidstopdate(new Date(Long.parseLong(end)));
       }else {
           biddingSelect.setBidstopdate(null);
       }
        com.example.demo.controller.table.Bidding.getXlsx(biddingService.AllBiddingAll(biddingSelect),response);
    }
    @RequestMapping("/downloadProjectBidding")
    public void export1(HttpServletResponse response,String projectid) throws Exception{
        com.example.demo.controller.table.Bidding.getXlsx(biddingService.ProjectBiddingAll(projectid),response);
    }
    @RequestMapping("/importExcel")//导入excel
    @ResponseBody
    @Transactional
    public CommonReturnType importExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName.matches("^.+\\.(?i)(xls)$")){//03版本excel,xls
            biddingService.importExcel(file,3);
        }else if (fileName.matches("^.+\\.(?i)(xlsx)$")){//07版本,xlsx
            biddingService.importExcel(file,7);
        }
        return CommonReturnType.create(null);
    }
}
