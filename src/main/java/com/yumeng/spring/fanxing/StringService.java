package com.yumeng.spring.fanxing;
public class StringService implements Service<String> {



    @Override
    public String process(String string) {
        return "Process String: " + string;
    }
}