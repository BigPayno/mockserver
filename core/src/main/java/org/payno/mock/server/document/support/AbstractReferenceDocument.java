package org.payno.mock.server.document.support;

import lombok.Data;
import org.payno.mock.server.document.Document;
import org.payno.mock.server.document.ReferenceDocument;

import java.util.List;

@Data
public abstract class AbstractReferenceDocument<D extends Document<D>> extends AbstractDocument<D> implements ReferenceDocument<D> {

    protected Long reference;
    protected Long version;
    protected List<String> references;

    @Override
    public Long getReference() {
        return reference;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public List<String> getReferences() {
        return references;
    }
}
