package com.payno.easyhttp.core;

public interface ExecuteExceptionHandler {
    void handle(Exception e);
    class NoOp implements ExecuteExceptionHandler{
        @Override
        public void handle(Exception e) {

        }
    }
}
