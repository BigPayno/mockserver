package org.payno.mock.server.core.configurer.filter;

import lombok.extern.slf4j.Slf4j;
import org.payno.mock.server.core.FieldAccessor;

/**
 * 空对象字段过滤
 *
 * @author zhaolei22
 * @date 2020/08/06
 */
@Slf4j
public class NullObjectFieldFilter implements FieldFilter{

    @Override
    public boolean test(FieldAccessor fieldAccessor) {
        assert fieldAccessor != null;
        boolean isAssignFromObject = Object.class.isAssignableFrom(fieldAccessor.fieldReflect().type());
        boolean isNull = fieldAccessor.fieldReflect().get()==null;
        if(log.isTraceEnabled()){
            log.trace("Object is assign from {} : {},is null : {}",fieldAccessor.fieldName(),isAssignFromObject,isNull);
        }
        return isAssignFromObject&&isNull;
    }

}
