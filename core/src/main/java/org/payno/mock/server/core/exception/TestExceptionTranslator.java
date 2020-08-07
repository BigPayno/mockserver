package org.payno.mock.server.core.exception;

import org.payno.mock.server.core.MockExceptionTranslator;

/**
 * 测试异常翻译
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public class TestExceptionTranslator implements MockExceptionTranslator {

    @Override
    public MockException translate(Exception exception) {
        return new MockException();
    }
}
