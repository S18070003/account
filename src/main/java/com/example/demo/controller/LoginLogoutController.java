package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;

import com.example.demo.common.EmBusinessError;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@ResponseBody
@RequestMapping("/web")
public class LoginLogoutController {

    @RequestMapping("/login")
    public CommonReturnType login(@RequestBody  JSONObject user) {
        String loginName=user.getString("loginName");
        String password=user.getString("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName,password);
        Map<String,Object> returnMsg = new HashMap<String, Object>();
        try {
            subject.login(token);
            returnMsg.put("user",user);
            returnMsg.put("loginTips","登陆成功");
//            returnMsg.put("userType",sysAdministratorService.getRoleType());
            subject.getSession().setAttribute("LOGIN_USER",user);
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
    @RequestMapping("/logout")
    public CommonReturnType logout(){
        Subject subject = SecurityUtils.getSubject();
       // SysAdministrator user = (SysAdministrator) subject.getPrincipal();
        subject.logout();
        return CommonReturnType.create("退出成功");
    }

    @RequestMapping("/pleaseLogin")
    public CommonReturnType pleaseLogin(){
        throw new BusinessException(EmBusinessError.PLEASE_LOGIN);
    }

    @RequestMapping("/sessionTest")
    public CommonReturnType SessionTest(){
        Subject subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        return CommonReturnType.create(session);
    }


    @RequestMapping("/noAuth")
    public CommonReturnType noAuth(){
       // throw new BusinessException(EmBusinessError.NO_PERMISSSION);
        throw new BusinessException(EmBusinessError.PLEASE_LOGIN);
    }

}
