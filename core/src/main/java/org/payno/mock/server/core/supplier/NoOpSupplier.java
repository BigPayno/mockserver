package org.payno.mock.server.core.supplier;

import org.payno.mock.server.core.FieldMetaData;
import org.payno.mock.server.core.ValueSupplier;

public class NoOpSupplier implements ValueSupplier {
    @Override
    public boolean supports(Class clazz, FieldMetaData fieldMetaData) {
        return true;
    }

    @Override
    public Object supplier() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
