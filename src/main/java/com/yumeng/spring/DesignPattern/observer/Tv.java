package com.yumeng.spring.DesignPattern.observer;

public class Tv implements LightListener {

	private Signallight light = new ConcreteSignallight();
	public Tv(Signallight light){
		this.light = light;
		light.registerListener(this);
	}
	@Override
	public void process(String state) {
		if("red".equals(state))
			System.out.println("开");
		else
			System.out.println("关");
	}

}
