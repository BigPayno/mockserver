package org.payno.mock.server.core.configurer.filter;

import lombok.extern.slf4j.Slf4j;
import org.payno.mock.server.core.FieldAccessor;

@Slf4j
public class BaseFieldFilter implements FieldFilter{
    @Override
    public boolean test(FieldAccessor fieldAccessor) {
        assert fieldAccessor != null;
        boolean isAssignFromObject = Object.class.isAssignableFrom(fieldAccessor.fieldReflect().type());
        if(log.isTraceEnabled()){
            log.trace("Object is assign from {} : {}",fieldAccessor.fieldName(),isAssignFromObject);
        }
        return !isAssignFromObject;
    }
}
