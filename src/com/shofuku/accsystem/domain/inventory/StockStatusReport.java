package com.shofuku.accsystem.domain.inventory;

public class StockStatusReport {
	
	//fill in with columns
	private String itemCode;
	private String itemDescription;
	private String unitOfMeasurement;
	private double unitCost;
	private double beginningBalanceQty;
	private double beginningBalanceAmount;
	private double receiptsQty;
	private double receiptsAmount;
	private double issuancesQuantity;
	private double issuancesAmount;
	private double endingBalanceQty;
	private double endingBalanceAmount;
	private String classification;
	private String subClassification;
	private String itemType;
	
	public StockStatusReport() {
	}

	public StockStatusReport(String itemCode, String itemDescription,
			String unitOfMeasurement, double unitCost,
			double beginningBalanceQty, double beginningBalanceAmount,
			double receiptsQty, double receiptsAmount,
			double issuancesQuantity, double issuancesAmount,
			double endingBalanceQty, double endingBalanceAmount,
			String classification, String subClassification, String itemType) {
		this.itemCode = itemCode;
		this.itemDescription = itemDescription;
		this.unitOfMeasurement = unitOfMeasurement;
		this.unitCost = unitCost;
		this.beginningBalanceQty = beginningBalanceQty;
		this.beginningBalanceAmount = beginningBalanceAmount;
		this.receiptsQty = receiptsQty;
		this.receiptsAmount = receiptsAmount;
		this.issuancesQuantity = issuancesQuantity;
		this.issuancesAmount = issuancesAmount;
		this.endingBalanceQty = endingBalanceQty;
		this.endingBalanceAmount = endingBalanceAmount;
		this.classification = classification;
		this.subClassification = subClassification;
		this.itemType = itemType;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}
	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public double getBeginningBalanceQty() {
		return beginningBalanceQty;
	}
	public void setBeginningBalanceQty(double beginningBalanceQty) {
		this.beginningBalanceQty = beginningBalanceQty;
	}
	public double getBeginningBalanceAmount() {
		return beginningBalanceAmount;
	}
	public void setBeginningBalanceAmount(double beginningBalanceAmount) {
		this.beginningBalanceAmount = beginningBalanceAmount;
	}
	public double getReceiptsQty() {
		return receiptsQty;
	}
	public void setReceiptsQty(double receiptsQty) {
		this.receiptsQty = receiptsQty;
	}
	public double getReceiptsAmount() {
		return receiptsAmount;
	}
	public void setReceiptsAmount(double receiptsAmount) {
		this.receiptsAmount = receiptsAmount;
	}
	public double getIssuancesQuantity() {
		return issuancesQuantity;
	}
	public void setIssuancesQuantity(double issuancesQuantity) {
		this.issuancesQuantity = issuancesQuantity;
	}
	public double getIssuancesAmount() {
		return issuancesAmount;
	}
	public void setIssuancesAmount(double issuancesAmount) {
		this.issuancesAmount = issuancesAmount;
	}
	public double getEndingBalanceQty() {
		return endingBalanceQty;
	}
	public void setEndingBalanceQty(double endingBalanceQty) {
		this.endingBalanceQty = endingBalanceQty;
	}
	public double getEndingBalanceAmount() {
		return endingBalanceAmount;
	}
	public void setEndingBalanceAmount(double endingBalanceAmount) {
		this.endingBalanceAmount = endingBalanceAmount;
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

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
}
