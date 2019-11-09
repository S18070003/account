package com.example.demo.common;

/**
 * @author zcc
 * @date 2019/3/25 20:21
 */
public enum EmBusinessError implements CommonError {
    //通用错误类型
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),
    SAME_OUTTIME(10003,"有相同权限点"),
    PLEASE_LOGIN(10004,"请登录"),
    NO_PERMISSSION(10005,"没有权限"),
    HASE_STOPED(10006,"已停用或已欠费"),

    ADMINISTRATORS(10007,"角色超级管理员不能变更"),
    LOGIN_ALREADY(10008,"你已经登录，请不要重复登录"),
    IP_LOGIN_ALREADY(10009,"此IP已经登录一个账号，请退出后操作"),

    ARREARS(10010,"已欠费，请缴费"),
    //20000为用户信息相关错误
    STUDENT_NOT_EXIST(20001,"账号不存在"),
    STUDENT_LOGIN_FAIL(20002,"请输入正确的帐号或密码"),
    CHOOSE_BUSINESSID(20003,"请选择公司后再试"),
    //30000为课程相关错误信息
    COUREE_ERROR(30001,"信息不存在"),
    FILE_ERROR(30002,"文件超出限制"),

    HAS_QUIZ_USETHISQUESTION(30003,"有考试使用该试题"),
    HAS_CONFERENCE_USETHISQUESTION(30004,"该会议下存在子会议，不能删除，如要删除，请先删除子会议"),


    //40000识别相关
    OCR_FILD(40001,"识别失败"),
    CAR_ALREADE_EXITE(40002,"该车辆信息已经存在"),
    FILE_NOFOUNF(40003,"身份证照片不存在，请完善驾驶员身份信息"),

    //50000添加人员相关
    PERSONE_HASEXITE(50001,"该手机号已经注册"),
    ALREADY_EXISTENCE(50002,"已经存在"),
    NO_RECORD(50003,"无记录"),

    //60000微信注册
    ROLE_ALREADY_EXISTENCE(50002,"已存在相同角色");
    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private int errCode;
    private String errMsg;
    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }
}
