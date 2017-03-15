package com.yumeng.spring.bytebuddy;


import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 * Created by yumeng on 2017/3/12.
 */
public class Test {
    public static void main(String[] args) {
       // ByteBuddyAgent.install();
        Foo foo = new Foo();
        new ByteBuddy()
                .redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

    }

}
