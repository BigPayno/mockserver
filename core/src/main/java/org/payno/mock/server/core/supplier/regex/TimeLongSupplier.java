package org.payno.mock.server.core.supplier.regex;

import org.payno.mock.server.core.FieldAccessor;
import org.payno.mock.server.core.FieldSupplier;
import org.payno.mock.server.core.ProtocolContext;

public class TimeLongSupplier <P extends ProtocolContext<P>> implements FieldSupplier<Long,P> {

    static String START_FLAG = "create";
    static String END_FLAG = "end";
    static String TIME_FLAG = "time";

    @Override
    public Class<?> supportField() {
        return long.class;
    }

    @Override
    public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
        return fieldAccessor.fieldName().toLowerCase().contains(TIME_FLAG);
    }

    @Override
    public Long supplier(P protocolContext, FieldAccessor fieldAccessor) {
        if(fieldAccessor.fieldName().toLowerCase().contains(START_FLAG)){
            return System.currentTimeMillis()-10000L;
        }
        if(fieldAccessor.fieldName().toLowerCase().contains(END_FLAG)){
            return System.currentTimeMillis();
        }
        return System.currentTimeMillis();
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
