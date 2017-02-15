package com.yumeng.spring.DesignPattern.builder;

public class ConcretePersonbuilder implements PersonBuilder {
	private Person person;
    public ConcretePersonbuilder() {
		// TODO Auto-generated constructor stub
		person = new Person();
	}

	@Override
	public PersonBuilder buildEye(String eyeName) {
		// TODO Auto-generated method stub
		person.setEye(eyeName);
		return this;
	}

	@Override
	public PersonBuilder buildLeg(String legName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonBuilder buildHair(String hairName) {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Person getPerson() {
		// TODO Auto-generated method stub
		return null;
	}

}
