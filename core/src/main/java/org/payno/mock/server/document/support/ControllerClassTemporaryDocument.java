package org.payno.mock.server.document.support;

import lombok.Data;

import java.io.InputStream;
import java.io.OutputStream;

@Data
public class ControllerClassTemporaryDocument extends AbstractTemporaryDocument<ControllerClassTemporaryDocument>{

    @Override
    public OutputStream getDocument() {
        return null;
    }

    @Override
    public String getType() {
        return "Controller.class";
    }

    @Override
    public InputStream asSink() {
        return null;
    }

    @Override
    public OutputStream asSource() {
        return null;
    }
}
