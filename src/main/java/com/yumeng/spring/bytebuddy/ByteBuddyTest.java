package com.yumeng.spring.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class ByteBuddyTest {

    @Test
    public void helloWorld() throws Exception {
        Class<?> dynamicObject = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        assertThat(dynamicObject.newInstance().toString(), is("Hello World!"));
    }

    public static class Foo {
        public String bar() {
            return null;
        }

        public String foo() {
            return null;
        }

        public String foo(Object o) {
            return null;
        }
    }

    @Test
    public void interceptingMethods() throws Exception {
        Foo dynamicFoo = new ByteBuddy()
                .subclass(Foo.class)
                .method(ElementMatchers.isDeclaredBy(Foo.class)).intercept(FixedValue.value("One!"))
                .method(ElementMatchers.named("foo")).intercept(FixedValue.value("Two!"))
                .method(ElementMatchers.named("foo").and(ElementMatchers.takesArguments(1))).intercept(FixedValue.value("Three!"))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .newInstance();

        assertThat(dynamicFoo.bar(), equalTo("One!"));
        assertThat(dynamicFoo.foo(), equalTo("Two!"));
        assertThat(dynamicFoo.foo("3"), equalTo("Three!"));
    }

    public static class Source {
        public String hello(String name) {
            return null;
        }
    }

    public static class Target {
        public static String intercept(String name) {
            return "Hello " + name + "!";
        }

        public static String intercept(int i) {
            return Integer.toString(i);
        }

        public static String intercept(Object o) {
            return o.toString();
        }
    }

    @Test
    public void delegatingMethodCalls() throws Exception {
        Source dynamicSource = new ByteBuddy()
                .subclass(Source.class)
                .method(ElementMatchers.named("hello")).intercept(MethodDelegation.to(Target.class))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .newInstance();

        assertThat(dynamicSource.hello("World"), equalTo("Hello World!"));
    }

    public static class MemoryDatabase {
        public List<String> load(String info) {
            return Arrays.asList(info + ": foo", info + ": bar");
        }
    }

    public static class LoggerInterceptor {
        public static List<String> log(@SuperCall Callable<List<String>> zuper) throws Exception {
            System.out.println("Calling database");
            try {
                return zuper.call();
            } finally {
                System.out.println("Returned from database");
            }
        }
    }

    @Test
    public void delegatingMethodCallsWithSuperCall() throws Exception {
        MemoryDatabase dynamicMemoryDatabase = new ByteBuddy()
                .subclass(MemoryDatabase.class)
                .method(ElementMatchers.named("load")).intercept(MethodDelegation.to(LoggerInterceptor.class))
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded()
                .newInstance();

        assertThat(dynamicMemoryDatabase.load("json"), equalTo(Arrays.asList("json: foo", "json: bar")));
    }

    // TODO continue at the @Super explanation (http://bytebuddy.net/#/tutorial)
}