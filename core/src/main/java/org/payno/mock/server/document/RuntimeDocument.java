package org.payno.mock.server.document;

import org.payno.mock.server.document.exception.DocumentRuntimeException;

public interface RuntimeDocument {
    void init() throws DocumentRuntimeException;
}
