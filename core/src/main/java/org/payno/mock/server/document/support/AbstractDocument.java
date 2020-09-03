package org.payno.mock.server.document.support;

import lombok.Data;
import org.payno.mock.server.document.Document;

@Data
public abstract class AbstractDocument<D extends Document<D>> implements Document<D> {

    protected String type;

    @Override
    public String getType() {
        return type;
    }
}
