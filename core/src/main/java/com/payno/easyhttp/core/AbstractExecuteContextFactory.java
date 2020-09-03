package com.payno.easyhttp.core;

import org.springframework.web.client.RestTemplate;

public abstract class AbstractExecuteContextFactory<F extends AbstractExecuteContextFactory<F>> implements ExecuteContextFactory<F>{

    protected RestTemplate restTemplate;
    protected ExecuteExceptionHandler exceptionHandler;

    protected RestTemplate getRestTemplate() {
        return restTemplate;
    }

    protected ExecuteExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public AbstractExecuteContextFactory(RestTemplate restTemplate, ExecuteExceptionHandler exceptionHandler) {
        this.restTemplate = restTemplate;
        this.exceptionHandler = exceptionHandler;
    }
}
