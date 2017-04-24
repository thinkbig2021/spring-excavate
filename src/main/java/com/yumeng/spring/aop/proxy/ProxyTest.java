package com.yumeng.spring.aop.proxy;

public class ProxyTest{
      
    //@Test
    public static void main(String args[]) throws Throwable{
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        //实例化目标对象
        UserService userService=new UserServiceImpl();  
          
        //实例化Invocation  
        MyInvocationHandler invocationHandler=new MyInvocationHandler(userService);  
          
        //根据目标生成代理对象  
        UserService proxy=(UserService)invocationHandler.getProxy();  
          
        //调用代理对象方法  
        proxy.add();  
    }  
}  
