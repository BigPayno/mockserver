package org.payno.mock.server.core.exception;

import org.payno.mock.server.core.MockExceptionTranslator;

/**
 * 模拟异常跑步
 *
 * @author zhaolei22
 * @date 2020/08/07
 */
public interface MockExceptionRunner<T> {
    /**
     * 除了翻译
     *
     * @return {@link MockExceptionTranslator}
     */
    MockExceptionTranslator exceptionTranslator();

    /**
     * 得到
     *
     * @return {@link T}
     */
    T get();

    /**
     * 处理
     *
     * @param t t
     * @throws Exception 异常
     */
    void handle(T t) throws Exception;

    /**
     * 执行
     *
     * @throws MockException 模拟异常
     */
    default void exec() throws MockException{
        try{
            handle(get());
        }catch (Exception e){
            exceptionTranslator().translate(e);
        }
    }
}
