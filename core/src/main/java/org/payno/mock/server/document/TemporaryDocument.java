package org.payno.mock.server.document;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.io.OutputStream;

public interface TemporaryDocument<D extends Document<D>> extends Document<D>{
    @Override
    default String getId(){
        return resource();
    }

    /**
     * 资源
     *
     * @see Resource
     * @return {@link String}
     */
    String resource();
    InputStream asSink();
    OutputStream asSource();
}
