package com.payno.easyhttp;

public interface RuntimeControllerSource {
    Class<?> controllerType() throws ClassNotFoundException;
    String methodName();
}
