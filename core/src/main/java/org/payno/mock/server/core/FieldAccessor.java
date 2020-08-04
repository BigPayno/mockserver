package org.payno.mock.server.core;

import org.joor.Reflect;

/**
 * 字段访问器
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public interface FieldAccessor {
    /**
     * 字段名
     *
     * @return {@link String}
     */
    String fieldName();

    /**
     * 字段类型
     *
     * @return {@link Class<?>}
     */
    Reflect fieldReflect();

    /**
     * 类类型
     *
     * @return {@link Class<?>}
     */
    Class<?> classType();

    class Default implements FieldAccessor {
        String fieldName;
        Reflect reflect;
        Class<?> classType;

        public static Default of(String fieldName,Reflect reflect,Class<?> classType){
            Default data = new Default();
            data.fieldName = fieldName;
            data.classType = classType;
            data.reflect = reflect;
            return data;
        }

        @Override
        public String fieldName() {
            return fieldName;
        }

        @Override
        public Reflect fieldReflect() {
            return reflect;
        }

        @Override
        public Class<?> classType() {
            return classType;
        }
    }
}
