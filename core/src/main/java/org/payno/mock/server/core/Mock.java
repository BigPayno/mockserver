package org.payno.mock.server.core;

import org.joor.Reflect;
import org.payno.mock.server.core.exception.MockException;

public interface Mock<P extends ProtocolContext<P>> {
    /**
     * 创建
     *
     * @param tClass t类
     * @return {@link MockProxy<T, P>}
     * @throws MockException 模拟异常
     */
    <T> MockProxy<T,P> create(Class<T> tClass) throws MockException;

    /**
     * 创建
     *
     * @param spy 间谍
     * @return {@link MockProxy<T, P>}
     * @throws MockException 模拟异常
     */
    <T> MockProxy<T,P> create(T spy) throws MockException;

    class Default<P extends ProtocolContext<P>> implements Mock<P>{

        P protocolContext;
        MockConfigurer<P> mockConfigurer;
        ProtocolContextHandler<P> protocolContextHandler;


        public Default(P protocolContext, MockConfigurer<P> mockConfigurer,ProtocolContextHandler<P> protocolContextHandler) {
            this.protocolContext = protocolContext;
            this.mockConfigurer = mockConfigurer;
            this.protocolContextHandler = protocolContextHandler;
        }

        @Override
        public <T> MockProxy<T, P> create(Class<T> tClass) throws MockException{
            return new MockProxy.Default<T,P>(Reflect.on(tClass).create().get())
                    .inject(protocolContext)
                    .handleProtocolContext(protocolContextHandler)
                    .config(mockConfigurer);
        }

        @Override
        public <T> MockProxy<T, P> create(T spy) throws MockException{
            return new MockProxy.Default<T,P>(spy)
                    .inject(protocolContext)
                    .handleProtocolContext(protocolContextHandler)
                    .config(mockConfigurer);
        }
    }
}
