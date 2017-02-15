package com.yumeng.spring.DesignPattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSignallight implements Signallight {

	private List<LightListener>  listeners = new ArrayList<LightListener>();
	private String state;
	@Override
	public void setState(String state) {
         this.state=state;
         notifyListener();
	}

	@Override
	public void registerListener(LightListener car) {
		listeners.add(car);
	}

	@Override
	public void delListener(LightListener car) {

	}

	@Override
	public void notifyListener() {
		// TODO Auto-generated method stub
		for(LightListener listener : listeners){
			listener.process(state);
		}

	}

}
