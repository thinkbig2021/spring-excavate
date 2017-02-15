package com.yumeng.spring.fanxing;

public interface Invoker<T> {
   Class<T> getInterface();
   Result invoke() ;
}
