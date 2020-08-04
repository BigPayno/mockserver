package org.payno.mock.server;

import lombok.Data;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockConfigurer.NoOpConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.protocol.SpringMvcHttpProtocolContext;

import java.util.Optional;

public class DefaultMockTest {

    @Data
    static class Person{
        String name;
        String gender;
    }

    public static void main(String[] args) {
        SpringMvcHttpProtocolContext springMvc = new SpringMvcHttpProtocolContext();
        MockConfigurer<SpringMvcHttpProtocolContext> noOp = new NoOpConfigurer<>();
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(springMvc,noOp);
        MockProxy<Person,SpringMvcHttpProtocolContext> mockProxy = mock.create(Person.class, null);
        Person person = mockProxy.get();
        System.out.println(person);
    }

}
