package com.payno.easyhttp.core;

public class DefaultExecuteResult<Res> implements ExecuteResult<Res>{

    Res res;

    @Override
    public void setRes(Res res) {
        this.res = res;
    }

    @Override
    public Res res() {
        return res;
    }

    @Override
    public <UnsafeRes> UnsafeRes res(Class<UnsafeRes> resType) {
        return (UnsafeRes) res;
    }
}
