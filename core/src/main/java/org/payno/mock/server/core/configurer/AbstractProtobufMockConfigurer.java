package org.payno.mock.server.core.configurer;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import org.joor.Reflect;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContext;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * protobuf模拟配置
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public abstract class AbstractProtobufMockConfigurer<P extends ProtocolContext<P>> extends AbstractMockConfigurer<P>{

    static final String PROTO_FIELD_END_FIX = "_";
    static final Set<String> STATUS_ENUMS = ImmutableSet
            .of("ENUM","STATUS","STATE");
    static final Set<Class<?>> BASE_TYPES = ImmutableSet.of(
      int.class,long.class,double.class,float.class,byte.class
    );

    /**
     * protobuf 字段生成后最后以_为结尾
     *
     * @param proxy 代理
     * @return {@link List<FieldAccessor>}
     */
    @Override
    List<FieldAccessor> fieldExtract(MockProxy<?, P> proxy) {
        return Reflect.on(proxy.get()).fields().entrySet().stream()
                .filter(entry->entry.getKey().endsWith(PROTO_FIELD_END_FIX))
                /*.filter(entry-> {
                    Class<?> type = entry.getValue().type();
                    String name = entry.getKey().toUpperCase();
                    Object obj = entry.getValue().get();
                    if(!type.isAssignableFrom(Number.class)||!type.isAssignableFrom(String.class)){
                        return obj==null;
                    }else if(type.isAssignableFrom(String.class)){
                        return Strings.isNullOrEmpty((String)obj);
                    }else if(BASE_TYPES.contains(type)){
                        for(String item:STATUS_ENUMS){
                            if(name.contains(item)){
                                return false;
                            }
                        }
                    }
                    return true;
                })*/
                .map(entry-> FieldAccessor.Default.of(entry.getKey(),entry.getValue(),proxy.get().getClass()))
                .collect(Collectors.toList());
    }
}
