package org.payno.mock.server;

import lombok.Data;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockConfigurer.NoOpConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContextHandler.NoOp;
import org.payno.mock.server.core.configurer.StandardMockConfigurer;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.payno.mock.server.core.supplier.base.DefaultStringSupplier;

public class DefaultMockTest {

    @Data
    static class Person{
        String name;
        String gender;
    }

    public static void main(String[] args) {
        SpringMvcHttpProtocolContext springMvc = new SpringMvcHttpProtocolContext();
        MockConfigurer<SpringMvcHttpProtocolContext> noOp = new NoOpConfigurer<>();
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(springMvc,noOp,new NoOp<>());
        MockProxy<Person, SpringMvcHttpProtocolContext> mockProxy = mock.create(Person.class);
        Person person = mockProxy.get();
        System.out.println(person);

        MockConfigurer<SpringMvcHttpProtocolContext> standardConfigurer = new StandardMockConfigurer<>();
        Mock<SpringMvcHttpProtocolContext> mock2 = new Default<>(springMvc,standardConfigurer,new NoOp<>());
        standardConfigurer.registerFieldSupplier(new DefaultStringSupplier<>());
        person.setGender("male");
        MockProxy<Person, SpringMvcHttpProtocolContext> mockProxy1 = mock2.create(person);
        System.out.println(mockProxy1.get());
    }
}
