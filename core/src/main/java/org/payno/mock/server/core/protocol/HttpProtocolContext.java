package org.payno.mock.server.core.protocol;

import org.payno.mock.server.core.ProtocolContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 抽象的http协议上下文
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public interface HttpProtocolContext<P extends ProtocolContext<P>>
        extends ProtocolContext<P> {
    /**
     * 得到cur请求
     *
     * @return {@link HttpServletRequest}
     */
    HttpServletRequest getCurRequest();

    /**
     * 得到cur响应
     *
     * @return {@link HttpServletResponse}
     */
    HttpServletResponse getCurResponse();
}
