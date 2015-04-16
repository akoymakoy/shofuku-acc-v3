package com.shofuku.accsystem.domain.lookups;

public class UnitOfMeasurements {
	int id;
	String value;
	String module;
	
	public UnitOfMeasurements(){
		
	}
	
	public UnitOfMeasurements(String value, String module){
		this.value = value;
		this.module = module;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
}
