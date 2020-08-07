package org.payno.mock.server.core.supplier.base;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

import java.util.Random;

public class RandomLongSupplier <P extends ProtocolContext<P>> implements FieldSupplier<Long,P> {
    @Override
    public Class<?> supportField() {
        return long.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public Long supplier(P protocolContext, FieldAccessor fieldAccessor) {
        return new Random().nextLong();
    }

    @Override
    public int getOrder() {
        return 21;
    }
}
