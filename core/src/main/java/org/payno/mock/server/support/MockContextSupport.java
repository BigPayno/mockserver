package org.payno.mock.server.support;

import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.MockConfigurer;

public class MockContextSupport implements MockContextConfigurer{
    @Override
    public Mock mock() {
        return new Mock.Default();
    }

    @Override
    public MockConfigurer mockConfigurer() {
        return new MockConfigurer.Default();
    }

    @Override
    public MockContextRunner runner() {
        return new MockContextRunner();
    }
}
