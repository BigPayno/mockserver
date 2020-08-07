package org.payno.mock.server.core.configurer.filter;

public final class FieldFilters {
    public static final FieldFilter BASE_FIELD_FILTER = new BaseFieldFilter();
    public static final FieldFilter NULL_OBJECT_FILTER = new NullObjectFieldFilter();
    public static final FieldFilter PROTOBUF_FIELD_FILTER = new ProtobufFieldFilter();
}
