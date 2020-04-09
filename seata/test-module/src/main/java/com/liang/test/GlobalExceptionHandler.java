package com.liang.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 这个类不知道什么情况下会起作用
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String ERROR_MESSAGE = "系统内部错误，请联系管理员！";

    /**
     * 通用异常处理
     */
    @ExceptionHandler(RestException.class)
    public RestApiResult handleRestException(RestException e) {
        return RestApiResult.ERROR(e.getErrorCode(),e.getMessage());
    }

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RestApiResult handleException(HttpRequestMethodNotSupportedException e) {
        return RestApiResult.ERROR(ResultCode.REQUEST_METHOD_NOT_SUPPORT,"不支持' " + e.getMethod() + "'请求");
    }

    /**
     * DispatcherServlet没找到 handler异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public RestApiResult handlerNoFoundException(Exception e) {
        return RestApiResult.ERROR(404, "路径不存在，请检查路径是否正确");
    }


    @ExceptionHandler(value = Exception.class)
    public RestApiResult errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {

        logger.error(e.getMessage());
        return RestApiResult.ERROR(ResultCode.ERROR, e.getMessage());

    }


}