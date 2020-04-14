package com.liang.seata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public final class RestApiResult<T> implements Serializable {

    private static String SUCCESS ="正常";

    private static String ERROR = "错误";

    private Integer code;

    private String message;

    private T data;


    //判断返回是否成功
    public boolean isSuccess(){
        return this.code.compareTo(ResultCode.SUCCESS.code()) == 0;
    }

    public static RestApiResult OK(){

        return new RestApiResult(ResultCode.SUCCESS,SUCCESS,null);

    }

    public static RestApiResult OK(String message){

        return new RestApiResult(ResultCode.SUCCESS,message,null);

    }

    public static <T> RestApiResult<T> OK(T data){

        return new RestApiResult(ResultCode.SUCCESS,SUCCESS,data);
    }


    public static <T> RestApiResult<T> OK(String message, T data){

        return new RestApiResult(ResultCode.SUCCESS,message,data);
    }


    public static RestApiResult ERROR(ResultCode resultCode){
        return new RestApiResult(resultCode,resultCode.message(),null);
    }

    public static RestApiResult ERROR(ResultCode resultCode, String message){
        return new RestApiResult(resultCode,message,null);
    }

    public static <T> RestApiResult<T> ERROR(ResultCode resultCode, T data){
        return new RestApiResult(resultCode,resultCode.message(),data);
    }


    public static <T> RestApiResult<T> ERROR(ResultCode resultCode, String message, T data){
        return new RestApiResult(resultCode,message,data);
    }

    public static RestApiResult ERROR(Integer code, String message){
        return new RestApiResult(code,message,null);
    }

    public static <T> RestApiResult<T> ERROR(Integer code, String message,T data){
        return new RestApiResult(code,message,data);
    }

    public RestApiResult(ResultCode resultCode, String message, T data) {
        this.code = resultCode.code();
        this.message = message;
        this.data = data;
    }

    public RestApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
