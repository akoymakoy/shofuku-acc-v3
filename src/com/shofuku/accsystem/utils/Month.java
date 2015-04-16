package com.shofuku.accsystem.utils;

public enum Month {

	JAN(1,"Jan"),
	FEB(2,"Feb"),
	MAR(3,"Mar"),
	APR(4,"Apr"),
	MAY(5,"May"),
	JUN(6,"Jun"),
	JUL(7,"Jul"),
	AUG(8,"Aug"),
	SEP(9,"Sep"),
	OCT(10,"Oct"),
	NOV(11,"Nov"),
	DEC(12,"Dec");
	
	private int month;
	private String monthAbrv;
	
	Month(int monthNumber,String monthAbrv){
	this.month = monthNumber;
	this.monthAbrv = monthAbrv;
	}
	
	public int getMonth() {
		return month;
	}
	
	public String getMonthAbrv() {
		return monthAbrv;
	}
	
}
