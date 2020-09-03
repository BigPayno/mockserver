package com.payno.easyhttp;

import com.google.common.base.CaseFormat;

public class IdeaTestContractRuntimeControllerSource implements RuntimeControllerSource{

    private static String getTestControllerName(String testClassName){
        return testClassName.substring(0,testClassName.length()-4);
    }

    private static String getTestMethodName(String testMethodName){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL,testMethodName.substring(4));
    }

    @Override
    public Class<?> controllerType() throws ClassNotFoundException {
        String testClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        Class<?> controllerClass = Class.forName(getTestControllerName(testClassName));
        return controllerClass;
    }

    @Override
    public String methodName() {
        String testMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        return getTestMethodName(testMethodName);
    }
}
