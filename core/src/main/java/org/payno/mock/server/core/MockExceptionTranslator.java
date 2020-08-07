package org.payno.mock.server.core;

import org.payno.mock.server.core.exception.MockException;

/**
 * 模拟异常翻译
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public interface MockExceptionTranslator {
    /**
     * 翻译
     *
     * @param exception 异常
     * @return {@link MockException}
     */
    MockException translate(Exception exception);

}
