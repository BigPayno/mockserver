package org.payno.mock.server.support;

import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.ValueSupplier;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Collection;

public class MockContextRunner implements ApplicationRunner, ApplicationContextAware, EnvironmentAware {

    ApplicationContext context;
    Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MockSupplierComponentScanner scanner = new MockSupplierComponentScanner(false,environment);
        AnnotationConfigApplicationContext context2 = (AnnotationConfigApplicationContext)context;
        //  注入所有的类型提供器
        scanner
                .findCandidateComponents("org.payno.mock.server.core.supplier")
                .forEach(beanDefinition -> context2.registerBeanDefinition(beanDefinition.getBeanClassName(),beanDefinition));
        Collection<ValueSupplier> suppliers=context.getBeansOfType(ValueSupplier.class).values();
        MockConfigurer mockConfigurer = context.getBean(MockConfigurer.class);
        suppliers.forEach(mockConfigurer::addValueSupplier);
    }
}
