package org.payno.mock.server.core;

import org.payno.mock.server.core.exception.MockException;

/**
 * 对协议上下文进行处理
 *
 * @author zhaolei22
 * @date 2020/08/06
 */
public interface ProtocolContextHandler<P extends ProtocolContext<P>> {

    /**
     * 处理
     *
     * @param p p
     * @throws MockException 模拟异常
     */
    void handle(P p) throws MockException;

    class NoOp<P extends ProtocolContext<P>> implements ProtocolContextHandler<P>{

        @Override
        public void handle(P p) throws MockException {

        }
    }
}
