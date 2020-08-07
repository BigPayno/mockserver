package org.payno.mock.server.support.autoconfigure;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BooleanTypeHandler;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.ImportAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

@Slf4j
public class MockContextImportSelector implements ImportSelector , EnvironmentAware {

    Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        EnableMock enableMock = annotationMetadata.getAnnotations()
                .get(EnableMock.class).synthesize();
        Boolean enable = environment.getProperty("mock.server.enabled", Boolean.class);
        if(enable!=null&&enable){
            if(enableMock.mockServer()){
                return new String[]{"org.payno.mock.server.support.autoconfigure.BaseMockConfigurer"};
            }else{
                return new String[]{"org.payno.mock.server.support.autoconfigure.LocalMockConfigurer"};
            }
        }else{
            return new String[]{"org.payno.mock.server.support.autoconfigure.BlankConfigurer"};
        }
    }
}
