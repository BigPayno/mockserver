package org.payno.mock.server.support.aop;

import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.ProtocolContext;
import org.springframework.aop.framework.autoproxy.AbstractBeanFactoryAwareAdvisingPostProcessor;
import org.springframework.beans.factory.BeanFactory;

public class NeedMockPostProcessor<P extends ProtocolContext<P>> extends AbstractBeanFactoryAwareAdvisingPostProcessor {

    public NeedMockPostProcessor(Mock<P> mock) {
        this.mock = mock;
    }

    Mock<P> mock;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.advisor = new NeedMockAdvisor(mock);
    }
}
