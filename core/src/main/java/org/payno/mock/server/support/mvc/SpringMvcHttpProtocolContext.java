package org.payno.mock.server.support.mvc;

import org.payno.mock.server.core.protocol.HttpProtocolContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * spring mvc http协议上下文
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public class SpringMvcHttpProtocolContext implements HttpProtocolContext<SpringMvcHttpProtocolContext> {
    @Override
    public HttpServletRequest getCurRequest() {
        ServletRequestAttributes servletRequestAttributes=
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    @Override
    public HttpServletResponse getCurResponse() {
        ServletRequestAttributes servletRequestAttributes=
                (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    public String getURI(){
        return getCurRequest().getRequestURI();
    }
}
