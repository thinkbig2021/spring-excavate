package com.yumeng.spring.DesignPattern.observer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Signallight light = new ConcreteSignallight();
		LightListener car = new Car(light);
		LightListener tv = new Tv(light);
		light.setState("red");
		light.setState("green");

	}

}
