package com.shofuku.accsystem.controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shofuku.accsystem.domain.customers.Customer;
import com.shofuku.accsystem.domain.customers.CustomerPurchaseOrder;
import com.shofuku.accsystem.domain.customers.CustomerSalesInvoice;
import com.shofuku.accsystem.domain.customers.DeliveryReceipt;
import com.shofuku.accsystem.domain.disbursements.CashPayment;
import com.shofuku.accsystem.domain.disbursements.CheckPayments;
import com.shofuku.accsystem.domain.disbursements.PettyCash;
import com.shofuku.accsystem.domain.financials.AccountEntryProfile;
import com.shofuku.accsystem.domain.inventory.FPTS;
import com.shofuku.accsystem.domain.inventory.FinishedGood;
import com.shofuku.accsystem.domain.inventory.OfficeSupplies;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.domain.inventory.RawMaterial;
import com.shofuku.accsystem.domain.inventory.RequisitionForm;
import com.shofuku.accsystem.domain.inventory.ReturnSlip;
import com.shofuku.accsystem.domain.inventory.TradedItem;
import com.shofuku.accsystem.domain.inventory.Utensils;
import com.shofuku.accsystem.domain.receipts.CashCheckReceipts;
import com.shofuku.accsystem.domain.receipts.OROthers;
import com.shofuku.accsystem.domain.receipts.ORSales;
import com.shofuku.accsystem.domain.suppliers.ReceivingReport;
import com.shofuku.accsystem.domain.suppliers.Supplier;
import com.shofuku.accsystem.domain.suppliers.SupplierInvoice;
import com.shofuku.accsystem.domain.suppliers.SupplierPurchaseOrder;
import com.shofuku.accsystem.utils.DateFormatHelper;
import com.shofuku.accsystem.utils.ExportSearchResultsHelper;
import com.shofuku.accsystem.utils.POIUtil;

@SuppressWarnings("rawtypes")
public class ReportAndSummaryManager extends BaseController{

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	Map<String,Object> actionSession;
	
	
	DateFormatHelper dfh = new DateFormatHelper();
	
	public ReportAndSummaryManager(Map<String, Object> actionSession) {
		this.actionSession = actionSession;
	}
	
	public ReportAndSummaryManager() {
	}

	POIUtil poiHelper;
	
	private POIUtil initializePoiHelper() {
		if(poiHelper==null) {
			poiHelper = new POIUtil(actionSession);
			return poiHelper;
		}else {
			return poiHelper;
		}
	}

	
	public InputStream generateSoaSummary(ServletContext servletContext,
			String dateFrom, String dateTo, String subModule,
			List customerList, Session session) {
		try {
			DateFormatHelper dfh = new DateFormatHelper();
			Date startDate = dfh.parseStringToTime(dateFrom);
			Date endDate = dfh.parseStringToTime(dateTo);
			if(dateTo!=null && !dateTo.equalsIgnoreCase(""))
				initializePoiHelper().setMaxDate(endDate.toString());
			if(dateFrom!=null && !dateFrom.equalsIgnoreCase(""))
				initializePoiHelper().setMinDate(startDate.toString());
			List list = baseHibernateDao.getBetweenDates(startDate, endDate, CustomerSalesInvoice.class.getName(), "customerInvoiceDate",session,"soldTo");
			list=filterCustomerInvoiceByCustomerNumber(list,customerList);
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().generateSummary(servletContext, subModule, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateSummary" + ioex.toString());
			ioex.printStackTrace();
		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}

		return null;
	}
	public InputStream generateSummary(ServletContext servletContext,
			String dateFrom, String dateTo, String subModule,
			List parameterList, String isFormatReport, Session session) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			
			if(dateTo!=null && !dateTo.equalsIgnoreCase("")){
				Date endDate = dfh.parseStringToTime(dateTo);
				initializePoiHelper().setMaxDate(endDate.toString());
				
			}
			if(dateFrom!=null && !dateFrom.equalsIgnoreCase("")){
				Date startDate = dfh.parseStringToTime(dateFrom);
				initializePoiHelper().setMinDate(startDate.toString());
				
			}
			
			List summarizedInvoice= null;
			if (subModule.equalsIgnoreCase("ItemSoldToCustomers")){
				List list = baseHibernateDao.getCustomerSalesInvoiceByCustomers(dateFrom, dateTo,parameterList,session);
				list = filterCustomerInvoiceByCustomerNumber(list,parameterList);
				summarizedInvoice = summarizeItemsFromInvoice(list);
				wb = initializePoiHelper().generateSummary(servletContext, subModule, summarizedInvoice);
			}else if(subModule.equalsIgnoreCase("ItemPurchasedFromSupplier")) {
				List list = baseHibernateDao.getReceivingReportBySupplier(dateFrom, dateTo,parameterList,session);
				list = filterReceivingReportBySupplierId(list,parameterList);
				summarizedInvoice = summarizeItemsFromReceivingReport(list);
				wb = initializePoiHelper().generateSummary(servletContext, subModule, summarizedInvoice);
			}else {
				List list = getDataListByDateRange(dateFrom, dateTo, subModule, session);
				list = filterByParameterList(list,parameterList);
				if(isFormatReport.equalsIgnoreCase("true")) {
					ExportSearchResultsHelper exporter = new ExportSearchResultsHelper(actionSession);
					exporter.setSearchResults(list);
					exporter.setSearchType(subModule);
					try {
						return exporter.generateExportedXls();
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}else {
					wb = initializePoiHelper().generateSummary(servletContext, subModule, list);
				}
				
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateSummary" + ioex.toString());
			ioex.printStackTrace();
		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}

		return null;
	}
	

	public InputStream printReceipt(Map receiptMap, String subModule,ServletContext servletContext ) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().printReceipts(subModule, receiptMap,servletContext);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateSummary" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
	}
	
	public InputStream printCheckPayments(CheckPayments chp, String subModule,ServletContext servletContext ) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().printCheckPayments(subModule, chp,servletContext);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("print check payments" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
	}
	
	
	public InputStream printCustomerInvoice(CustomerSalesInvoice csi, String subModule,ServletContext servletContext ) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().printCustomerInvoice(subModule, csi,servletContext);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("print customer invoice" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
	}
	
	public InputStream printSupplierPurchaseOrder(SupplierPurchaseOrder spo, String subModule,ServletContext servletContext ) {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().printSupplierPurchaseOrderInExcel(subModule, spo,servletContext);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("print supplier purchase order" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
	}
	
	public InputStream generateSummary(ServletContext servletContext,
			String dateFrom, String dateTo, String subModule,String referenceParameter,Session session) {
		
		try {
			List list = baseHibernateDao.listByParameterLike(PettyCash.class,"referenceNo",referenceParameter,session);
			((PettyCash)(list.get(0))).setByRef(true);
			HSSFWorkbook wb = new HSSFWorkbook();
			if(dateTo!=null && !dateTo.equalsIgnoreCase("")){
				Date endDate = dfh.parseStringToTime(dateTo);
				initializePoiHelper().setMaxDate(endDate.toString());
				
			}
			if(dateFrom!=null && !dateFrom.equalsIgnoreCase("")){
				Date startDate = dfh.parseStringToTime(dateFrom);
				initializePoiHelper().setMinDate(startDate.toString());
				
			}
			wb = initializePoiHelper().generateSummary(servletContext, subModule, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateSummary" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
		
	}
	
	
	public InputStream generateSummary(ServletContext servletContext,
			String dateFrom, String dateTo, String subModule,Session session) {

		try {
			List list = getDataList(dateFrom, dateTo, subModule,session);
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().generateSummary(servletContext, subModule, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateSummary" + ioex.toString());
			ioex.printStackTrace();
		}

		return null;
	}
	
	/*public InputStream generateInventorySummaryWithActiveStatus(ServletContext servletContext,
			String dateFrom, String dateTo, String subModule,String searchByStatus,Session session) {

		try {
			List list = new ArrayList();
			
			list= inventoryDao.listInventoryItemsWithActiveStatus(subModule,searchByStatus,session);
			
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = initializePoiHelper().generateSummary(servletContext, subModule, list);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			wb.write(baos);
			return new ByteArrayInputStream(baos.toByteArray());
		} catch (IOException ioex) {
			logger.debug("generateInventorySummaryWithActiveStatus" + ioex.toString());
			ioex.printStackTrace();
		}catch (NullPointerException npe) {
			logger.debug("generateInventorySummaryWithActiveStatus" + npe.toString());
			npe.printStackTrace();
		}

		return null;
	}*/
	
	private List setReturnSlipsForEachObj(List list,Session session) {
		InventoryManager invManager = (InventoryManager) actionSession.get("inventoryManager");
		List revisedList= new ArrayList();
		Object obj = (Object)list.get(0);
		if(obj instanceof ReceivingReport){
			List<ReceivingReport> tempList= list;
			for(ReceivingReport rr: tempList) {
				rr.setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						rr.getReceivingReportNo(), session));
				revisedList.add(rr);
			}
		}else if(obj instanceof DeliveryReceipt){
			List<DeliveryReceipt> tempList= list;
			for(DeliveryReceipt dr: tempList) {
				dr.setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						dr.getDeliveryReceiptNo(), session));
				revisedList.add(dr);
			}
		}else if(obj instanceof FPTS){
			List<FPTS> tempList= list;
			for(FPTS fpts: tempList) {
				fpts.setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						fpts.getFptsNo(), session));
				revisedList.add(fpts);
			}
		}else if(obj instanceof RequisitionForm){
			List<RequisitionForm> tempList= list;
			for(RequisitionForm rf: tempList) {
				rf.setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						rf.getRequisitionNo(), session));
				revisedList.add(rf);
			}
		}else if(obj instanceof SupplierInvoice){
			List<SupplierInvoice> tempList= list;
			for(SupplierInvoice si: tempList) {
				si.getReceivingReport().setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						si.getReceivingReport().getReceivingReportNo(), session));
				revisedList.add(si);
			}
		}else if(obj instanceof CustomerSalesInvoice){
			List<CustomerSalesInvoice> tempList= list;
			for(CustomerSalesInvoice ci: tempList) {
				ci.getDeliveryReceipt().setReturnSlipList(invManager.listInventoryByParameter(
						ReturnSlip.class, "returnSlipReferenceOrderNo",
						ci.getDeliveryReceipt().getDeliveryReceiptNo(), session));
				revisedList.add(ci);
			}
		}
		return revisedList;

	} 
	private List getDataList(String dateFrom, String dateTo, String subModule,Session session) {
		
		List list = new ArrayList();
		baseHibernateDao.setUser(super.getUser());
		
		if (subModule.equalsIgnoreCase("Supplier")) {
			list = baseHibernateDao.listAlphabeticalAscByParameter(Supplier.class,"supplierName",session);
		}else if(subModule.equalsIgnoreCase("Customer")) {
			list = baseHibernateDao.listAlphabeticalAscByParameter(Customer.class,"customerName",session);
		}else if(subModule.equalsIgnoreCase("RawMaterials")) {
			list = baseHibernateDao.listInventoryAlphabeticalAscByParameter(RawMaterial.class,"itemCode",session);
		}else if(subModule.equalsIgnoreCase("TradedItems")) {
			list = baseHibernateDao.listInventoryAlphabeticalAscByParameter(TradedItem.class,"itemCode",session);
		}else if(subModule.equalsIgnoreCase("Utensils")) {
			list = baseHibernateDao.listInventoryAlphabeticalAscByParameter(Utensils.class,"itemCode",session);
		}else if(subModule.equalsIgnoreCase("OfficeSupplies")) {
			list = baseHibernateDao.listInventoryAlphabeticalAscByParameter(OfficeSupplies.class,"itemCode",session);
		}else if(subModule.equalsIgnoreCase("FinishedGoods")) {
			list = baseHibernateDao.listInventoryAlphabeticalAscByParameter(FinishedGood.class,"productCode",session);
		}else if(subModule.equalsIgnoreCase("AccountEntryProfile")) {
			list = baseHibernateDao.listAlphabeticalAscByParameter(AccountEntryProfile.class,"accountCode",session);
		}else {
			list=getDataListByDateRange(dateFrom,dateTo,subModule,session);
			
		}
		return list;
	}
	
	private List getDataListByDateRange(String dateFrom, String dateTo,
			String subModule, Session session) {
		
		List list = new ArrayList();
		
		DateFormatHelper dfh = new DateFormatHelper();
		Date startDate = dfh.parseStringToTime(dateFrom);
		Date endDate = dfh.parseStringToTime(dateTo);
		if(dateTo!=null && !dateTo.equalsIgnoreCase(""))
			initializePoiHelper().setMaxDate(endDate.toString());
		if(dateFrom!=null && !dateFrom.equalsIgnoreCase(""))
			initializePoiHelper().setMinDate(startDate.toString());
		
		if(subModule.equalsIgnoreCase("SupplierPurchaseOrder")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, SupplierPurchaseOrder.class.getName(), "purchaseOrderDate",session,"purchaseOrderDate");
		}else if(subModule.equalsIgnoreCase("ReceivingReport")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, ReceivingReport.class.getName(), "receivingReportDate",session,"receivingReportDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("SupplierInvoice")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, SupplierInvoice.class.getName(), "supplierInvoiceDate",session,"supplierInvoiceDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("CustomerPurchaseOrder")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CustomerPurchaseOrder.class.getName(), "purchaseOrderDate",session,"purchaseOrderDate");
		}else if(subModule.equalsIgnoreCase("DeliveryReceipt")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, DeliveryReceipt.class.getName(), "deliveryReceiptDate",session,"deliveryReceiptDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("CustomerSalesInvoice")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CustomerSalesInvoice.class.getName(), "customerInvoiceDate",session,"customerInvoiceDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("StatementOfAccount")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CustomerSalesInvoice.class.getName(), "customerInvoiceDate",session,"soldTo");
		}else if(subModule.equalsIgnoreCase("PettyCash")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, PettyCash.class.getName(), "pcVoucherDate",session,"pcVoucherDate");
		}else if(subModule.equalsIgnoreCase("CashPayment")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CashPayment.class.getName(), "cashVoucherDate",session,"cashVoucherDate");
		}else if(subModule.equalsIgnoreCase("CheckPayment")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CheckPayments.class.getName(), "checkVoucherDate",session,"checkVoucherDate");
		}else if(subModule.equalsIgnoreCase("InvoiceCheckVoucher")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CheckPayments.class.getName(), "checkVoucherDate",session,"checkVoucherDate");
		}else if(subModule.equalsIgnoreCase("CashReceipts")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, CashCheckReceipts.class.getName(), "cashReceiptDate",session,"cashReceiptDate");
		}else if(subModule.equalsIgnoreCase("ORSales")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, ORSales.class.getName(), "orDate",session,"orDate");
		}else if(subModule.equalsIgnoreCase("OROthers")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, OROthers.class.getName(), "orDate",session,"orDate");
		}else if(subModule.equalsIgnoreCase("FinishedProductTransferSlip")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, FPTS.class.getName(), "transactionDate",session,"transactionDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("OrderRequisition")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, RequisitionForm.class.getName(), "requisitionDate",session,"requisitionDate");
			list = setReturnSlipsForEachObj(list, session);
		}else if(subModule.equalsIgnoreCase("ReturnSlip")) {
			list = baseHibernateDao.getBetweenDates(startDate, endDate, ReturnSlip.class.getName(), "returnDate",session,"returnDate");
		}
		
		return list;
	}
	
	private List filterByParameterList(List resultList, List parameterList) {
		List finalList = new ArrayList();
		Iterator itr = resultList.iterator();
		
		while(itr.hasNext()){
			Object object = itr.next();
			Iterator innerItr = parameterList.iterator();
			
			if(object instanceof CustomerSalesInvoice){
				CustomerSalesInvoice invoice = ((CustomerSalesInvoice) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo().equalsIgnoreCase(id)){
						finalList.add(invoice);
						break;
					}
				}
			}else if( object instanceof DeliveryReceipt) {
				DeliveryReceipt dr = ((DeliveryReceipt) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(dr.getCustomerPurchaseOrder().getCustomer().getCustomerNo().equalsIgnoreCase(id)){
						finalList.add(dr);
						break;
					}
				}
			}else if( object instanceof CustomerPurchaseOrder) {
				CustomerPurchaseOrder po= ((CustomerPurchaseOrder) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(po.getCustomer().getCustomerNo().equalsIgnoreCase(id)){
						finalList.add(po);
						break;
					}
				}
			}else if( object instanceof SupplierInvoice) {
				SupplierInvoice invoice = ((SupplierInvoice) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(invoice.getReceivingReport().getSupplierPurchaseOrder().getSupplier().getSupplierId().equalsIgnoreCase(id)){
						finalList.add(invoice);
						break;
					}
				}
			}else if( object instanceof ReceivingReport) {
				ReceivingReport rr = ((ReceivingReport) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId().equalsIgnoreCase(id)){
						finalList.add(rr);
						break;
					}
				}
			}else if( object instanceof SupplierPurchaseOrder) {
				SupplierPurchaseOrder po = ((SupplierPurchaseOrder) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(po.getSupplier().getSupplierId().equalsIgnoreCase(id)){
						finalList.add(po);
						break;
					}
				}
			}else if( object instanceof PettyCash) {
				PettyCash pc = ((PettyCash) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(pc.getPayee().equalsIgnoreCase(id)){
						finalList.add(pc);
						break;
					}
				}
			}else if( object instanceof CashPayment) {
				CashPayment cp = ((CashPayment) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(cp.getPayee().equalsIgnoreCase(id)){
						finalList.add(cp);
						break;
					}
				}
			}else if( object instanceof CheckPayments) {
				CheckPayments chp = ((CheckPayments) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(chp.getPayee().equalsIgnoreCase(id)){
						finalList.add(chp);
						break;
					}
				}
			}else if( object instanceof ORSales) {
				ORSales orSales = ((ORSales) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(orSales.getReceivedFrom().equalsIgnoreCase(id)){
						finalList.add(orSales);
						break;
					}
				}
			}else if( object instanceof OROthers) {
				OROthers orOthers = ((OROthers) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(orOthers.getReceivedFrom().equalsIgnoreCase(id)){
						finalList.add(orOthers);
						break;
					}
				}
			}else if( object instanceof CashCheckReceipts) {
				CashCheckReceipts ccr = ((CashCheckReceipts) object);
				while(innerItr.hasNext()){
					String id = (String)innerItr.next();
					if(ccr.getCashReceiptNo().equalsIgnoreCase(id)){
						finalList.add(ccr);
						break;
					}
				}
			}else {
				finalList=resultList;
			}
		}
		return finalList;
	}
	private List filterCustomerInvoiceByCustomerNumber(List resultList, List customerList) {
		List finalList = new ArrayList();
		Iterator itr = resultList.iterator();
		while(itr.hasNext()){
			Iterator innerItr = customerList.iterator();
			
			CustomerSalesInvoice invoice = ((CustomerSalesInvoice)itr.next());
			while(innerItr.hasNext()){
				String id = (String)innerItr.next();
				if(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo().equalsIgnoreCase(id)){
					finalList.add(invoice);
					break;
				}
			}
		}
		return finalList;
	}
	
	private List filterReceivingReportBySupplierId(List resultList, List supplierList) {
		List finalList = new ArrayList();
		Iterator itr = resultList.iterator();
		while(itr.hasNext()){
			Iterator innerItr = supplierList.iterator();
			ReceivingReport rr = ((ReceivingReport)itr.next());
			while(innerItr.hasNext()){
				String id = (String)innerItr.next();
				if(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId().equalsIgnoreCase(id)){
					finalList.add(rr);
					break;
				}
			}
		}
		return finalList;
	}
	
	
	private List summarizeItemsFromInvoice(List list) {
		// TODO Auto-generated method stub
		
		Map<String, CustomerSalesInvoice> poGroupedByCustomer = new HashMap<String, CustomerSalesInvoice>();

		Iterator listItr = list.iterator();
		while(listItr.hasNext()){
			CustomerSalesInvoice invoice = (CustomerSalesInvoice) listItr.next();

			if(poGroupedByCustomer.containsKey(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo())){
				CustomerSalesInvoice tempCPO = (CustomerSalesInvoice ) poGroupedByCustomer.get(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo());
				
				Map originalMap = convertPurchaseOrderDetailsToMap(tempCPO.getPurchaseOrderDetails());
				Map<String, PurchaseOrderDetails> inboundMap = convertPurchaseOrderDetailsToMap(invoice.getPurchaseOrderDetails());
				
				for(Map.Entry<String, PurchaseOrderDetails> entry : inboundMap.entrySet()){
					  String key = entry.getKey();
					  PurchaseOrderDetails podetails= (PurchaseOrderDetails)entry.getValue();
					    
					if(originalMap.containsKey(key)){
						PurchaseOrderDetails tempDetails =  (PurchaseOrderDetails) originalMap.get(key);
						tempDetails.setQuantity(tempDetails.getQuantity() + podetails.getQuantity());
						tempDetails.setAmount(tempDetails.getAmount()+podetails.getAmount());
						originalMap.put(key, tempDetails);
					}else{
						originalMap.put(key, podetails);
					}
				}
				tempCPO.setPurchaseOrderDetails(convertMapToPODetails(originalMap));
			poGroupedByCustomer.put(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo(), tempCPO);
			}else{
				poGroupedByCustomer.put(invoice.getDeliveryReceipt().getCustomerPurchaseOrder().getCustomer().getCustomerNo(), invoice);
			}
		}
		List summarizedCPO = new ArrayList();
		
		for(Map.Entry<String, CustomerSalesInvoice> entry : poGroupedByCustomer.entrySet()){
			  String key = entry.getKey();
			  CustomerSalesInvoice invoice= (CustomerSalesInvoice)entry.getValue();
			  summarizedCPO.add(invoice);
		}
		
		return summarizedCPO;
	}
	
	private List summarizeItemsFromReceivingReport(List list) {
		// TODO Auto-generated method stub
		
		Map<String, ReceivingReport> poGroupedBySupplier = new HashMap<String, ReceivingReport>();

		Iterator listItr = list.iterator();
		while(listItr.hasNext()){
			ReceivingReport rr = (ReceivingReport) listItr.next();

			if(poGroupedBySupplier.containsKey(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId())){
				ReceivingReport tempSPO = (ReceivingReport) poGroupedBySupplier.get(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId());
				
				Map originalMap = convertPurchaseOrderDetailsToMap(tempSPO.getPurchaseOrderDetails());
				Map<String, PurchaseOrderDetails> inboundMap = convertPurchaseOrderDetailsToMap(rr.getPurchaseOrderDetails());
				
				for(Map.Entry<String, PurchaseOrderDetails> entry : inboundMap.entrySet()){
					  String key = entry.getKey();
					  PurchaseOrderDetails podetails= (PurchaseOrderDetails)entry.getValue();
					    
					if(originalMap.containsKey(key)){
						PurchaseOrderDetails tempDetails =  (PurchaseOrderDetails) originalMap.get(key);
						tempDetails.setQuantity(tempDetails.getQuantity() + podetails.getQuantity());
						tempDetails.setAmount(tempDetails.getAmount()+podetails.getAmount());
						originalMap.put(key, tempDetails);
					}else{
						originalMap.put(key, podetails);
					}
				}
				tempSPO.setPurchaseOrderDetails(convertMapToPODetails(originalMap));
				poGroupedBySupplier.put(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId(), tempSPO);
			}else{
				poGroupedBySupplier.put(rr.getSupplierPurchaseOrder().getSupplier().getSupplierId(), rr);
			}
		}
		List summarizedSPO = new ArrayList();
		
		for(Map.Entry<String, ReceivingReport> entry : poGroupedBySupplier.entrySet()){
			  String key = entry.getKey();
			  ReceivingReport rr= (ReceivingReport)entry.getValue();
			  summarizedSPO.add(rr);
		}
		
		return summarizedSPO;
	}

	private Set convertMapToPODetails(Map<String, PurchaseOrderDetails> map) {
		Set podetailsSet = new HashSet();
		for (Map.Entry<String, PurchaseOrderDetails> entry : map.entrySet()) {
			String key = entry.getKey();
			PurchaseOrderDetails podetails = (PurchaseOrderDetails) entry
					.getValue();
			podetailsSet.add(podetails);
		}
		return podetailsSet;
	}

	private Map<String, PurchaseOrderDetails> convertPurchaseOrderDetailsToMap(
			Set set) {
		Iterator itr = set.iterator();
		Map<String, PurchaseOrderDetails> tempMap = new HashMap();
		try {
			while (itr.hasNext()) {
				PurchaseOrderDetails podetails = (PurchaseOrderDetails) itr
						.next();
				tempMap.put(podetails.getItemCode(), podetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return tempMap;

	}
}
