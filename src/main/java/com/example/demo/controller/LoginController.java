package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserList;
import com.example.demo.service.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private login loginService;
    @RequestMapping("/match")
    public JSONObject match(String username, String password){
        JSONObject data=new JSONObject();
         UserList userList = loginService.login(username);
        String password1=userList.getPassword();
         if(password.equals(password1)){///////////equals()判断内容是否相同，==表示全等，地址和内容都相同
             data.put("isLogin",true);
             data.put("realname",userList.getRealname());
         }else {
             data.put("isLogin",false);
             data.put("realname","");
         }
         return data;
    };

}
