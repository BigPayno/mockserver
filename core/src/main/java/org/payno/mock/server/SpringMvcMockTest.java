package org.payno.mock.server;

import com.googlecode.protobuf.format.JsonFormat;
import com.qingqing.api.proto.v1.ProtoBufResponse.BaseResponse;
import com.qingqing.common.util.JsonUtil;
import com.qingqing.socialiosvc.proto.activity.SocialiosvcActivityProto;
import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.Mock.Default;
import org.payno.mock.server.core.MockConfigurer;
import org.payno.mock.server.core.MockProxy;
import org.payno.mock.server.core.ProtocolContextHandler;
import org.payno.mock.server.core.ProtocolContextHandler.NoOp;
import org.payno.mock.server.core.configurer.ProtobufMockConfigurer;
import org.payno.mock.server.support.mvc.SpringMvcHttpProtocolContext;
import org.payno.mock.server.core.supplier.ProtobufStringSupplier;
import org.payno.mock.server.core.supplier.empty.EmptyListSupplier;
import org.payno.mock.server.core.supplier.mock.MockFieldSupplier;
import org.payno.mock.server.core.supplier.mock.MockListFieldSupplier;
import org.payno.mock.server.core.supplier.base.RandomIntSupplier;
import org.payno.mock.server.core.supplier.base.RandomLongSupplier;
import org.payno.mock.server.core.supplier.regex.TimeLongSupplier;

public class SpringMvcMockTest {

    public static void main(String[] args) {
        SpringMvcHttpProtocolContext springMvc = new SpringMvcHttpProtocolContext();
        ProtocolContextHandler<SpringMvcHttpProtocolContext> protocolContextHandler = new NoOp<>();
        MockConfigurer<SpringMvcHttpProtocolContext> protobufMockConfigurer = new ProtobufMockConfigurer<>();
        Mock<SpringMvcHttpProtocolContext> mock = new Default<>(springMvc,protobufMockConfigurer,protocolContextHandler);
        protobufMockConfigurer.registerFieldSupplier(new RandomIntSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new EmptyListSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new ProtobufStringSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new MockFieldSupplier<>(mock));
        protobufMockConfigurer.registerFieldSupplier(new MockListFieldSupplier<>(mock));
        protobufMockConfigurer.registerFieldSupplier(new TimeLongSupplier<>());
        protobufMockConfigurer.registerFieldSupplier(new RandomLongSupplier<>());

        MockProxy<BaseResponse, SpringMvcHttpProtocolContext> mockProxy = mock.create(BaseResponse.class);
        BaseResponse baseResponse = mockProxy.get();
        System.out.println(JsonFormat.printToString(baseResponse));

        MockProxy<SocialiosvcActivityProto.SocialioSvcActivityListResponse, SpringMvcHttpProtocolContext> mockProxy1 =
                mock.create(SocialiosvcActivityProto.SocialioSvcActivityListResponse.class);
        SocialiosvcActivityProto.SocialioSvcActivityListResponse listResponse = mockProxy1.get();
        System.out.println(JsonUtil.format(JsonFormat.printToString(listResponse)));
    }

}
