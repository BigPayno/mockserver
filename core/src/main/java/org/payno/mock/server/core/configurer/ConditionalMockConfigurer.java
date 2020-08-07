package org.payno.mock.server.core.configurer;

import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;

public interface ConditionalMockConfigurer<P extends ProtocolContext<P>> extends MockConfigurer<P> {

    /**
     * 支持
     *
     * @param object 对象
     * @return boolean
     */
    boolean support(MockProxy<?, P> object);

}
