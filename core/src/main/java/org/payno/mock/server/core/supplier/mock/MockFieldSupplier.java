package org.payno.mock.server.core.supplier.mock;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.ProtocolContext;

public class MockFieldSupplier<P extends ProtocolContext<P>> implements FieldSupplier<Object,P> {

    Mock<P> mockProxy;

    public MockFieldSupplier(Mock<P> mockProxy) {
        super();
        this.mockProxy = mockProxy;
    }

    @Override
    public Class<?> supportField() {
        return Object.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public Object supplier(P protocolContext, FieldAccessor fieldAccessor) {
        Object spy = fieldAccessor.fieldReflect().get();
        if(null==spy){
            return mockProxy.create(fieldAccessor.fieldReflect().type()).get();
        }else{
            return mockProxy.create((Object) fieldAccessor.fieldReflect().get()).get();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
