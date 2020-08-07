package org.payno.mock.server.core.configurer.filter;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.payno.mock.server.core.FieldAccessor;

import java.util.function.Predicate;

/**
 * 字段过滤
 *
 * @author zhaolei22
 * @date 2020/08/06
 */
public interface FieldFilter extends Predicate<FieldAccessor>, com.google.common.base.Predicate<FieldAccessor> {
    /**
     * apply
     *
     * @param fieldAccessor 字段访问器
     * @return boolean
     */
    @Override
    default boolean apply(@Nullable FieldAccessor fieldAccessor){
        return test(fieldAccessor);
    }
}
