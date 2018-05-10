package com.example.demo.core.ret;


public class RetResult<T> {

    /**
     * code为状态码、msg为提示信息、data为返回的数据
    */
    public int code;
    private String msg;
    private T data;

    public RetResult<T> setCode(RetCode retCode){
        this.code = retCode.code;
        return this;
    }

    public RetResult<T> setCode(int code){
        this.code = code;
        return this;
    }

    public int getCode(){
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public RetResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public RetResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
