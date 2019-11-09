package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Util.MD5Util;
import com.example.demo.common.CommonReturnType;
import com.example.demo.controller.Param.LeadFellowArray;
import com.example.demo.entity.LeadFellow;
import com.example.demo.service.User;
import com.example.demo.service.leadFellow;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/web/user")
public class UserController {
    @Autowired
    private User userServer;
    @Autowired
    private leadFellow leadFellowService;
    @RequestMapping("/add")
    @Transactional
    public CommonReturnType insert(@RequestBody JSONObject jsonObject){
        com.example.demo.entity.User user=new com.example.demo.entity.User();
        user.setUsername(jsonObject.getString("username"));
        user.setRealname(jsonObject.getString("realname"));
        user.setType(jsonObject.getString("type"));

        String password=jsonObject.getString("password");
        String newpassword= MD5Util.md5(password);
        user.setPassword(newpassword);

        int check=userServer.checkByUsername(jsonObject.getString("username"));
        if (check==0){
            userServer.insert(user);

            LeadFellowArray leadFellowArray =new LeadFellowArray();
            int id=user.getId();
            leadFellowArray.setLeader(id);
           String fellow=jsonObject.getString("fellow");
            System.out.println(fellow);
            if ( fellow!=null && fellow!="" && fellow!="null" && fellow!="[]"){
                JSONArray jsonArray=jsonObject.getJSONArray("fellow");
                leadFellowArray.setFellow(JSONObject.parseArray(jsonArray.toJSONString(), Integer.class));
                return CommonReturnType.create(leadFellowService.add(leadFellowArray));
            }else {
                return CommonReturnType.create(true);
            }
        }else {
            return CommonReturnType.create("exist");
        }
    }
    @RequestMapping("/getAll")
    public CommonReturnType getAll(){
        Map<String,Object> data=new HashMap<String, Object>();
        data.put("User",userServer.selectAll());
        data.put("Fellow",leadFellowService.getAllExtendUser());
        return CommonReturnType.create(data);
    }
    @RequestMapping("/delete")
    public CommonReturnType delete(int id){
        leadFellowService.deleteByFellowId(id);
        return CommonReturnType.create(userServer.delete(id));
    }
    @RequestMapping("/update")
    public CommonReturnType update(@RequestBody JSONObject jsonObject){
        com.example.demo.entity.User user=new com.example.demo.entity.User();
        user.setUsername(jsonObject.getString("username"));
        user.setRealname(jsonObject.getString("realname"));
        user.setType(jsonObject.getString("type"));
        user.setId(jsonObject.getInteger("id"));
        String password=jsonObject.getString("password");
        String newpassword= MD5Util.md5(password);
        user.setPassword(newpassword);
        userServer.update(user);

        int id=jsonObject.getInteger("id");
        String fellow=jsonObject.getString("fellow");
        System.out.println(fellow);
        if ( fellow!=null && fellow!="" && fellow!="null" && fellow!="[]"){
            leadFellowService.deleteByLeaderId(id);
            LeadFellowArray leadFellowArray =new LeadFellowArray();
            leadFellowArray.setLeader(id);
            JSONArray jsonArray=jsonObject.getJSONArray("fellow");
            leadFellowArray.setFellow(JSONObject.parseArray(jsonArray.toJSONString(), Integer.class));
            return CommonReturnType.create(leadFellowService.add(leadFellowArray));
        }else {
            return CommonReturnType.create( leadFellowService.deleteByLeaderId(id));
        }
    }
}
