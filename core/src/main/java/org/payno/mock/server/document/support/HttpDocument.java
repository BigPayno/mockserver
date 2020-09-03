package org.payno.mock.server.document.support;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.OutputStream;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpDocument extends AbstractReferenceDocument<HttpDocument>{

    String uri;
    RequestDocument requestDocument;
    ResponseDocument responseDocument;


    public static class RequestDocument extends AbstractReferenceDocument<RequestDocument>{

        @Override
        public String getType() {
            return "request";
        }

        @Override
        public OutputStream getDocument() {
            return null;
        }
    }

    public static class ResponseDocument extends AbstractReferenceDocument<ResponseDocument>{
        @Override
        public String getType() {
            return "response";
        }

        @Override
        public OutputStream getDocument() {
            return null;
        }
    }

    @Override
    public OutputStream getDocument() {
        return null;
    }

    @Override
    public String getType() {
        return "http";
    }
}
