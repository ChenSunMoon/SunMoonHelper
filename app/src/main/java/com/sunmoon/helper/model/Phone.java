package com.sunmoon.helper.model;
/*
联系人信息存储
 */
public class Phone {
	private String name;
	private String number;

	public Phone(String name, String number) {
		this.number=number;
		this.name=name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
