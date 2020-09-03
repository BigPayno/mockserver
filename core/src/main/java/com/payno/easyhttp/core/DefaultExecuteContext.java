package com.payno.easyhttp.core;

import org.springframework.web.client.RestTemplate;

public class DefaultExecuteContext<Req,Res> implements ExecuteContext<Req,Res>{
    RestTemplate restTemplate;
    String url;
    Req request;
    Class<Res> responseType;
    ExecuteStrategy executeStrategy;
    ExecuteExceptionHandler exceptionHandler;
    ExecuteResult<Res> result;

    public DefaultExecuteContext(RestTemplate restTemplate, String url, Req request, Class<Res> responseType, ExecuteStrategy executeStrategy, ExecuteExceptionHandler exceptionHandler, ExecuteResult<Res> result) {
        this.restTemplate = restTemplate;
        this.url = url;
        this.request = request;
        this.responseType = responseType;
        this.executeStrategy = executeStrategy;
        this.exceptionHandler = exceptionHandler;
        this.result = result;
    }

    @Override
    public RestTemplate restTemplate() {
        return restTemplate;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Req request() {
        return request;
    }

    @Override
    public Class<Res> responseType() {
        return responseType;
    }

    @Override
    public ExecuteStrategy executeStrategy() {
        return executeStrategy;
    }

    @Override
    public ExecuteExceptionHandler exceptionHandler() {
        return exceptionHandler;
    }

    @Override
    public ExecuteResult<Res> result() {
        return result;
    }
}
