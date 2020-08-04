package org.payno.mock.server.core;

/**
 * 为Object装配属性
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public interface MockConfigurer<P extends ProtocolContext<P>> {


    /**
     * 配置代理对象的属性
     *
     * @param object 对象
     */
    void config(MockProxy<?,P> object);

    /**
     * 注册属性的提供者
     *
     * @param supplier 供应商
     */
    void registerFieldSupplier(FieldSupplier<?,P> supplier);

    /**
     * 没有op配置
     *
     * @author zhaolei22
     * @date 2020/08/04
     */
    class NoOpConfigurer<P extends ProtocolContext<P>> implements MockConfigurer<P>{
        @Override
        public void config(MockProxy<?, P> object) {
            //do nothing!
        }

        @Override
        public void registerFieldSupplier(FieldSupplier<?,P> supplier) {
            //do nothing!
        }
    }
}
