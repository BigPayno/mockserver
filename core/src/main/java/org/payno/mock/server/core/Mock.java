package org.payno.mock.server.core;

import org.joor.Reflect;

import javax.annotation.Nullable;
import java.util.Optional;

public interface Mock<P extends ProtocolContext<P>> {
    /**
     * 创建对应类型的对象
     *
     * @param tClass t类
     * @param spy    间谍
     * @return {@link MockProxy<T, P>}
     */
    <T> MockProxy<T,P> create(Class<T> tClass,@Nullable T spy);

    <T> MockProxy<T,P> create(T spy);

    class Default<P extends ProtocolContext<P>> implements Mock<P>{

        P protocolContext;
        MockConfigurer<P> mockConfigurer;

        public Default(P protocolContext, MockConfigurer<P> mockConfigurer) {
            this.protocolContext = protocolContext;
            this.mockConfigurer = mockConfigurer;
        }

        @Override
        public <T> MockProxy<T, P> create(Class<T> tClass,T spy) {
            return new MockProxy.Default<T,P>(spy==null?Reflect.on(tClass).create().get():spy)
                    .inject(protocolContext)
                    .config(mockConfigurer);
        }

        @Override
        public <T> MockProxy<T, P> create(T spy) {
            return new MockProxy.Default<T,P>(spy)
                    .inject(protocolContext)
                    .config(mockConfigurer);
        }
    }
}
