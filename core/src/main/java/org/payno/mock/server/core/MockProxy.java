package org.payno.mock.server.core;

import org.springframework.lang.NonNull;

/**
 * 模拟代理,T是将要创建的对象,P是协议内容
 *
 * @author zhaolei22
 * @date 2020/08/04
 */
public interface MockProxy<T,P extends ProtocolContext<P>> {

   /**
    * 配置协议相关的信息
    *
    * @param protocolContext 协议内容
    * @return {@link MockProxy<T>}
    */
   MockProxy<T,P> inject(P protocolContext);


   /**
    * 配置创建的mock对象的属性
    *
    * @param configurer 配置
    * @return {@link MockProxy<T>}
    */
   MockProxy<T,P> config(MockConfigurer<P> configurer);


   /**
    * 得到mock对象
    *
    * @return {@link T}
    */
   T get();

   /**
    * 获得协议上下文
    *
    * @return {@link P}
    */
   P getProtocolContext();


   class Default<T,P extends ProtocolContext<P>> implements MockProxy<T,P>{

      T object;
      P protocolContext;

      public Default(@NonNull T object) {
         super();
         this.object = object;
      }

      @Override
      public MockProxy<T, P> inject(P protocolContext) {
         this.protocolContext = protocolContext;
         return this;
      }

      @Override
      public MockProxy<T, P> config(MockConfigurer<P> configurer) {
         configurer.config(this);
         return this;
      }

      @Override
      public T get() {
         return object;
      }

      @Override
      public P getProtocolContext() {
         return protocolContext;
      }
   }
}
