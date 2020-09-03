package com.payno.easyhttp.core;

import org.springframework.web.bind.annotation.RequestMethod;

public enum HttpExecuteStrategy implements ExecuteStrategy{
    GET{
        @Override
        public <Req, Res> ExecuteResult<Res> execute(ExecuteContext<Req, Res> context) {
            try{
                Res response = context.restTemplate()
                        .getForObject(context.url(),context.responseType());
                context.result().setRes(response);
                return context.result();
            }catch (Exception e){
                context.exceptionHandler().handle(e);
                return new ErrorExecuteResult<>();
            }
        }
    },
    POST{
        @Override
        public <Req, Res> ExecuteResult<Res> execute(ExecuteContext<Req, Res> context) {
            try{
                Res response = context.restTemplate()
                        .postForObject(context.url(),context.request(),context.responseType());
                context.result().setRes(response);
                return context.result();
            }catch (Exception e){
                context.exceptionHandler().handle(e);
                return new ErrorExecuteResult<>();
            }
        }
    };

    public static ExecuteStrategy valueOf(RequestMethod httpMethod){
        if (httpMethod == RequestMethod.GET) {
            return GET;
        }else{
            return POST;
        }
    }
}
