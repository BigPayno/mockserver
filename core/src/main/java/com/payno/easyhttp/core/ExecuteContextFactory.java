package com.payno.easyhttp.core;

import java.util.function.Function;

public interface ExecuteContextFactory<F extends ExecuteContextFactory<F>> {
    /**
     * 构建
     *
     * @param process 过程
     * @return {@link ExecuteContext<Req, Res>}
     */
    default <Req,Res> ExecuteContext<Req,Res> build(Function<F,ExecuteContext<Req,Res>> process){
        return process.apply(getFactory());
    }

    F getFactory();
}
