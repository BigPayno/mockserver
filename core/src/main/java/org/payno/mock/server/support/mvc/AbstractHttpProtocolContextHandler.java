package org.payno.mock.server.support.mvc;

import com.google.common.base.Strings;
import org.payno.mock.server.core.MockExceptionTranslator;
import org.payno.mock.server.core.exception.MockException;
import org.payno.mock.server.core.exception.MockExceptionRunner;
import org.payno.mock.server.core.exception.MockExceptions;
import org.payno.mock.server.core.protocol.HttpProtocolContextHandler;
import org.payno.mock.server.core.protocol.HttpProtocolDetails;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * rpc spring http协议上下文处理程序
 *      http处理信息来自远程调用
 * @author zhaolei22
 * @date 2020/08/07
 */
public abstract class AbstractHttpProtocolContextHandler implements HttpProtocolContextHandler<SpringMvcHttpProtocolContext> {

    @Override
    public void handle(SpringMvcHttpProtocolContext springMvcHttpProtocolContext) throws MockException {
        HttpProtocolDetails details = getHttpProtocolDetails(springMvcHttpProtocolContext.getURI());
        new MockExceptionRunner<HttpServletResponse>(){
            @Override
            public MockExceptionTranslator exceptionTranslator() {
                return MockExceptions.DEAFAULT;
            }

            @Override
            public HttpServletResponse get() {
                return springMvcHttpProtocolContext.getCurResponse();
            }

            @Override
            public void handle(HttpServletResponse httpServletResponse) throws Exception {
                if(Objects.nonNull(details.getSleepTime())){
                    Thread.sleep(details.getSleepTime());
                }
                if(Objects.nonNull(details.getResponseStatus())){
                    httpServletResponse.setStatus(details.getResponseStatus());
                }
                if(!Strings.isNullOrEmpty(details.getRedirectUri())){
                    httpServletResponse.sendRedirect(details.getRedirectUri());
                }
                if(details.getCookies()!=null){
                    details.getCookies().forEach((k,v)->{
                        httpServletResponse.addCookie(new Cookie(k,v));
                    });
                }
                if(details.getHeaders()!=null){
                    details.getHeaders().forEach(httpServletResponse::setHeader);
                }
            }
        }.exec();
    }
}
