package com.example.demo.controller;

import com.example.demo.common.BusinessException;
import com.example.demo.common.CommonReturnType;
import com.example.demo.common.EmBusinessError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcc
 * @date 2019/3/25 20:24
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 吃掉不可预知的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e){
        e.printStackTrace();
        Map<String,Object> responseData = new HashMap<>();
        if(e instanceof BusinessException){
            BusinessException businessException =(BusinessException)e;
            responseData.put("errCode",businessException.getErrCode());
            responseData.put("errMsg",businessException.getErrMsg());
        }
        else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg",EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData,"fail");
    }
}
