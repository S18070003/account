package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.EmBusinessError;
import com.example.demo.entity.User;
import com.example.demo.service.leadFellow;
import com.example.demo.service.login;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/web/login")
public class LoginController {
    @Autowired
    private login loginService;
    @Autowired
    private com.example.demo.service.User userService;
    @Autowired
    private leadFellow leadFellowService;
    @RequestMapping("/match")
    public JSONObject match(String username, String password){
        JSONObject data=new JSONObject();
         User userList = loginService.login(username);
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
    @RequestMapping("/login")
    public CommonReturnType login(@RequestBody User user){
        String loginName=user.getUsername();
        String password=user.getPassword();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName,password);
        Map<String,Object> returnMsg = new HashMap<String, Object>();
        try {
            subject.login(token);
            User user1=userService.selectUserByUsername(loginName);
            returnMsg.put("username",loginName);
            returnMsg.put("password",password);
            returnMsg.put("loginTips","登陆成功");
            returnMsg.put("realname",user1.getRealname());
            returnMsg.put("type",user1.getType());
            subject.getSession().setAttribute("LOGIN_USER",user1);
            int leaderId=user1.getId();
            List<Integer> fellowlist=leadFellowService.getFellow(leaderId);
            fellowlist.add(leaderId);
            subject.getSession().setAttribute("Fellow",fellowlist);
//            this.httpServletRequest.getSession().setAttribute("LOGIN_USER",user);
            return CommonReturnType.create(returnMsg);
        } catch (UnknownAccountException e) {
            //用户不存在
            throw new BusinessException(EmBusinessError.STUDENT_LOGIN_FAIL);
        } catch (IncorrectCredentialsException e) {
            //密码不正确
            throw new BusinessException(EmBusinessError.STUDENT_LOGIN_FAIL);
        } catch (AuthenticationException e) {
            //throw new BusinessException(EmBusinessError.ARREARS);
            // throw new BusinessException(EmBusinessError.HASE_STOPED);
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        }catch (Exception e) {
            //未知异常UNKNOWN_ERROR
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR);
        }
    }
    @RequestMapping("/loginout")
    public CommonReturnType logout(){
        Subject subject = SecurityUtils.getSubject();
        // SysAdministrator user = (SysAdministrator) subject.getPrincipal();
        subject.logout();
        return CommonReturnType.create("退出成功");
    }
    @RequestMapping("/pleaseLogin")
    public CommonReturnType pleaseLogin(){
        System.out.println("123");
        throw new BusinessException(EmBusinessError.PLEASE_LOGIN);
    }
    @RequestMapping("/noAuth")
    public CommonReturnType noAuth(){
        // throw new BusinessException(EmBusinessError.NO_PERMISSSION);
        throw new BusinessException(EmBusinessError.PLEASE_LOGIN);
    }
}
