package org.payno.mock.server.core.supplier.base;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

public class DefaultStringSupplier<P extends ProtocolContext<P>> implements FieldSupplier<String,P> {

    @Override
    public Class<?> supportField() {
        return String.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public String supplier(P protocolContext, FieldAccessor fieldAccessor) {
        return "default mock string value";
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
