package com.payno.easyhttp.core;

public interface ExecuteResult<Res> {
    void setRes(Res res);
    Res res();
    <UnsafeRes> UnsafeRes res(Class<UnsafeRes> resType);
}
