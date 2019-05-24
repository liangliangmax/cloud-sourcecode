package com.liang.api.entity;

import java.io.Serializable;

public class NeuabcRestApiResult<T> implements Serializable {

    private static String SUCCESS ="success";

    private static String ERROR = "error";

    private Integer code;

    private String message;

    private T data;


    public static <T> NeuabcRestApiResult<T> OK(){

        return new NeuabcRestApiResult<>(ResultCode.SUCCESS,SUCCESS,null);

    }

    public static <T> NeuabcRestApiResult<T> OK(String message){

        return new NeuabcRestApiResult<>(ResultCode.SUCCESS,message,null);

    }

    public static <T> NeuabcRestApiResult<T> OK(T data){

        return new NeuabcRestApiResult<>(ResultCode.SUCCESS,SUCCESS,data);
    }


    public static <T> NeuabcRestApiResult<T> OK(String message,T data){

        return new NeuabcRestApiResult<>(ResultCode.SUCCESS,message,data);
    }


    public static <T> NeuabcRestApiResult<T> ERROR(ResultCode resultCode,String message){
        return new NeuabcRestApiResult<>(resultCode,message,null);
    }

    public static <T> NeuabcRestApiResult<T> ERROR(ResultCode resultCode,T data){
        return new NeuabcRestApiResult<>(resultCode,ERROR,data);
    }


    public static <T> NeuabcRestApiResult<T> ERROR(ResultCode resultCode,String message,T data){
        return new NeuabcRestApiResult<>(resultCode,message,data);
    }


    public NeuabcRestApiResult(){

    }



    public NeuabcRestApiResult(ResultCode resultCode, String message, T data) {
        this.code = resultCode.code();
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
