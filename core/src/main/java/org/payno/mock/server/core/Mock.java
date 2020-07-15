package org.payno.mock.server.core;

import com.google.common.collect.MapMaker;
import org.joor.Reflect;

import java.util.concurrent.ConcurrentMap;

public interface Mock {
    <T> boolean support(Class<T> tClass);
    <T> MockProxy<T> create(Class<T> tClass);

    final class Default implements Mock{

        ConcurrentMap<Class,Boolean> support = new MapMaker().concurrencyLevel(2)
                .makeMap();

        @Override
        public <T> boolean support(Class<T> tClass) {
            try{
                Boolean exist = support.get(tClass);
                if(exist==null){
                    Reflect.on(tClass).create();
                }else{
                    return exist.booleanValue();
                }
            }catch (Exception e){
                support.put(tClass,Boolean.FALSE);
            }
            return false;
        }

        @Override
        public <T> MockProxy<T> create(Class<T> tClass){
            return MockProxy.Default.create(
                    Reflect.on(tClass).create().get());
        }
    }
}
