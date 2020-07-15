package org.payno.mock.server.core;

public interface ValueSupplier<T> extends Comparable<ValueSupplier>{
    boolean supports(Class<?> clazz, FieldMetaData fieldMetaData);
    T supplier();
}
