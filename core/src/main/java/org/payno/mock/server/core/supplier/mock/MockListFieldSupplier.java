package org.payno.mock.server.core.supplier.mock;

import com.google.common.collect.Lists;
import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.ProtocolContext;
import org.springframework.core.ResolvableType;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockListFieldSupplier  <P extends ProtocolContext<P>> implements FieldSupplier<List<?>,P> {

    Mock<P> mockProxy;

    public MockListFieldSupplier(Mock<P> mockProxy) {
        super();
        this.mockProxy = mockProxy;
    }

    @Override
    public Class<?> supportField() {
        return List.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public List<?> supplier(P protocolContext, FieldAccessor fieldAccessor) {
        List<?> list = fieldAccessor.fieldReflect().get();
        if (!CollectionUtils.isEmpty(list)){
            return list.stream().map(item->
                    mockProxy.create(item).get()
            ).collect(Collectors.toList());
        }else{
            Field field = null;
            try{
                field = fieldAccessor.classType().getDeclaredField(fieldAccessor.fieldName());
            }catch (Exception e){
                e.printStackTrace();
            }
            if(field!=null){
                Class<?> itemType = ResolvableType.forField(field)
                        .getGeneric(0).getRawClass();
                return IntStream.range(0,10)
                        .mapToObj(index->mockProxy.create(itemType).get())
                        .collect(Collectors.toList());
            }
            return Collections.emptyList();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
