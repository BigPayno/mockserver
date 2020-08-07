package org.payno.mock.server.support.aop;

import org.aopalliance.aop.Advice;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.ProtocolContext;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class NeedMockAdvisor<P extends ProtocolContext<P>> extends AbstractPointcutAdvisor implements BeanFactoryAware {


    public NeedMockAdvisor(Mock<P> mock) {
        this.mock = mock;
        this.advice = new NeedMockInterceptor(mock);
        this.pointcut = new AnnotationMatchingPointcut(null,NeedMock.class,true);
    }

    Mock<P> mock;

    private volatile Advice advice;

    private volatile Pointcut pointcut;

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (this.advice instanceof BeanFactoryAware) {
            ((BeanFactoryAware)this.advice).setBeanFactory(beanFactory);
        }
    }
}
