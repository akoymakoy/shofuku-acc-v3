package com.shofuku.accsystem.domain.inventory;

public class Ingredient {

	public Ingredient() {
	}

	public Ingredient(String productCode, String description,
			double quantity, String unitOfMeasurement, double standardPricePerUnit,double actualPricePerUnit,double transferPricePerUnit) {
		this.productCode = productCode;
		this.description = description;
		this.quantity = quantity;
		this.unitOfMeasurement = unitOfMeasurement;
		this.standardPricePerUnit = standardPricePerUnit;
		this.actualPricePerUnit = actualPricePerUnit;
		this.transferPricePerUnit = transferPricePerUnit;
	}

	private int id;
	private String productCode;
	private String description;
	private double quantity;
	private String unitOfMeasurement;
	private double standardPricePerUnit;
	private double actualPricePerUnit;
	private double transferPricePerUnit;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public double getStandardPricePerUnit() {
		return standardPricePerUnit;
	}

	public void setStandardPricePerUnit(double standardPricePerUnit) {
		this.standardPricePerUnit = standardPricePerUnit;
	}

	public double getActualPricePerUnit() {
		return actualPricePerUnit;
	}

	public void setActualPricePerUnit(double actualPricePerUnit) {
		this.actualPricePerUnit = actualPricePerUnit;
	}
	public double getTransferPricePerUnit() {
		return transferPricePerUnit;
	}

	public void setTransferPricePerUnit(double transferPricePerUnit) {
		this.transferPricePerUnit = transferPricePerUnit;
	}
}
