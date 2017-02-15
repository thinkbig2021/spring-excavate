package com.yumeng.spring.DesignPattern.builder;

public interface PersonBuilder {
	PersonBuilder buildEye(String eyeName);
	PersonBuilder buildLeg(String legName);
	PersonBuilder buildHair(String hairName);
	Person getPerson();

}
