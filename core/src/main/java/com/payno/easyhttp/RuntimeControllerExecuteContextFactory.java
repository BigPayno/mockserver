package com.payno.easyhttp;

import com.google.common.collect.Lists;
import com.payno.easyhttp.core.AbstractExecuteContextFactory;
import com.payno.easyhttp.core.DefaultExecuteContext;
import com.payno.easyhttp.core.DefaultExecuteResult;
import com.payno.easyhttp.core.ExecuteContext;
import com.payno.easyhttp.core.ExecuteContextFactory;
import com.payno.easyhttp.core.ExecuteExceptionHandler;
import com.payno.easyhttp.core.HttpExecuteStrategy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Method;
import java.util.Optional;

public class RuntimeControllerExecuteContextFactory
        extends AbstractExecuteContextFactory<RuntimeControllerExecuteContextFactory>
        implements ExecuteContextFactory<RuntimeControllerExecuteContextFactory> {

    public RuntimeControllerExecuteContextFactory(RestTemplate restTemplate, ExecuteExceptionHandler exceptionHandler) {
        super(restTemplate, exceptionHandler);
    }

    @Override
    public RuntimeControllerExecuteContextFactory getFactory() {
        return this;
    }

    public <Req, Res> ExecuteContext<Req, Res> create(String urlPrefix, RuntimeControllerSource source, Req req){
        return create(urlPrefix, source.controllerType(),source.methodName(), req);
    }

    public <Req, Res> ExecuteContext<Req, Res> create(String urlPrefix,Class<?> controller, String methodName,Req req){
        RequestMapping controllerMapping = AnnotatedElementUtils.findMergedAnnotation(controller, RequestMapping.class);
        Optional<Method> methodOpt = Lists.newArrayList(controller.getMethods()).stream().filter(method1 -> method1.getName().equals(methodName)).findFirst();
        if(!methodOpt.isPresent()){
            throw new IllegalArgumentException(
                    String.format("%s %s not found",controller.getName(),methodName));
        }
        RequestMapping methodMapping = AnnotatedElementUtils.findMergedAnnotation(methodOpt.get(), RequestMapping.class);
        return new DefaultExecuteContext<>(
                restTemplate,
                urlPrefix+getFirstSafe(controllerMapping.value())+getFirstSafe(methodMapping.value()),
                req,
                (Class<Res>) methodOpt.get().getReturnType(),
                methodMapping.method().length == 0 ?
                        HttpExecuteStrategy.POST :
                        HttpExecuteStrategy.valueOf(methodMapping.method()[0]),
                exceptionHandler,
                new DefaultExecuteResult<>()
        );
    }

    String getFirstSafe(String[] args){
        if(args==null||args.length<1){
            return "";
        }else{
            return args[0];
        }
    }
}
