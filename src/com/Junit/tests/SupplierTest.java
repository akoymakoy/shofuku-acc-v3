package com.Junit.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.junit.Test;

import com.shofuku.accsystem.controllers.SupplierManager;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.domain.lookups.ExpenseClassification;
import com.shofuku.accsystem.domain.suppliers.Supplier;
import com.shofuku.accsystem.domain.suppliers.SupplierPurchaseOrder;
import com.shofuku.accsystem.utils.DateFormatHelper;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.POIUtil;

public class SupplierTest {
	
	private SupplierPurchaseOrder pos = new SupplierPurchaseOrder();
	private SupplierManager sm = new SupplierManager();
	@SuppressWarnings("rawtypes")
	@Test
	public void insertSupplier() {
//		Long x = sm.recordCount(ExpenseClassification.class);
//		String output = Long.toString(x);
//		while (output.length() < 6) output = "0" + output;
//		System.out.println(output);
		DecimalFormat df = new DecimalFormat("###,###,###.00");
		String x = "1,000";
		
		double value=0.0;
		try {
			value = (Long)(df.parse(x));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		assertNotNull(value);
//		double x = 22221.001;
//		DecimalFormat df = new DecimalFormat("##.00");
//		x = Double.valueOf(df.format(x));
//		System.out.println(df.format(x));
//		Supplier supplier = new Supplier();
//		supplier.setSupplierId("SB1234567");
//		supplier.setSupplierName("ASUKA TRADER INC");
//		supplier.setSupplierStatus(" VAT-EXEMPT");
//		supplier.setContactName("JUAN DELA CRUZ");
//		supplier.setContactTitle("MARKETING MANAGER");
//		supplier.setCompanyAddress("123 ASUKA ST., MAKATI CITY");
//		supplier.setPhoneNumber("(62) 911-11-11");
//		supplier.setFaxNumber("(62) 911-11-11");
//		supplier.setMobileNumber("(0917) 911-11-11");
//		supplier.setEmailAddress("asukatraderinc@/yahoo.com");
//		supplier.setWebsite("www.asukatraderinc.com");
//		int count = service.insertSupplier(supplier);
//		int count = service.updateSupplier(supplier);
//		assertTrue(sm.addSupplier(supplier));
//		SupplierPurchaseOrder po = new SupplierPurchaseOrder();
//		DateFormatHelper dfh = new DateFormatHelper();
//		po.setSupplierPurchaseOrderId("Test");
//		po.setSupplier(supplier);
//		po.setPurchaseOrderDate(dfh.parseStringToTimestamp("Apr-30-86"));
//		for (int x =01;x<111;x++)
//		dfh.parseStringToTimestamp("May-30-"+x);
//		assertTrue(sm.addSupplierPurchaseOrder(po));
//		SupplierManager sm = new SupplierManager();
//		String parameter = "supplierId";
//		String name = "sMarks2";
//		String id = "0992";
//		supplier.setSupplierId(id);
//		supplier.setSupplierName(name);
//		
//		List list = sm.listSuppliersByParameter(supplier.getClass(),parameter,id);
//		
//		assertTrue(list.size()>0);
//		System.out.println(list.size());
//		assertTrue(sm.deleteByParameter(supplier.getSupplierId(), supplier.getClass()));
//		assertTrue(sm.deleteByParameter("SB1234567",supplier.getClass()));

//		SupplierPurchaseOrder spo = new SupplierPurchaseOrder();
//		Supplier supplier = new Supplier();
//		supplier.setSupplierId("0219-2TestSup");
//		supplier.setSupplierName("Makoy");
//		spo.setSupplier(supplier);
//		spo.setSupplierPurchaseOrderId("0219Test2");
//		
//		PurchaseOrderDetails podetails = new PurchaseOrderDetails("0219Test", "test0219", 3, "box", 3, 10.50);
//		Set<PurchaseOrderDetails> poDetailsSet = new HashSet<PurchaseOrderDetails>();
//		poDetailsSet.add(podetails);
//
//		spo.setPurchaseOrderDetails(poDetailsSet);
//		
//		sm.addSupplierObject(supplier);
//		assertTrue(sm.addSupplierObject(spo));
//		List listNgSuppliers = sm.listAlphabeticalAscByParameter(supplier.getClass(), "supplierName");
		

//		List listNgSuppliers =sm.listSuppliersByParameterLike(spo.getClass(), "supplier.supplierName", "ubo");
//		System.out.println(listNgSuppliers.size());
//		assertNotNull(listNgSuppliers);

		
		
		
//		
//		List spoResult = new ArrayList<SupplierPurchaseOrder>();
//		for(int x = 0; x < listNgSuppliers.size();x++) {
//			Supplier sup = (Supplier) listNgSuppliers.get(x);
//			spoResult.addAll(sm.listSuppliersByParameter(spo.getClass(), "supplier.supplierId", sup.getSupplierId() ));
//		}
//		
//		for(int x = 0; x<= spoResult.size();x++)
//			System.out.println(((SupplierPurchaseOrder)spoResult.get(x)).getSupplierPurchaseOrderId());
//		assertNotNull(listNgSuppliers);
		
		/*
		DateFormatHelper dfh = new DateFormatHelper();
		String dateFromJsp = "1983-12-08 00:00:00";
		Timestamp ts= dfh.parseStringToTimestamp(dateFromJsp);
		
		List spoResult = sm.getSupplierElementsByDate(dfh.parseStringToTime(dateFromJsp), pos.getClass().getName(),"purchaseOrderDate");
		
		for(int x = 0; x< spoResult.size();x++)
			System.out.println(((SupplierPurchaseOrder)spoResult.get(x)).getSupplierPurchaseOrderId());
		assertNotNull(spoResult);
		
		Supplier supplier = new Supplier();
		List list = sm.listAlphabeticalAscByParameter(supplier.getClass(), "supplierName");
		SupplierPurchaseOrder spo = new SupplierPurchaseOrder();
		sm.listByName(spo.getClass(), "supplier.supplierName", "sub");
		supplier.setSupplierId("Test0992");
		List list = sm.listSuppliersByParameter(Supplier.class, "supplierId", supplier.getSupplierId());
		DateFormatHelper dfh = new DateFormatHelper();
		Date startDate = dfh.parseStringToTime("2012-02-01");
		Date endDate = dfh.parseStringToTime("2012-02-04");
		List list = sm.getSupplierElementsBetweenDatesByParameter(startDate, endDate, SupplierPurchaseOrder.class.getName(), "purchaseOrderDate");
		
		Set<PurchaseOrderDetails> purchaseOrderDetails = spo.getPurchaseOrderDetails();
		
		Iterator iterator = purchaseOrderDetails.iterator();
		while (iterator.hasNext()) {
			PurchaseOrderDetails poDetails = (PurchaseOrderDetails) iterator.next();
			System.out.println(poDetails.getItemCode());
		}*/
//		
//		SupplierPurchaseOrder spo = new SupplierPurchaseOrder();
//		spo.setSupplierPurchaseOrderId("TestPO5");
//		spo.setSupplier(new Supplier());
//		spo.getSupplier().setSupplierId("osigenanga");
//		List list = sm.getPODetails(PurchaseOrderDetails.class,"TestPO");
//		
//		
//		assertTrue(sm.deleteSupplierByParameter(spo.getSupplierPurchaseOrderId(), SupplierPurchaseOrder.class));
	}

	/*
	@Test
	public void getSupplierById() {
		assertNotNull(service.getSupplierById(1));
	}
	
	 @Test
	public void getSuppliersByDateRange() {
		java.util.Date today =
		        new java.util.Date();

		java.sql.Date date1 =
		        new java.sql.Date(today.getTime());
		java.sql.Date date2 =
		        new java.sql.Date(today.getTime());
		
		date1.setDate(26);
		date2.setDate(28);
		assertNotNull(service.getSuppliersByDateRange(date1,date2));
		
	}
	/*
	@Test
	public void insertSupplier() {
		int ctr=51;
		supplier.setSupplierId("SB1234567");
		supplier.setSupplierName("ASUKA TRADER INC");
		supplier.setSupplierStatus(" VAT-EXEMPT");
		supplier.setContactName("JUAN DELA CRUZ");
		supplier.setContactTitle("MARKETING MANAGER");
		supplier.setCompanyAddress("123 ASUKA ST., MAKATI CITY");
		supplier.setPhoneNumber("(62) 911-11-11");
		supplier.setFaxNumber("(62) 911-11-11");
		supplier.setMobileNumber("(0917) 911-11-11");
		supplier.setEmailAddress("asukatraderinc@yahoo.com");
		supplier.setWebsite("www.asukatraderinc.com");
		
		pos.setSupplierPurchaseOrderId(ctr);
		pos.setSupplier(supplier);
		POIUtil util = new POIUtil();
		
		String fileName = "C:" + File.separator + "Users" + File.separator
				+ "miraflor" + File.separator + "Documents" + File.separator
				+ "temp" + File.separator + "SHOFUKU" + File.separator
				+ "documents" + File.separator + "ordering form.xls";
		String orderType = "WET GOODS";
		Set<PurchaseOrderDetails> posSet = new HashSet<PurchaseOrderDetails>();
		
		posSet = util.readOrderingForm(fileName,orderType);
//		pos.setPurchaseOrderDetails(posSet);
		PurchaseOrder po = new PurchaseOrder();
		po.setPurchaseOrderDetails(posSet);

		ReceivingReport rcRep = new ReceivingReport();
		rcRep.setPurchaseOrderDetails(posSet);
		rcRep.setSupplierPurchaseOrder(pos);
		rcRep.setReceivingReportNo(100);
//		List<ReceivingReport> receivingReport = null;
		SupplierManager sm = new SupplierManager();
//		int count = service.updateSupplier(supplier);
//		receivingReport = sm.list(rcRep);
//				System.out.println(((ReceivingReport)(receivingReport.get(0))).getSupplierPurchaseOrder().getSupplier().getContactName());
		assertNotNull(sm.add(rcRep));
//		assertNotNull(sm.add(pos));
		
//		assertTrue(count > 0);
	}
	*/
//	@Test
//	private void generateSupplierProfileSummary() {
//		POIUtil poiHelper = new POIUtil();
//		
//	}
}
