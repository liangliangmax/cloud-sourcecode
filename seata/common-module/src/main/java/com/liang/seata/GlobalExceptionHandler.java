package com.liang.seata;


import com.liang.seata.dto.RestApiResult;
import com.liang.seata.dto.RestException;
import com.liang.seata.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个类不知道什么情况下会起作用
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_MESSAGE = "系统内部错误，请联系管理员！";

    /**
     * 通用异常处理
     */
    @ExceptionHandler(RestException.class)
    public RestApiResult handleRestException(RestException e) {
        log.error(e.getMessage(), e);
        return RestApiResult.ERROR(e.getErrorCode(),e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public RestApiResult handleRuntimeRestException(RestException e) {
        log.error(e.getMessage(), e);
        return RestApiResult.ERROR(e.getErrorCode(),e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestApiResult handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return RestApiResult.ERROR(ResultCode.REQUEST_METHOD_NOT_SUPPORT,"不支持' " + e.getMethod() + "'请求");
    }

    /**
     * DispatcherServlet没找到 handler异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public RestApiResult handlerNoFoundException(Exception e) {
        log.error(e.getMessage(), e);
        return RestApiResult.ERROR(404, "路径不存在，请检查路径是否正确");
    }


    @ExceptionHandler(value = Exception.class)
    public RestApiResult errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        log.error(e.getMessage(), e);
        return RestApiResult.ERROR(ResultCode.ERROR, e.getMessage());

    }


}