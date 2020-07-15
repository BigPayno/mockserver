package org.payno.mock.server.support;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;

public class MockSupplierComponentScanner extends ClassPathScanningCandidateComponentProvider{

    public MockSupplierComponentScanner(boolean useDefaultFilters, Environment environment) {
        super(useDefaultFilters, environment);
        super.addIncludeFilter(new AnnotationTypeFilter(MockSupplier.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return true;
    }
}
