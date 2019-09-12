package com.liang.dl;


import java.io.Serializable;

public class NeuabcRestApiResult implements Serializable {

    private static String SUCCESS ="success";

    private static String ERROR = "error";

    private Integer code;

    private String message;

    private Object data;


    public static NeuabcRestApiResult OK(){

        return new NeuabcRestApiResult(ResultCode.SUCCESS,SUCCESS,null);

    }

    public static NeuabcRestApiResult OK(String message){

        return new NeuabcRestApiResult(ResultCode.SUCCESS,message,null);

    }

    public static NeuabcRestApiResult OK(Object data){

        return new NeuabcRestApiResult(ResultCode.SUCCESS,SUCCESS,data);
    }


    public static NeuabcRestApiResult OK(String message,Object data){

        return new NeuabcRestApiResult(ResultCode.SUCCESS,message,data);
    }


    public static NeuabcRestApiResult ERROR(ResultCode resultCode,String message){
        return new NeuabcRestApiResult(resultCode,message,null);
    }

    public static NeuabcRestApiResult ERROR(ResultCode resultCode,Object data){
        return new NeuabcRestApiResult(resultCode,ERROR,data);
    }


    public static NeuabcRestApiResult ERROR(ResultCode resultCode,String message,Object data){
        return new NeuabcRestApiResult(resultCode,message,data);
    }


    public NeuabcRestApiResult(){

    }



    public NeuabcRestApiResult(ResultCode resultCode, String message, Object data) {
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

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
