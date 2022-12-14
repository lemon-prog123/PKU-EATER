package org.example.error;

import org.example.response.CommonReturnType;

//全局错误码定义
public enum EmBusinessError implements CommonError {
    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),
    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    //可在此补充enum错误值
    USER_LOGIN_FAIL(20002, "用户账号或密码不正确")
    ;

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
    //改动对应的errmessage
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
