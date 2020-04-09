package com.liang.test;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RestApiResult implements Serializable {

    private static String SUCCESS ="正常";

    private static String ERROR = "错误";

    private Integer code;

    private String message;

    private Object data;


    //判断返回是否成功
    public boolean isSeccuess(){
        return this.code.compareTo(ResultCode.SUCCESS.code()) == 0;
    }

    public static RestApiResult OK(){

        return new RestApiResult(ResultCode.SUCCESS,SUCCESS,null);

    }

    public static RestApiResult OK(String message){

        return new RestApiResult(ResultCode.SUCCESS,message,null);

    }

    public static RestApiResult OK(Object data){

        return new RestApiResult(ResultCode.SUCCESS,SUCCESS,data);
    }


    public static RestApiResult OK(String message, Object data){

        return new RestApiResult(ResultCode.SUCCESS,message,data);
    }


    public static RestApiResult ERROR(ResultCode resultCode){
        return new RestApiResult(resultCode,resultCode.message(),null);
    }

    public static RestApiResult ERROR(ResultCode resultCode, String message){
        return new RestApiResult(resultCode,message,null);
    }

    public static RestApiResult ERROR(ResultCode resultCode, Object data){
        return new RestApiResult(resultCode,ERROR,data);
    }


    public static RestApiResult ERROR(ResultCode resultCode, String message, Object data){
        return new RestApiResult(resultCode,message,data);
    }

    public static RestApiResult ERROR(int code, String message){
        return new RestApiResult(code,message,null);
    }

    public RestApiResult(ResultCode resultCode, String message, Object data) {
        this.code = resultCode.code();
        this.message = message;
        this.data = data;
    }

    public RestApiResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
