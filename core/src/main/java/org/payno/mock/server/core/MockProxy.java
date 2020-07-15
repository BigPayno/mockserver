package org.payno.mock.server.core;

public interface MockProxy<T> {

   MockProxy<T> config(MockConfigurer configurer);
   T get();

   class Default<T> implements MockProxy<T>{

      T t;

      public Default(T t) {
         super();
         this.t = t;
      }

      public static <T> Default<T> create(T t){
         return new Default<>(t);
      }

      @Override
      public MockProxy<T> config(MockConfigurer configurer) {
         configurer.config(this.t);
         return this;
      }

      @Override
      public T get() {
         return this.t;
      }
   }
}
