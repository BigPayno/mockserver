package org.payno.mock.server.document.support;

import java.io.OutputStream;
import java.util.List;

public class ControllerClassReferenceDocument extends AbstractReferenceDocument<ControllerClassReferenceDocument>{

    List<HttpDocument> httpDocuments;

    @Override
    public OutputStream getDocument() {
        return null;
    }
}
