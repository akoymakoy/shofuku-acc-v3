package com.shofuku.accsystem.domain.inventory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;

public class PurchaseOrderDetails implements Serializable{

	private static final long serialVersionUID = -7795516207818995492L;
	DecimalFormat df= new DecimalFormat("#.##");
	public PurchaseOrderDetails() {

	}

	public PurchaseOrderDetails(String itemCode,
			String description, double quantity, String unitOfMeasurement,
			double unitCost, double amount,String isVattable,double vatAmount,double vattableAmount) {
		this.itemCode = itemCode;
		this.description = description;
		this.quantity = quantity;
		this.unitOfMeasurement = unitOfMeasurement;
		this.unitCost = unitCost;
		this.amount = amount;
		//START: 2013 - PHASE 3 : PROJECT 4: MARK
		this.isVattable=isVattable;
		this.vatAmount = vatAmount;
		this.vattableAmount = vattableAmount;
		//END: 2013 - PHASE 3 : PROJECT 4: MARK
	}
	
	public PurchaseOrderDetails(String itemCode,double quantityIn) {
		this.itemCode = itemCode;
		this.quantityIn = quantityIn;
	}
	//not in database
	private String group;
	private boolean isInFinishedGoods;

	private int id;
	private String itemCode;
	private String description;
	private double quantity;
	private double quantityIn;
	private double quantityOut;
	private Timestamp orderCreatedDate;
	private String unitOfMeasurement;
	private double unitCost;
	private double amount;
	//START: 2013 - PHASE 3 : PROJECT 4: MARK
	private String isVattable;
	private double vatAmount;
	private double vattableAmount;
	private String parent;
	//END: 2013 - PHASE 3 : PROJECT 4: MARK

	//START 2014 - PHASE 4: PROJECT N/A : MARK
	private String itemType;
	//END 2014 - PHASE 4: PROJECT N/A : MARK
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}


	public boolean isInFinishedGoods() {
		return isInFinishedGoods;
	}

	public void setInFinishedGoods(boolean isInFinishedGoods) {
		this.isInFinishedGoods = isInFinishedGoods;
	}
	//START: 2013 - PHASE 3 : PROJECT 4: MARK
	public Timestamp getOrderCreatedDate() {
		return orderCreatedDate;
	}

	public void setOrderCreatedDate(Timestamp orderCreatedDate) {
		this.orderCreatedDate = orderCreatedDate;
	}

	public double getQuantityIn() {
		return quantityIn;
	}

	public void setQuantityIn(double quantityIn) {
		this.quantityIn = quantityIn;
	}

	public double getQuantityOut() {
		return quantityOut;
	}

	public void setQuantityOut(double quantityOut) {
		this.quantityOut = quantityOut;
	}
	public String getIsVattable() {
		return isVattable;
	}

	public void setIsVattable(String isVattable) {
		this.isVattable = isVattable;
	}
	public double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}
	public double getVattableAmount() {
		return vattableAmount;
	}

	public void setVattableAmount(double vattableAmount) {
		this.vattableAmount = vattableAmount;
	}
	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	//END: 2013 - PHASE 3 : PROJECT 4: MARK
}
