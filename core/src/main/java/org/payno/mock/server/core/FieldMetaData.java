package org.payno.mock.server.core;

import org.joor.Reflect;

public interface FieldMetaData {
    String fieldName();
    Reflect filedAccess();

    class Default implements FieldMetaData{
        String fieldName;
        Reflect reflect;

        public static Default of(String fieldName,Reflect reflect){
            Default data = new Default();
            data.fieldName = fieldName;
            data.reflect = reflect;
            return data;
        }

        @Override
        public String fieldName() {
            return null;
        }

        @Override
        public Reflect filedAccess() {
            return null;
        }
    }
}
