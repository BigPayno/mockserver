package org.payno.mock.server.support;

import org.payno.mock.server.core.Mock;
import org.payno.mock.server.core.MockConfigurer;

public interface MockContextConfigurer {
    Mock mock();
    MockConfigurer mockConfigurer();
    MockContextRunner runner();
}
