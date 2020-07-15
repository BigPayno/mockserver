package org.payno.mock.server.core;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

public interface MockConfigurer {
    void config(Object object);
    void addValueSupplier(ValueSupplier supplier);

    class Default implements MockConfigurer{

        static Logger logger = LoggerFactory.getLogger(Default.class);

        @Override
        public void config(Object object) {
            if(logger.isDebugEnabled()){
                logger.debug("{} doesn't config with anything!");
            }
        }

        @Override
        public void addValueSupplier(ValueSupplier supplier) {
            // do nothing!
        }
    }
}
