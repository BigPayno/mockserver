package org.payno.mock.server.core.configurer;

import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;

import java.util.List;

/**
 * 连接模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public class ChainedMockConfigurer<P extends ProtocolContext<P>> implements MockConfigurer<P> {

    public ChainedMockConfigurer(List<ConditionalMockConfigurer<P>> configurers) {
        super();
        this.configurers = configurers;
    }

    List<ConditionalMockConfigurer<P>> configurers;

    @Override
    public void config(MockProxy<?, P> object) {
        for(ConditionalMockConfigurer<P> mockConfigurer : configurers){
            if(mockConfigurer.support(object)){
                mockConfigurer.config(object);
                break;
            }
        }
    }

    @Override
    public void registerFieldSupplier(FieldSupplier<?, P> supplier) {
        configurers.forEach(mockConfigurer->mockConfigurer.registerFieldSupplier(supplier));
    }
}
