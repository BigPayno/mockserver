package org.payno.mock.server.core.configurer;

import com.google.protobuf.GeneratedMessage;
import org.joor.Reflect;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;
import org.payno.mock.server.core.configurer.filter.FieldFilters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * protobuf模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public class ProtobufMockConfigurer<P extends ProtocolContext<P>> extends AbstractMockConfigurer<P> implements ConditionalMockConfigurer<P>{

    /**
     * protobuf 字段生成后最后以_为结尾
     *
     * @param proxy 代理
     * @return {@link List<FieldAccessor>}
     */
    @Override
    List<FieldAccessor> fieldExtract(MockProxy<?, P> proxy) {
        return Reflect.on(proxy.get()).fields().entrySet().stream()
                .map(entry-> FieldAccessor.Default.of(entry.getKey(),entry.getValue(),proxy.get().getClass()))
                .filter(FieldFilters.PROTOBUF_FIELD_FILTER
                        .and(FieldFilters.BASE_FIELD_FILTER.or(FieldFilters.NULL_OBJECT_FILTER)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean support(MockProxy<?, P> object) {
        return GeneratedMessage.class.isAssignableFrom(object.get().getClass());
    }
}
