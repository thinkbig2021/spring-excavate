package com.yumeng.spring.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by yumeng on 2017/3/23.
 */
public class Mytest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {

        MyServer o = new MyServer();
        BaseServer b=(BaseServer)o;
        System.out.println(b.getClass());
        System.out.println(o.getClass());
        Method m = o.getClass().getDeclaredMethod("myfunction");
        m.invoke(o);
        BaseServer baseServer = new BaseServer();
        System.out.println(b.getClass().getDeclaredField("password"));

    }
    static interface  Server{
        void connection();
    }
    static class BaseServer{
        private String name ="11";
        private final String password="222";
        public String email = "www@qq.com";
    }
    static class MyServer extends BaseServer implements Server{

        @Override
        public void connection() {
            System.out.println("connection");
        }
        public void myfunction(){
            System.out.println("my function");
        }
    }
}
