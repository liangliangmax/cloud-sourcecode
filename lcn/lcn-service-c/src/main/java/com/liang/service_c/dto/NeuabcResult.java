package com.liang.service_c.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public class NeuabcResult implements Serializable {

    private static String SUCCESS ="success";

    private static String ERROR = "error";

    private Integer code;

    private String message;

    private Object data;

    public static ResponseEntity ok(String message,Object data){

        return ResponseEntity.ok(NeuabcResult.OK(message,data));
    }

    public static ResponseEntity ok(){

        return ResponseEntity.ok(NeuabcResult.OK());
    }

    public static ResponseEntity ok(String message){

        return ResponseEntity.ok(NeuabcResult.OK(message));
    }

    public static ResponseEntity ok(Object data){

        return ResponseEntity.ok(NeuabcResult.OK(data));
    }

    public static ResponseEntity error(ResultCode resultCode,Object data){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(NeuabcResult.ERROR(resultCode,data));
    }

    public static ResponseEntity error(ResultCode resultCode,String message){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(NeuabcResult.ERROR(resultCode,message));
    }

    public static ResponseEntity error(ResultCode resultCode,String message,Object data){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(NeuabcResult.ERROR(resultCode,message,data));
    }





    private static NeuabcResult ERROR(ResultCode resultCode,String message){
        return new NeuabcResult(resultCode,message,null);
    }

    private static NeuabcResult ERROR(ResultCode resultCode,Object data){
        return new NeuabcResult(resultCode,ERROR,data);
    }


    private static NeuabcResult ERROR(ResultCode resultCode,String message,Object data){
        return new NeuabcResult(resultCode,message,data);
    }



    private static NeuabcResult OK(){

        return new NeuabcResult(ResultCode.SUCCESS,SUCCESS,null);

    }

    private static NeuabcResult OK(String message){

        return new NeuabcResult(ResultCode.SUCCESS,message,null);

    }

    private static NeuabcResult OK(Object data){

        return new NeuabcResult(ResultCode.SUCCESS,SUCCESS,data);
    }


    private static NeuabcResult OK(String message,Object data){

        return new NeuabcResult(ResultCode.SUCCESS,message,data);
    }


    public NeuabcResult(ResultCode resultCode, String message, Object data) {
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
