package com.payno.easyhttp.core;

import org.springframework.web.client.RestTemplate;

public interface ExecuteContext<Req,Res> {
    RestTemplate restTemplate();
    String url();
    Req request();
    Class<Res> responseType();
    ExecuteStrategy executeStrategy();
    ExecuteExceptionHandler exceptionHandler();
    ExecuteResult<Res> result();
    default ExecuteResult<Res> execute(){
        return executeStrategy().execute(this);
    }
}
