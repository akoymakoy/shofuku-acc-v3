package com.Junit.tests;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;

import com.shofuku.accsystem.controllers.CustomerManager;
import com.shofuku.accsystem.controllers.InventoryManager;
import com.shofuku.accsystem.controllers.SupplierManager;
import com.shofuku.accsystem.domain.customers.DeliveryReceipt;
import com.shofuku.accsystem.domain.inventory.FinishedGood;
import com.shofuku.accsystem.domain.inventory.Ingredient;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.domain.suppliers.ReceivingReport;
import com.shofuku.accsystem.utils.DateFormatHelper;
import com.shofuku.accsystem.utils.HibernateUtil;

public class InventoryTest {
	
	private InventoryManager invMgr = new InventoryManager();

//	@Test
//	public void insertFinishedProduct() {
//		
//		FinishedGood finGood = new FinishedGood();
//		
//		finGood.setDescription("GARLIC RICE");
//		finGood.setProductCode("fin003");
//		finGood.setStandardPricePerUnit(5.50);
//		finGood.setUnitOfMeasurement("cup");

//		Ingredient ingredient = new Ingredient("rawmaterial002","bawang",1.00,"pack",5,6);
		
//		Set<Ingredient> setIngredients = new HashSet<Ingredient>();
//		setIngredients.add(ingredient);
//		
//		invMgr.persistsIngredients(setIngredients);
//		finGood.setIngredients(setIngredients);
//		
//		assertNotNull(invMgr.addInventoryObject(finGood));
//	}
	/*
	@Test
	public void insertRawMat() {
		RawMaterial rawMaterial = new RawMaterial();
		rawMaterial.setItemCode("rawmaterial001");
		rawMaterial.setDescription("bawang");
		rawMaterial.setStandardPricePerUnit(1.00);
		rawMaterial.setUnitOfMeasurement("pack");
		
		assertNotNull(invMgr.addInventoryObject(rawMaterial));
		
	}
	*/
	
	
	@Test
	public void recalculateInventoryCount() {
		
		SupplierManager supMgr = new SupplierManager();
		DateFormatHelper dfh = new DateFormatHelper();
		Date startDate = dfh.parseStringToTime("2014-01-01");
		Date endDate = dfh.parseStringToTime("2014-01-05");
		
		List list =supMgr.getSupplierElementsBetweenDatesByParameter(startDate, endDate, ReceivingReport.class.getName(), "receivingReportDate", getSession());
		
		Iterator itr = list.iterator();
		
		while(itr.hasNext()) {
			ReceivingReport rr = (ReceivingReport) itr.next();;
			Iterator poItr = rr.getPurchaseOrderDetails().iterator();
			while(poItr.hasNext()) {
				PurchaseOrderDetails poDetails = (PurchaseOrderDetails)poItr.next();
				//PART WHERE WE UPDATE EACH INVENTORY (POSITIVE +)
				System.out.println(poDetails.getDescription() + ": "+poDetails.getQuantity());
			}
			
		}
		
		
		CustomerManager cusMgr = new CustomerManager();
		startDate = dfh.parseStringToTime("2014-01-01");
		endDate = dfh.parseStringToTime("2014-01-05");
		
		list =cusMgr.getCustomerElementsBetweenDatesByParameter(startDate, endDate, DeliveryReceipt.class.getName(), "deliveryReceiptDate", getSession());
		
		itr = list.iterator();
		
		while(itr.hasNext()) {
			DeliveryReceipt dr = (DeliveryReceipt) itr.next();;
			Iterator poItr = dr.getPurchaseOrderDetails().iterator();
			while(poItr.hasNext()) {
				PurchaseOrderDetails poDetails = (PurchaseOrderDetails)poItr.next();
				//PART WHERE WE UPDATE EACH INVENTORY (POSITIVE +)
				System.out.println(poDetails.getDescription() + ": "+ poDetails.getQuantity()*-1);
			}
			
		}
		
		
		
		assertNotNull(list);
	}
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
