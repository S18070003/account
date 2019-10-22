package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.controller.table.Ledger;
import com.example.demo.entity.Bidding;
import com.example.demo.entity.RelationProjectBidding;
import com.example.demo.model.BiddingAll;
import com.example.demo.model.BiddingSelect;
import com.example.demo.model.HomeAllBidding;
import com.example.demo.service.bidding;
import com.example.demo.service.relationProjectBidding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/bidding")
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
    public JSONObject delete(int id,String projectid){
        JSONObject data=new JSONObject();
        int y=biddingService.delete(id);
        int z=relationProjectBiddingService.delete(id,projectid);
        System.out.println(z);
        if(y+z==2){
            data.put("isDelete",true);
        }else{
            data.put("isDelete",false);
        }
        return data;
    }
    @RequestMapping("/selectByProjectid")
    public JSONObject select(String projectid){
        JSONObject data=new JSONObject();
        Bidding bidding=biddingService.getByProjectid(projectid);
        System.out.println(bidding);
        if(bidding==null){
            data.put("result",false);
        }else {
            data.put("result",true);
            data.put("bidding",bidding);
        }
        return data;
    }
    @RequestMapping("/insert")
    public JSONObject insert(@RequestBody JSONObject json){
        JSONObject data=new JSONObject();
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
        int result=relationProjectBiddingService.insert(record);
        if(result==1){
            data.put("isinsert",true);
        }else {
            data.put("isinsert",false);
        }
        return data;
    }
    @RequestMapping("/update")
    public JSONObject update(@RequestBody JSONObject json){
        System.out.println(json.getInteger("id"));
        Bidding bidding=new Bidding();
        bidding.setId(json.getInteger("id"));
        bidding.setBiddiscardreason(json.getString("biddiscardreason"));
        bidding.setBidlossreason(json.getString("bidlossreason"));
        bidding.setBidopenresult(json.getString("bidopenresult"));
        bidding.setBidreview(json.getString("bidreview"));
        bidding.setBidtargetmoney(json.getDouble("bidtargetmoney"));
        JSONObject data=new JSONObject();
        int x=biddingService.update(bidding);
        if (x==1){
            data.put("isUpdate",true);
        }else {
            data.put("isUpdate",false);
        }
        return data;
    }
    @RequestMapping("/getHomeBiddingAll")
    public List<BiddingAll> getHomeBiddingAll(@RequestBody BiddingSelect biddingSelect){
        return biddingService.AllBiddingAll(biddingSelect);
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
}
