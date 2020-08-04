package org.payno.mock.server;

import com.googlecode.protobuf.format.JsonFormat;
import com.qingqing.api.proto.v1.ProtoBufResponse.BaseResponse;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.socialiosvc.proto.activity.SocialiosvcActivityProto;
import lombok.Data;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.configurer.SpringMvcProtobufMockConfigurer;
import org.payno.mock.server.core.protocol.SpringMvcHttpProtocolContext;
import org.payno.mock.server.core.supplier.empty.EmptyListSupplier;
import org.payno.mock.server.core.supplier.mock.MockFieldSupplier;
import org.payno.mock.server.core.supplier.mock.MockListFieldSupplier;
import org.payno.mock.server.core.supplier.ProtobufStringSupplier;
import org.payno.mock.server.core.supplier.random.RandomIntSupplier;
import org.payno.mock.server.core.supplier.random.RandomLongSupplier;
import org.payno.mock.server.core.supplier.regex.TimeLongSupplier;

public class SpringMvcMockTest {

    @Data
    static class Person{
        String name;
        String gender;
    }

    public static void main(String[] args) {
        SpringMvcHttpProtocolContext springMvc = new SpringMvcHttpProtocolContext();
        MockConfigurer<SpringMvcHttpProtocolContext> protobufMockConfigurer = new SpringMvcProtobufMockConfigurer();
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(springMvc,protobufMockConfigurer);
        protobufMockConfigurer.registerFieldSupplier(new RandomIntSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new EmptyListSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new ProtobufStringSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new MockFieldSupplier<>(mock));
        protobufMockConfigurer.registerFieldSupplier(new MockListFieldSupplier<>(mock));
        protobufMockConfigurer.registerFieldSupplier(new TimeLongSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new RandomLongSupplier<>());
        MockProxy<BaseResponse,SpringMvcHttpProtocolContext> mockProxy = mock.create(BaseResponse.class, null);
        BaseResponse baseResponse = mockProxy.get();
        System.out.println(JsonFormat.printToString(baseResponse));

        MockProxy<SocialiosvcActivityProto.SocialioSvcActivityListResponse,SpringMvcHttpProtocolContext> mockProxy1 =
                mock.create(SocialiosvcActivityProto.SocialioSvcActivityListResponse.class,null);
        SocialiosvcActivityProto.SocialioSvcActivityListResponse listResponse = mockProxy1.get();
        System.out.println(JsonUtil.format(JsonFormat.printToString(listResponse)));
    }

}
