package org.payno.mock.server.support.autoconfigure;

import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.core.ProtocolContextHandler.NoOp;
import org.payno.mock.server.core.configurer.StandardMockConfigurer;
import org.payno.mock.server.core.supplier.base.DefaultStringSupplier;
import org.payno.mock.server.support.aop.NeedMockPostProcessor;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalMockConfigurer {
    @Bean
    Mock<SpringMvcHttpProtocolContext> mock(){
        SpringMvcHttpProtocolContext context = new SpringMvcHttpProtocolContext();
        ProtocolContextHandler<SpringMvcHttpProtocolContext> handler =
                new NoOp<>();
        MockConfigurer<SpringMvcHttpProtocolContext> mockConfigurer = new StandardMockConfigurer<>();
        mockConfigurer.registerFieldSupplier(new DefaultStringSupplier<>());
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(context,mockConfigurer,handler);
        return mock;
    }

    @Bean
    NeedMockPostProcessor<SpringMvcHttpProtocolContext> needMockPostProcessor(){
        return new NeedMockPostProcessor<>(mock());
    }
}
