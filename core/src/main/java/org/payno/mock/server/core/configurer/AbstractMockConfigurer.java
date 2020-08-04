package org.payno.mock.server.core.configurer;

import com.google.common.collect.Collections2;
import org.joor.Reflect;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.FieldSupplier.NoOpFieldSupplier;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 摘要模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public abstract class AbstractMockConfigurer<P extends ProtocolContext<P>> implements MockConfigurer<P> {

    protected ConcurrentHashMap<Class<?>, Set<FieldSupplier<?,P>>> fieldSupplierMap = new ConcurrentHashMap<>();

    FieldSupplier<?,P> NULL_SUPPLIER = new NoOpFieldSupplier<>();

    @Override
    public void config(MockProxy<?, P> proxy) {
        List<FieldAccessor> fieldAccessors = fieldExtract(proxy);
        fieldAccessors.forEach(fieldAccessor -> {
            FieldSupplier<?,P> fieldSupplier = findCandidate(proxy.getProtocolContext(),fieldAccessor);
            Reflect.on((Object)proxy.get())
                    .set(fieldAccessor.fieldName(),fieldSupplier.supplier(proxy.getProtocolContext(),fieldAccessor));
        });
    }

    @Override
    public void registerFieldSupplier(FieldSupplier<?,P> supplier) {
        Set<FieldSupplier<?,P>> supplierSet = fieldSupplierMap
                .getOrDefault(supplier.supportField(),new CopyOnWriteArraySet<>());
        supplierSet.add(supplier);
        fieldSupplierMap.put(supplier.supportField(),supplierSet);
    }

    FieldSupplier<?,P> findCandidate(P protocolContext, FieldAccessor fieldAccessor){
        Set<FieldSupplier<?,P>> candidates = fieldSupplierMap.get(fieldAccessor.fieldReflect().type());
        if(CollectionUtils.isEmpty(candidates)){
            candidates = fieldSupplierMap.get(Object.class);
            if(!CollectionUtils.isEmpty(candidates)){
                return selectCandidate(candidates,protocolContext,fieldAccessor).orElseGet(()->NULL_SUPPLIER);
            }
            return NULL_SUPPLIER;
        }else{
            return selectCandidate(candidates,protocolContext,fieldAccessor).orElseGet(()->NULL_SUPPLIER);
        }
    }

    /**
     * 从代理对象中提取所有需要的属性的访问器
     *
     * @param proxy 对象
     * @return {@link List<FieldAccessor>}
     */
    abstract List<FieldAccessor> fieldExtract(MockProxy<?, P> proxy);

    /**
     * 选择候选人
     *
     * @param fieldAccessor   字段访问器
     * @param protocolContext 协议内容
     * @param candidates      候选人
     * @return {@link FieldSupplier<?, P>}
     */
    abstract Optional<FieldSupplier<?,P>> selectCandidate(Set<FieldSupplier<?,P>> candidates, P protocolContext, FieldAccessor fieldAccessor);

}
