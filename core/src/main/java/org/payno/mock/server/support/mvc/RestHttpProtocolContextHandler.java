package org.payno.mock.server.support.mvc;

import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.core.protocol.HttpProtocolDetails;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Nonnull;

/**
 * http spring http协议上下文处理程序
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public class RestHttpProtocolContextHandler extends AbstractHttpProtocolContextHandler implements ProtocolContextHandler<SpringMvcHttpProtocolContext> {

    public RestHttpProtocolContextHandler(@Nonnull String url, @Nonnull RestTemplate restTemplate) {
        super();
        this.url = url;
        this.restTemplate = restTemplate;
    }

    String url;
    RestTemplate restTemplate;

    @Override
    public HttpProtocolDetails getHttpProtocolDetails(String uri) {
        return restTemplate.postForEntity(url,uri,HttpProtocolDetails.class).getBody();
    }
}
