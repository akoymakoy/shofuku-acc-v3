package com.shofuku.accsystem.domain.financials;

import java.io.Serializable;

public class AccountingRules implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ruleId;
	private String accountCode;
	private String action;
	private String supplierPurchaseOrder;
	private String supplierReceivingReport;
	private String supplierInvoice;
	private String customerPurchaseOrder;
	private String customerDeliveryReceipt;
	private String customerInvoice;
	private String inventoryFPTS;
	private String inventoryOR;
	private String inventoryRS;	
	private String disbursementPettyCash;
	private String disbursementCashPayment;
	private String disbursementCheckPayment;
	private String disbursementCheckVoucher;
	private String receiptsOrSales;
	private String receiptsOrOther;
	private String receiptsCheck;
	private String journal;
	
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public String getJournal() {
		return journal;
	}
	public void setJournal(String journal) {
		this.journal = journal;
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

}
