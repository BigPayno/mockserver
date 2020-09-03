package com.payno.easyhttp.core;

public class ErrorExecuteResult<Res> implements ExecuteResult<Res>{
    @Override
    public void setRes(Res res) {

    }
    @Override
    public Res res() {
        throw new IllegalStateException("execute error!");
    }

    @Override
    public <UnsafeRes> UnsafeRes res(Class<UnsafeRes> resType) {
        throw new IllegalStateException("execute error!");
    }
}
