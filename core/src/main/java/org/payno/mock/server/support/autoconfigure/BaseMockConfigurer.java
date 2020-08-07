package org.payno.mock.server.support.autoconfigure;

import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.core.configurer.StandardMockConfigurer;
import org.payno.mock.server.core.supplier.base.DefaultStringSupplier;
import org.payno.mock.server.support.aop.NeedMockPostProcessor;
import org.payno.mock.server.support.mvc.RestHttpProtocolContextHandler;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BaseMockConfigurer implements EnvironmentAware {

    Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Bean
    Mock<SpringMvcHttpProtocolContext> mock(){
        SpringMvcHttpProtocolContext context = new SpringMvcHttpProtocolContext();
        String mockServer = environment.getProperty("mock.server.url");
        ProtocolContextHandler<SpringMvcHttpProtocolContext> handler =
                new RestHttpProtocolContextHandler(mockServer,new RestTemplate());
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
