package com.shofuku.accsystem.domain.inventory;

import java.sql.Clob;

public class StockStatus {

	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	String itemCode;
	Clob xmlInventoryCount;
	int month;
	int  year;
	
	public StockStatus(String itemCode, Clob xmlInventoryCount, int month,
			int year) {
		this.itemCode = itemCode;
		this.xmlInventoryCount = xmlInventoryCount;
		this.month = month;
		this.year = year;
	}
	
	public StockStatus() {
		
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Clob getXmlInventoryCount() {
		return xmlInventoryCount;
	}
	public void setXmlInventoryCount(Clob xmlInventoryCount) {
		this.xmlInventoryCount = xmlInventoryCount;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
}
