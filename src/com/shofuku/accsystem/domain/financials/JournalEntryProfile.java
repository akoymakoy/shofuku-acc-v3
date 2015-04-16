package com.shofuku.accsystem.domain.financials;

import java.io.Serializable;
import java.sql.Timestamp;

public class JournalEntryProfile implements Serializable{

private static final long serialVersionUID = -5640780320190734831L;
	
	private String accountCode;
	private String entryNo;
	private String entryName;
	private String comment;
	private String isAccepted;
	private double amount;
	private Timestamp entryDate;
	private Timestamp postingDate;
	AccountEntryProfile aepCredit;
	AccountEntryProfile aepDebit;
	private String module;
	
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getEntryNo() {
		return entryNo;
	}
	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIsAccepted() {
		return isAccepted;
	}
	public void setIsAccepted(String isAccepted) {
		this.isAccepted = isAccepted;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Timestamp getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Timestamp entryDate) {
		this.entryDate = entryDate;
	}
	public Timestamp getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(Timestamp postingDate) {
		this.postingDate = postingDate;
	}
	public AccountEntryProfile getAepCredit() {
		return aepCredit;
	}
	public void setAepCredit(AccountEntryProfile aepCredit) {
		this.aepCredit = aepCredit;
	}
	public AccountEntryProfile getAepDebit() {
		return aepDebit;
	}
	public void setAepDebit(AccountEntryProfile aepDebit) {
		this.aepDebit = aepDebit;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}	
	
}
