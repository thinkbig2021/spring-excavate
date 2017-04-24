package com.yumeng.spring.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {
      
      
    //目标对象  
    private Object target;  
      
    /** 
     * 构造方法 
     * @param target 目标对象 
     */  
    public MyInvocationHandler(Object target) {  
        super();  
        this.target=target;  
    }  


    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
         //System.out.println(proxy);
         System.out.println(proxy.getClass());
         //在目标方法执行前简单打印一下  
         System.out.println("----------before----------");  
           
         //执行目标方法对象  
         Object result=method.invoke(target, args);  
           
         //在目标方法执行之后简单打印一下  
         System.out.println("----------after----------");  
          
         return result;  
    }  
      
      
    /** 
     * 获取目标对象的代理对象 
     * @return 代理对象 
     */  
    public Object getProxy(){  
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces(),this);  
    }  
      
      
}  