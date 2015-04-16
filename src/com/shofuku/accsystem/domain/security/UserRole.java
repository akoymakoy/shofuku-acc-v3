package com.shofuku.accsystem.domain.security;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 967830983335276636L;
	private int userRoleId;
	private String userRoleName;
	private String supplier;
	private String supplierPurchaseOrder;
	private String supplierReceivingReport;
	private String supplierInvoice;
	private String supplierSummary;
	private String customer;
	private String customerPurchaseOrder;
	private String customerDeliveryReceipt;
	private String customerInvoice;
	private String customerSummary;
	private String rawMaterial;
	private String tradedItem;
	private String finishedGood;
	private String inventoryFPTS;
	private String inventoryOR;
	private String inventoryRS;
	private String inventorySummary;
	private String disbursementPettyCash;
	private String disbursementCashPayment;
	private String disbursementCheckPayment;
	private String disbursementCheckVoucher;
	private String disbursementSummary;
	private String receiptsOrSales;
	private String receiptsOrOther;
	private String receiptsCheck;
	private String receiptSummary;
	private String accountEntryProfile;
	private String journalEntryProfile;
	private String financialReport;
	private String templateGenerator;
	private String userProfile;
	private String userRole;
	
	
	
	public String getTemplateGenerator() {
		return templateGenerator;
	}
	public void setTemplateGenerator(String templateGenerator) {
		this.templateGenerator = templateGenerator;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getSupplierPurchaseOrder() {
		return supplierPurchaseOrder;
	}
	public void setSupplierPurchaseOrder(String supplierPurchaseOrder) {
		this.supplierPurchaseOrder = supplierPurchaseOrder;
	}
	public String getSupplierReceivingReport() {
		return supplierReceivingReport;
	}
	public void setSupplierReceivingReport(String supplierReceivingReport) {
		this.supplierReceivingReport = supplierReceivingReport;
	}
	public String getSupplierInvoice() {
		return supplierInvoice;
	}
	public void setSupplierInvoice(String supplierInvoice) {
		this.supplierInvoice = supplierInvoice;
	}
	public String getSupplierSummary() {
		return supplierSummary;
	}
	public void setSupplierSummary(String supplierSummary) {
		this.supplierSummary = supplierSummary;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomerPurchaseOrder() {
		return customerPurchaseOrder;
	}
	public void setCustomerPurchaseOrder(String customerPurchaseOrder) {
		this.customerPurchaseOrder = customerPurchaseOrder;
	}
	public String getCustomerDeliveryReceipt() {
		return customerDeliveryReceipt;
	}
	public void setCustomerDeliveryReceipt(String customerDeliveryReceipt) {
		this.customerDeliveryReceipt = customerDeliveryReceipt;
	}
	public String getCustomerInvoice() {
		return customerInvoice;
	}
	public void setCustomerInvoice(String customerInvoice) {
		this.customerInvoice = customerInvoice;
	}
	public String getCustomerSummary() {
		return customerSummary;
	}
	public void setCustomerSummary(String customerSummary) {
		this.customerSummary = customerSummary;
	}
	public String getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(String rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public String getTradedItem() {
		return tradedItem;
	}
	public void setTradedItem(String tradedItem) {
		this.tradedItem = tradedItem;
	}
	public String getFinishedGood() {
		return finishedGood;
	}
	public void setFinishedGood(String finishedGood) {
		this.finishedGood = finishedGood;
	}
	public String getInventoryFPTS() {
		return inventoryFPTS;
	}
	public void setInventoryFPTS(String inventoryFPTS) {
		this.inventoryFPTS = inventoryFPTS;
	}
	public String getInventoryOR() {
		return inventoryOR;
	}
	public void setInventoryOR(String inventoryOR) {
		this.inventoryOR = inventoryOR;
	}
	public String getInventoryRS() {
		return inventoryRS;
	}
	public void setInventoryRS(String inventoryRS) {
		this.inventoryRS = inventoryRS;
	}
	public String getInventorySummary() {
		return inventorySummary;
	}
	public void setInventorySummary(String inventorySummary) {
		this.inventorySummary = inventorySummary;
	}
	public String getDisbursementPettyCash() {
		return disbursementPettyCash;
	}
	public void setDisbursementPettyCash(String disbursementPettyCash) {
		this.disbursementPettyCash = disbursementPettyCash;
	}
	public String getDisbursementCashPayment() {
		return disbursementCashPayment;
	}
	public void setDisbursementCashPayment(String disbursementCashPayment) {
		this.disbursementCashPayment = disbursementCashPayment;
	}
	public String getDisbursementCheckPayment() {
		return disbursementCheckPayment;
	}
	public void setDisbursementCheckPayment(String disbursementCheckPayment) {
		this.disbursementCheckPayment = disbursementCheckPayment;
	}
	public String getDisbursementCheckVoucher() {
		return disbursementCheckVoucher;
	}
	public void setDisbursementCheckVoucher(String disbursementCheckVoucher) {
		this.disbursementCheckVoucher = disbursementCheckVoucher;
	}
	public String getDisbursementSummary() {
		return disbursementSummary;
	}
	public void setDisbursementSummary(String disbursementSummary) {
		this.disbursementSummary = disbursementSummary;
	}
	public String getReceiptsOrSales() {
		return receiptsOrSales;
	}
	public void setReceiptsOrSales(String receiptsOrSales) {
		this.receiptsOrSales = receiptsOrSales;
	}
	public String getReceiptsOrOther() {
		return receiptsOrOther;
	}
	public void setReceiptsOrOther(String receiptsOrOther) {
		this.receiptsOrOther = receiptsOrOther;
	}
	public String getReceiptsCheck() {
		return receiptsCheck;
	}
	public void setReceiptsCheck(String receiptsCheck) {
		this.receiptsCheck = receiptsCheck;
	}
	public String getReceiptSummary() {
		return receiptSummary;
	}
	public void setReceiptSummary(String receiptSummary) {
		this.receiptSummary = receiptSummary;
	}
	public String getAccountEntryProfile() {
		return accountEntryProfile;
	}
	public void setAccountEntryProfile(String accountEntryProfile) {
		this.accountEntryProfile = accountEntryProfile;
	}
	public String getJournalEntryProfile() {
		return journalEntryProfile;
	}
	public void setJournalEntryProfile(String journalEntryProfile) {
		this.journalEntryProfile = journalEntryProfile;
	}
	public String getFinancialReport() {
		return financialReport;
	}
	public void setFinancialReport(String financialReport) {
		this.financialReport = financialReport;
	}
	
	
	

}
