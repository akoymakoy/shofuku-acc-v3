package com.shofuku.accsystem.domain.financials;

import java.io.Serializable;

public class AccountEntryProfile  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5640780320190734831L;
	
	private String accountCode;
	private String parentCode;
	private String name;
	private String reportType;
	private String classification;
	private String reportAction;
	private String hierarchy;
	private AccountingRules accountingRules;
	private String isActive;
	
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getReportAction() {
		return reportAction;
	}
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public AccountingRules getAccountingRules() {
		return accountingRules;
	}
	public void setAccountingRules(AccountingRules accountingRules) {
		this.accountingRules = accountingRules;
	}
	public String getHierarchy() {
		return hierarchy;
	}
	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}
		
}
