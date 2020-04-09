package com.liang.test;

import lombok.Getter;

@Getter
public class RestException extends RuntimeException {

    private int errorCode;

    public RestException(ResultCode resultCode) {
        super(resultCode.message());
        this.errorCode = resultCode.code();
    }

    public RestException(ResultCode resultCode, String message) {
        super(message);
        this.errorCode = resultCode.code();
    }

    public RestException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
