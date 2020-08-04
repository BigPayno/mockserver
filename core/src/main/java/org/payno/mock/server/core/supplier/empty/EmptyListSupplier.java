package org.payno.mock.server.core.supplier.empty;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

import java.util.Collections;
import java.util.List;

public class EmptyListSupplier <P extends ProtocolContext<P>> implements FieldSupplier<List,P> {
    @Override
    public Class<?> supportField() {
        return List.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public List supplier(P protocolContext, FieldAccessor fieldAccessor) {
        return Collections.emptyList();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
