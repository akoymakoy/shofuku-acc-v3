package com.Junit.tests;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.shofuku.accsystem.controllers.AccountEntryManager;
import com.shofuku.accsystem.domain.financials.AccountEntryProfile;
import com.shofuku.accsystem.utils.HibernateUtil;

@SuppressWarnings("rawtypes")
public class AccountEntryTest {
	AccountEntryManager aepMgr = new AccountEntryManager();

//	@Test
//	public void insertAccountEntry() {
//		AccountEntryProfile aep = new AccountEntryProfile();
//
//		aep.setAccountCode(aepMgr.getAccountCode(1001, getSession()));
//		aep.setClassification("A");
//		aep.setIsACtive("ACTIVE");
//		aep.setName("ASSET");
//		aep.setParentCode(1001);
//		aep.setReportAction("DR");
//		aep.setReportType("FS");
//		
//		assertTrue(aepMgr.addAccountEntryProfile(aep, getSession()));
//	}
	
//	@Test
//	public void editAccountEntry() {
//		AccountEntryProfile aep = aepMgr.loadAccountEntryProfile(10011);
//		aep.setIsACtive("INACTIVE");
//		assertTrue(aepMgr.updateAccountEntryProfile(aep, getSession()));
//	}
	
	@Test
	public void loadAccountEntry() {
//		assertNotNull(aepMgr.loadAccountEntryProfile(10011));
//		List list = aepMgr.listAlphabeticalAccountEntryProfileAscByParameter(AccountEntryProfile.class,"accountCode", getSession());
//		List accountCodes = Arrays.asList("100001","10012","10013");
		
//		List list = aepMgr.getAccountEntriesFromList(accountCodes, getSession());
		List accountProfileCodeList = aepMgr.listAlphabeticalAccountEntryProfileChildrenAscByParameter(getSession());
		assertNotNull(accountProfileCodeList);
	}
	
//	@Test
//	public void deleteAccountEntry() {
//		AccountEntryProfile aep = new AccountEntryProfile();
//		aep.setAccountCode(10011);
//		assertTrue(aepMgr.deleteAccountEntryProfile(aep.getAccountCode(), AccountEntryProfile.class, getSession()));
//	}
	
	
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
