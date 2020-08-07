package org.payno.mock.server.core.configurer;

import org.joor.Reflect;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;
import org.payno.mock.server.core.configurer.filter.FieldFilters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 标准的模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/06
 */
public class StandardMockConfigurer<P extends ProtocolContext<P>> extends AbstractMockConfigurer<P> implements ConditionalMockConfigurer<P>{

    @Override
    List<FieldAccessor> fieldExtract(MockProxy<?, P> proxy) {
        return Reflect.on(proxy.get()).fields().entrySet().stream()
                .map(entry-> FieldAccessor.Default.of(entry.getKey(),entry.getValue(),proxy.get().getClass()))
                .filter(FieldFilters.BASE_FIELD_FILTER.or(FieldFilters.NULL_OBJECT_FILTER))
                .collect(Collectors.toList());
    }

    @Override
    public boolean support(MockProxy<?, P> object) {
        return true;
    }
}
