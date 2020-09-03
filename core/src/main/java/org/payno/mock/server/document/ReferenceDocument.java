package org.payno.mock.server.document;

import java.util.List;

public interface ReferenceDocument<D extends Document<D>> extends Document<D> {
    @Override
    default String getId(){
        return getReference().toString()+":"+getVersion().toString();
    }

    Long getReference();
    Long getVersion();
    List<String> getReferences();
}
