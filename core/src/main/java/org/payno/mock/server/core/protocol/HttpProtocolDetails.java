package org.payno.mock.server.core.protocol;

import lombok.Data;

import java.util.Map;

@Data
public class HttpProtocolDetails {
    Map<String,String> headers;
    Map<String,String> cookies;
    String redirectUri;
    Integer responseStatus;
    Integer mockException;
    Integer sleepTime;
}
