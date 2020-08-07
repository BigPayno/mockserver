package org.payno.mock.server.core.supplier.base;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

import java.util.Random;

public class RandomIntSupplier<P extends ProtocolContext<P>> implements FieldSupplier<Integer,P> {

    @Override
    public Class<?> supportField() {
        return int.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return true;
    }

    @Override
    public Integer supplier(P protocolContext, FieldAccessor fieldAccessor) {
        return new Random().nextInt();
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
