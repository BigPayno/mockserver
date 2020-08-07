package org.payno.mock.server.support.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.ProtocolContext;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.Method;

public class NeedMockInterceptor<P extends ProtocolContext<P>> implements MethodInterceptor {

    Mock<P> mock;

    public NeedMockInterceptor(Mock<P> mock) {
        this.mock = mock;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        NeedMock needMock = AnnotatedElementUtils.findMergedAnnotation(method,NeedMock.class);
        if(needMock!=null){
            if(needMock.mock()){
                Class<?> returnVal = method.getReturnType();
                return mock.create(returnVal).get();
            }
            if(needMock.spy()){
                return mock.create((Object)methodInvocation.proceed()).get();
            }
        }
        return methodInvocation.proceed();
    }
}
