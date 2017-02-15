package com.yumeng.spring.DesignPattern.observer;

public interface Signallight {
    void setState(String state);
    void registerListener(LightListener listener);
    void delListener(LightListener listener);
    void notifyListener();
    
}
