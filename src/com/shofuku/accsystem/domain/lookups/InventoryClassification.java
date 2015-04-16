package com.shofuku.accsystem.domain.lookups;

public class InventoryClassification {
private int id;
private String classification;
private String subClassification;

public InventoryClassification() {
	
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getClassification() {
	return classification;
}
public void setClassification(String classification) {
	this.classification = classification;
}
public String getSubClassification() {
	return subClassification;
}
public void setSubClassification(String subClassification) {
	this.subClassification = subClassification;
}
}
