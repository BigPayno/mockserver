package org.payno.mock.server.core.configurer;

import com.google.common.collect.Comparators;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.protocol.SpringMvcHttpProtocolContext;

import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 * spring mvc protobuf模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public class SpringMvcProtobufMockConfigurer extends AbstractProtobufMockConfigurer<SpringMvcHttpProtocolContext>{

    @Override
    Optional<FieldSupplier<?, SpringMvcHttpProtocolContext>> selectCandidate(Set<FieldSupplier<?, SpringMvcHttpProtocolContext>> candidates, SpringMvcHttpProtocolContext protocolContext, FieldAccessor fieldAccessor) {
        return candidates.stream()
                .filter(supplier->supplier.supports(protocolContext, fieldAccessor))
                .max(Comparator.comparing(FieldSupplier::getOrder));
    }
}
