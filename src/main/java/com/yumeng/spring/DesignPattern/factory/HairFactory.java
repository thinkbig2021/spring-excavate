package com.yumeng.spring.DesignPattern.factory;

public class HairFactory implements ElectricFactory {

	@Override
	public TV createTv() {
		// TODO Auto-generated method stub
		return new HairTv();
	}

	@Override
	public Icebox createIcebox() {
		// TODO Auto-generated method stub
		return new HairIcebox();
	}

}
