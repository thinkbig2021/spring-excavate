package com.yumeng.spring.DesignPattern.observer;

public class Car implements LightListener {

	private Signallight light = new ConcreteSignallight();
	public Car(Signallight light){
		this.light = light;
		light.registerListener(this);
	}
	@Override
	public void process(String state) {
		if("red".equals(state))
			System.out.println("停止");
	}

}
