package com.payno.easyhttp;

import com.payno.easyhttp.core.ExecuteContextFactory;
import com.payno.easyhttp.core.ExecuteExceptionHandler.NoOp;
import org.springframework.web.client.RestTemplate;

public class Main {
    public static void main(String[] args) {
        ExecuteContextFactory<RuntimeControllerExecuteContextFactory> contextFactory
                = new RuntimeControllerExecuteContextFactory(new RestTemplate(),new NoOp());
        String res = contextFactory
                .build(factory->factory.create("urlPrefix",Main.class,"main",null))
                .execute()
                .res(String.class);
    }
}
