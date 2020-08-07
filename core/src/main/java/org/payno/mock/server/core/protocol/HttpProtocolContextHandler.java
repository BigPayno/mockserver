package org.payno.mock.server.core.protocol;

import org.payno.mock.server.core.ProtocolContextHandler;

/**
 * http协议上下文处理程序
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public interface HttpProtocolContextHandler<P extends HttpProtocolContext<P>> extends ProtocolContextHandler<P> {
    /**
     * 发送当前的url得到http协议相关处理信息
     *      如cookie
     *      如header
     *      如重定向
     *
     * @param uri uri 当前mock的url
     * @return {@link HttpProtocolDetails}
     */
    HttpProtocolDetails getHttpProtocolDetails(String uri);
}
