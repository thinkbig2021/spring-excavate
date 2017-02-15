package com.yumeng.spring.DesignPattern.factory;

public class BMWCarFactory implements CarFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new BWMCar();
	}

}
