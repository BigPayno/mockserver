package org.payno.mock.server.core.configurer.filter;

import org.payno.mock.server.core.FieldAccessor;

public class ProtobufFieldFilter implements FieldFilter{

    static final String PROTO_FIELD_END_FIX = "_";

    @Override
    public boolean test(FieldAccessor fieldAccessor) {
        assert fieldAccessor != null;
        return fieldAccessor.fieldName().endsWith(PROTO_FIELD_END_FIX);
    }
}
