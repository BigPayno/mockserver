package org.payno.mock.server.core.configurer;

import org.joor.Reflect;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.ValueSupplier;
import org.payno.mock.server.core.FieldMetaData;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractMockConfigurer implements MockConfigurer {

    CopyOnWriteArrayList<ValueSupplier> valueSuppliers=new CopyOnWriteArrayList<>();

    @Override
    public void config(Object object) {
        List<FieldMetaData> fieldMetaDatas = fieldMetaDataExtract(object);
        fieldMetaDatas.forEach(fieldMetaData -> {
            ValueSupplier valueSupplier = lookupSupplier(object,fieldMetaData);
            Reflect.on(object.getClass())
                    .set(fieldMetaData.fieldName(),valueSupplier.supplier());
        });
    }

    @Override
    public void addValueSupplier(ValueSupplier supplier) {
        valueSuppliers.add(supplier);
    }

    abstract List<FieldMetaData> fieldMetaDataExtract(Object object);
    abstract ValueSupplier lookupSupplier(Object object,FieldMetaData fieldMetaData);

}
