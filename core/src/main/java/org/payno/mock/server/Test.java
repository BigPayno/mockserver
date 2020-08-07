package org.payno.mock.server;

import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.support.mvc.RestHttpProtocolContextHandler;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.springframework.web.client.RestTemplate;

public class Test {

    public static void main(String[] args) throws Exception{
        ProtocolContextHandler<SpringMvcHttpProtocolContext> handler =
                new RestHttpProtocolContextHandler("null",new RestTemplate());
        System.out.println(Object.class.isAssignableFrom(int.class));
    }
}
