package org.payno.mock.server.core.supplier;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

public class ProtobufStringSupplier<P extends ProtocolContext<P>> implements FieldSupplier<String,P> {
    @Override
    public Class<?> supportField() {
        return Object.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return fieldAccessor.fieldReflect().type()==Object.class;
    }

    @Override
    public String supplier(P protocolContext, FieldAccessor fieldAccessor) {
        return "String";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
