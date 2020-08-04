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
public interface HttpProtocolContext
        <REQ extends HttpServletRequest,RES extends HttpServletResponse,P extends HttpProtocolContext<REQ,RES,P>>
        extends ProtocolContext<P> {
    REQ getCurRequest();
    RES getCurResponse();
}
