package com.payno.easyhttp.core;

public interface ExecuteStrategy {
    <Req,Res> ExecuteResult<Res> execute(ExecuteContext<Req,Res> context);
}
