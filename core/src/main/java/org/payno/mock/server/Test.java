package org.payno.mock.server;

import com.qingqing.socialiosvc.proto.activity.SocialiosvcActivityProto;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws Exception{
        Field field = SocialiosvcActivityProto.SocialioSvcActivityListResponse.class.getDeclaredField("activityItems_");
        System.out.println(field);
    }
}
