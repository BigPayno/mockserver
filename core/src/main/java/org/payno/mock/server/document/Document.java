package org.payno.mock.server.document;

import java.io.InputStream;
import java.io.OutputStream;

public interface Document<D extends Document<D>> {
    String getId();
    String getType();
    OutputStream getDocument();
}
