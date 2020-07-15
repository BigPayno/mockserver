package org.payno.mock.server.support;

import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MockContextImportSelector implements ImportSelector, ImportAware {

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        // do nothing
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"org.payno.mock.server.MockContextAutoConfiguration"};
    }
}
