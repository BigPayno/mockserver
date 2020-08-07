package org.payno.mock.server;

import com.google.common.collect.Lists;
import com.qingqing.socialiosvc.proto.activity.SocialiosvcActivityProto;
import lombok.Data;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.core.ProtocolContextHandler.NoOp;
import org.payno.mock.server.core.configurer.ChainedMockConfigurer;
import org.payno.mock.server.core.configurer.ConditionalMockConfigurer;
import org.payno.mock.server.core.configurer.ProtobufMockConfigurer;
import org.payno.mock.server.core.configurer.StandardMockConfigurer;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.payno.mock.server.core.supplier.ProtobufStringSupplier;
import org.payno.mock.server.core.supplier.base.DefaultStringSupplier;
import org.payno.mock.server.core.supplier.base.RandomIntSupplier;
import org.payno.mock.server.core.supplier.base.RandomLongSupplier;
import org.payno.mock.server.core.supplier.empty.EmptyListSupplier;
import org.payno.mock.server.core.supplier.mock.MockFieldSupplier;
import org.payno.mock.server.core.supplier.mock.MockListFieldSupplier;
import org.payno.mock.server.core.supplier.regex.TimeLongSupplier;

public class ConditionalMockTest {

    @Data
    static class Person{
        String name;
        String gender;
    }

    public static void main(String[] args) {
        SpringMvcHttpProtocolContext springMvc = new SpringMvcHttpProtocolContext();
        ProtocolContextHandler<SpringMvcHttpProtocolContext> protocolContextHandler = new NoOp<>();
        ConditionalMockConfigurer<SpringMvcHttpProtocolContext> standardConfigurer = new StandardMockConfigurer<>();
        ConditionalMockConfigurer<SpringMvcHttpProtocolContext> protocolConfigurer = new ProtobufMockConfigurer<>();
        ChainedMockConfigurer<SpringMvcHttpProtocolContext> chainedConfigurer =
                new ChainedMockConfigurer<>(Lists.newArrayList(protocolConfigurer,standardConfigurer));
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(springMvc,chainedConfigurer,protocolContextHandler);
        chainedConfigurer.registerFieldSupplier(new DefaultStringSupplier<>());
        chainedConfigurer.registerFieldSupplier(new EmptyListSupplier<>());
        chainedConfigurer.registerFieldSupplier(new RandomIntSupplier<>());
        chainedConfigurer.registerFieldSupplier(new MockFieldSupplier<>(mock));
        chainedConfigurer.registerFieldSupplier(new MockListFieldSupplier<>(mock));
        chainedConfigurer.registerFieldSupplier(new TimeLongSupplier<>());
        chainedConfigurer.registerFieldSupplier(new ProtobufStringSupplier<>());
        chainedConfigurer.registerFieldSupplier(new RandomLongSupplier<>());
        Person person = new Person();
        person.setGender("male");
        MockProxy<Person, SpringMvcHttpProtocolContext> mockProxy = mock.create(person);
        System.out.println(mockProxy.get());
        MockProxy<SocialiosvcActivityProto.SocialioSvcActivityListResponse, SpringMvcHttpProtocolContext> mockProxy1 =
                mock.create(SocialiosvcActivityProto.SocialioSvcActivityListResponse.class);
        System.out.println(mockProxy1.get());
    }
}
