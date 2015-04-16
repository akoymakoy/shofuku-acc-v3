package com.Junit.tests;

//import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import com.shofuku.accsystem.controllers.AccountEntryManager;
import com.shofuku.accsystem.controllers.FinancialsManager;
import com.shofuku.accsystem.utils.HibernateUtil;

@SuppressWarnings("rawtypes")
public class FinancialsTest {
	FinancialsManager fMgr = new FinancialsManager();
//
//	@Test
//	public void loadAccountEntry() {
//		List supplierIdList = new ArrayList();
//		supplierIdList.add("SA12");
//		supplierIdList.add("SA13");
//		supplierIdList.add("SA14");
//		List invoices = fMgr.getSupplierInvoiceFromSupplierIDList(supplierIdList , getSession());
//		assertNotNull(invoices);
//	}
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
