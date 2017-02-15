package com.yumeng.spring.DesignPattern.factory;

public class BenzCarFactory implements CarFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new BenzCar();
	}

}
