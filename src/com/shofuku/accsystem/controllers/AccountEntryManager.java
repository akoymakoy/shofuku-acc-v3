package com.shofuku.accsystem.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;

import com.shofuku.accsystem.dao.impl.AccountEntryDaoImpl;
import com.shofuku.accsystem.domain.financials.AccountEntryProfile;
import com.shofuku.accsystem.domain.financials.AccountingRules;
import com.shofuku.accsystem.domain.financials.JournalEntryProfile;
import com.shofuku.accsystem.domain.financials.Transaction;
import com.shofuku.accsystem.domain.financials.Vat;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.utils.DoubleConverter;
import com.shofuku.accsystem.utils.PurchaseOrderDetailHelper;
import com.shofuku.accsystem.utils.SASConstants;


public class AccountEntryManager extends BaseController{
	
	DoubleConverter dblConverter = new DoubleConverter();
	
	public AccountEntryProfile loadAccountEntryProfile(String accountCode) {
		AccountEntryProfile accountEntry =(AccountEntryProfile) accountEntryDao.load(accountCode, AccountEntryProfile.class);
		if(accountEntry==null){
			return null;
		}else {
			return accountEntry;
		}
	}
	
	public JournalEntryProfile loadJournalEntryProfile(String accountCode, Session session) {
		JournalEntryProfile journalEntry =(JournalEntryProfile) accountEntryDao.listByParameter(JournalEntryProfile.class, 
				"entryNo", accountCode, session);
		if(journalEntry==null){
			return null;
		}else {
			return journalEntry;
		}
	}
	
	public boolean addAccountEntryProfile(Object aepObject,Session session) {
			return accountEntryDao.save(aepObject,session);
	}
	
	public boolean addAccountEntryRule(Object accountingRule,Session session) {
			return accountEntryDao.persistingInsert(accountingRule,session);
	}
	
	public boolean updateAccountingRule(AccountingRules rules,Session session) {
		return accountEntryDao.persistingUpdate(rules, session);
	}
	
	public AccountingRules loadAccountingProfileRuleByAccountCode(String accountCode, Session session) {
		List list = accountEntryDao.listByParameter(AccountingRules.class,"accountCode", accountCode, session);
		if(list==null){
			return null;
		}else {
			if(list.size()>0) {
				return (AccountingRules)list.get(0);
			}
		}
		return null;
	}
	
	public List listAccountingProfileByParentName(String parentName, Session session) {
		List resultList = listAccountEntryProfileByParameterLike(AccountEntryProfile.class, "name", parentName, session); 
		List list = new ArrayList<>();
		if(resultList!=null){
			Iterator itr = resultList.iterator();
			while(itr.hasNext()){
				AccountEntryProfile parentProfile = (AccountEntryProfile) itr.next();
				List tempList = accountEntryDao.listByParameter(AccountEntryProfile.class,"parentCode", parentProfile.getAccountCode(), session);
				if(tempList!=null) {
					Iterator itr2 = tempList.iterator();
					while(itr2.hasNext()) {
						AccountEntryProfile aep = (AccountEntryProfile) itr2.next();
						aep.setParentCode(aep.getParentCode() +SASConstants.HYPHEN+ loadAccountEntryProfile(aep.getParentCode()).getName() );
						list.add(aep); 
					}
					
				}
			}
		}
			
		return list;
	}
	
	
	

	public boolean deleteAccountEntryProfile(Object object, Class clazz,Session session) {
		return accountEntryDao.deleteByParameter(object, clazz,session);
	}

	public boolean updateAccountEntryProfile(Object persistentObject,Session session) {
		return accountEntryDao.update(persistentObject,session);
	}

	public List listAccountEntryProfileByName(Class clazz,
			String parameter, String value,Session session) {
		return accountEntryDao.listByParameter(clazz, parameter, value,session);
	}
	
	public List listAlphabeticalAccountEntryProfileAscByParameter(Class clazz, String parameter,Session session) {
		return accountEntryDao.listAlphabeticalAscByParameter(clazz, parameter,session);
	}
	
	public List listAlphabeticalAccountEntryProfileChildrenAscByParameter(Session session) {
		return accountEntryDao.listAlphabeticalAccountEntryProfileChildrenAscByParameter(session);
	}
	public List listAccountEntryProfileByParameterLike(Class clazz, String parameter, String value,Session session) {
		return accountEntryDao.listByParameterLike(clazz, parameter, value,session);
	}
	public List listByParameter(Class clazz, String parameter, String value,Session session) {
		return accountEntryDao.listByParameter(clazz, parameter, value,session);
	}
	
	public String getAccountCode(String parentCode,Session session) {
		int latestAvailableChildSlot = accountEntryDao.getChildrenCountByParentCode(parentCode, session)+1;
		return String.valueOf(Integer.valueOf(parentCode)*10+latestAvailableChildSlot);
		
	}
	
	public List<AccountEntryProfile> getAccountEntriesFromList(List accountCodes,Session session){
		return accountEntryDao.listAccountEntriesFromList(accountCodes,session);
	}
	
	public Long getTotalRecordCount(Class clazz,Session session) {
		return accountEntryDao.recordCount(clazz, session);
	}

	public void generateInventoryEntries(List<Transaction> transactionList, PurchaseOrderDetailHelper poDetailsHelper) {
		double rawMatTotal=0;
		double finGoodTotal=0;
		double tradedItemTotal=0;
		Transaction transaction = new Transaction();
		AccountEntryProfile accountEntryProfile = new AccountEntryProfile();
		
		
		Iterator<PurchaseOrderDetails> itr  =poDetailsHelper.getPurchaseOrderDetailsList().iterator();
		while(itr.hasNext()) {
			PurchaseOrderDetails podetails = (PurchaseOrderDetails)itr.next();
			if(podetails.getItemType().equalsIgnoreCase(SASConstants.RAW_MATERIAL_ABBR2)) {
				rawMatTotal =rawMatTotal +podetails.getVattableAmount(); 
			}else if (podetails.getItemType().equalsIgnoreCase(SASConstants.FINISHED_GOODS_ABBR2)){
				finGoodTotal = finGoodTotal + podetails.getVattableAmount();
			}else if (podetails.getItemType().equalsIgnoreCase(SASConstants.TRADED_ITEM_ABBR2)){
				tradedItemTotal = tradedItemTotal + podetails.getVattableAmount();
			}
		}
		
		if (rawMatTotal>0) {
			transaction = new Transaction();
			accountEntryProfile = new AccountEntryProfile();
			accountEntryProfile = loadAccountEntryProfile(SASConstants.RAW_MATERIAL_INVENTORY_ACCOUNT_CODE);
			transaction.setAccountEntry(accountEntryProfile);
			transaction.setAmount(dblConverter.formatDoubleToCurrency(rawMatTotal));
			transactionList.add(transaction);
		}
		
		if (finGoodTotal>0) {
			transaction = new Transaction();
			accountEntryProfile = new AccountEntryProfile();
			accountEntryProfile = loadAccountEntryProfile(SASConstants.FINISHED_GOODS_INVENTORY_ACCOUNT_CODE);
			transaction.setAccountEntry(accountEntryProfile);
			transaction.setAmount(dblConverter.formatDoubleToCurrency(finGoodTotal));
			transactionList.add(transaction);
		}
		
		if(tradedItemTotal>0) {
			transaction = new Transaction();
			accountEntryProfile = new AccountEntryProfile();
			accountEntryProfile = loadAccountEntryProfile(SASConstants.TRADED_ITEM_INVENTORY_ACCOUNT_CODE);
			transaction.setAccountEntry(accountEntryProfile);
			transaction.setAmount(dblConverter.formatDoubleToCurrency(tradedItemTotal));
			transactionList.add(transaction);
		}
	}
	
}
