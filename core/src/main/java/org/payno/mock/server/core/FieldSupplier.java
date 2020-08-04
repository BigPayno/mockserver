package org.payno.mock.server.core;

public interface FieldSupplier<T,P extends ProtocolContext<P>>{
    Class<?> supportField();
    boolean supports(P protocolContext, FieldAccessor fieldAccessor);
    T supplier(P protocolContext, FieldAccessor fieldAccessor);
    int getOrder();

    /**
     * 没有op领域供应商
     *
     * @author zhaolei22
     * @date 2020/08/04
     */
    class NoOpFieldSupplier<T,P extends ProtocolContext<P>> implements FieldSupplier<T,P>{
        @Override
        public Class<?> supportField() {
            return Object.class;
        }

        @Override
        public boolean supports(P protocolContext, FieldAccessor fieldAccessor) {
            return true;
        }

        @Override
        public T supplier(P protocolContext, FieldAccessor fieldAccessor) {
            return null;
        }

        @Override
        public int getOrder() {
            return SupplierOrder.DEFAULT_NULL;
        }
    }

    class SupplierOrder {
        public static int DEFAULT_NULL = -1;
    }
}
