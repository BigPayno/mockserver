package org.payno.mock.server.core.configurer;

import org.joor.Reflect;
import org.payno.mock.server.core.ValueSupplier;
import org.payno.mock.server.core.FieldMetaData;

import java.util.List;
import java.util.stream.Collectors;

public class ProtobufMockConfigurer extends AbstractMockConfigurer{

    static final String PROTO_FIELD_END_FIX = "_";

    @Override
    List<FieldMetaData> fieldMetaDataExtract(Object object) {
        return Reflect.on(object.getClass()).fields().entrySet().stream()
                //  protobuf 字段生成后最后以_为结尾
                .filter(entry->entry.getKey().endsWith(PROTO_FIELD_END_FIX))
                .map(entry->FieldMetaData.Default.of(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    ValueSupplier lookupSupplier(Object object, FieldMetaData fieldMetaData) {
        return null;
    }
}
