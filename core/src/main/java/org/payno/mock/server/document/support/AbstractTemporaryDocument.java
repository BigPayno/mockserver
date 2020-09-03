package org.payno.mock.server.document.support;

import lombok.Data;
import org.payno.mock.server.document.Document;
import org.payno.mock.server.document.TemporaryDocument;

import java.io.InputStream;

@Data
public abstract class AbstractTemporaryDocument<D extends Document<D>> extends AbstractDocument<D> implements TemporaryDocument<D> {

    protected String resource;
    protected InputStream inputStream;

    @Override
    public String resource() {
        return resource;
    }

    @Override
    public InputStream getResource() {
        return inputStream;
    }
}
