package com.shofuku.accsystem.action.suppliers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.shofuku.accsystem.controllers.AccountEntryManager;
import com.shofuku.accsystem.controllers.DisbursementManager;
import com.shofuku.accsystem.controllers.InventoryManager;
import com.shofuku.accsystem.controllers.SupplierManager;
import com.shofuku.accsystem.controllers.TransactionManager;
import com.shofuku.accsystem.domain.disbursements.CheckPayments;
import com.shofuku.accsystem.domain.financials.AccountEntryProfile;
import com.shofuku.accsystem.domain.financials.Transaction;
import com.shofuku.accsystem.domain.inventory.PurchaseOrderDetails;
import com.shofuku.accsystem.domain.inventory.ReturnSlip;
import com.shofuku.accsystem.domain.suppliers.ReceivingReport;
import com.shofuku.accsystem.domain.suppliers.Supplier;
import com.shofuku.accsystem.domain.suppliers.SupplierInvoice;
import com.shofuku.accsystem.domain.suppliers.SupplierPurchaseOrder;
import com.shofuku.accsystem.utils.DateFormatHelper;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.PurchaseOrderDetailHelper;
import com.shofuku.accsystem.utils.SASConstants;

public class EditSupplierAction extends ActionSupport{

	SupplierManager manager = new SupplierManager();
	Supplier supplier;
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(EditSupplierAction.class);
	
	private String supplierModule;
	private String moduleParameter;
	private String moduleParameterValue;
	private String forWhat;
	private String forWhatDisplay;
	private double tempTotal;
	
	
	List purchaseOrderNoList;
	List supplierNoList;
	List receivingReportNoList;
	List checkVoucherList;
	
	//START 2013 - PHASE 3 : PROJECT 1: MARK
		List accountProfileCodeList;
		List<Transaction> transactionList;
		List<Transaction> transactions;
		Iterator itr;
		//END 2013 - PHASE 3 : PROJECT 1: MARK  

	PurchaseOrderDetailHelper poDetailsHelper = new PurchaseOrderDetailHelper();
	PurchaseOrderDetailHelper poDetailsHelperToCompare = new PurchaseOrderDetailHelper();
	
	SupplierInvoice invoice;
	ReceivingReport rr;
	CheckPayments chp;
	SupplierPurchaseOrder po;
	
	DisbursementManager disbursementManager = new DisbursementManager();
	//START 2013 - PHASE 3 : PROJECT 1: MARK
	AccountEntryManager accountEntryManager = new AccountEntryManager();
	TransactionManager transactionMananger = new TransactionManager();
	//END 2013 - PHASE 3 : PROJECT 1: MARK  
	
	
	DateFormatHelper df = new DateFormatHelper();
	private Session getSession(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public String execute() throws Exception {
		Session session = getSession();
		try {
			forWhatDisplay="edit";
			accountProfileCodeList = accountEntryManager.listAlphabeticalAccountEntryProfileChildrenAscByParameter(session);
			if (getSupplierModule().equalsIgnoreCase("profile")){
				Supplier supplier = new Supplier();
				supplier = (Supplier) manager.listSuppliersByParameter(supplier.getClass(), "supplierId", this.getSupplier().getSupplierId(),session).get(0);
				this.setSupplier(supplier);
				return "profile";
			}else if (getSupplierModule().equalsIgnoreCase("purchaseOrder")){
				supplierNoList = manager.listAlphabeticalAscByParameter(Supplier.class, "supplierId", session);
				SupplierPurchaseOrder po = new SupplierPurchaseOrder();
				po = (SupplierPurchaseOrder) manager.listSuppliersByParameter(po.getClass(), "supplierPurchaseOrderId", this.getPo().getSupplierPurchaseOrderId(),session).get(0);
				poDetailsHelper.generatePODetailsListFromSet(po.getPurchaseOrderDetails());
				poDetailsHelper.generateCommaDelimitedValues();
				this.setSupplier(po.getSupplier());
				this.setPo(po);
				
				return "purchaseOrder";
			}else if (getSupplierModule().equalsIgnoreCase("receivingReport")){
				ReceivingReport rr = new ReceivingReport();
				purchaseOrderNoList = manager.listAlphabeticalAscByParameter(SupplierPurchaseOrder.class, "supplierPurchaseOrderId", session);
				rr = (ReceivingReport) manager.listSuppliersByParameter(rr.getClass(), "receivingReportNo", this.getRr().getReceivingReportNo(),session).get(0);
				
				//START Phase 3 - Azhee
				List tempList = transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", rr.getReceivingReportNo(), session);
				itr = tempList.iterator();
				transactionList = new ArrayList<Transaction>(); 
				while(itr.hasNext()) {
					Transaction transaction = (Transaction)itr.next();
					if(transaction.getIsInUse().equalsIgnoreCase(SASConstants.TRANSACTION_IN_USE)) {
						transactionList.add(transaction);
					}
				}
				this.setTransactionList(transactionList);
				//END Phase 3 - Azhee
				
				/*
				 * Checking and fetching existing return slips
				 */
				
				InventoryManager invManager= new InventoryManager();
				Session rsSession = getSession();
				List returnSlipList = invManager.listInventoryByParameter(ReturnSlip.class, "returnSlipReferenceOrderNo", rr.getReceivingReportNo(), rsSession);
				
				if(returnSlipList.size()>0) {
					rr.setReturnSlipList(returnSlipList);
				}else {
					rr.setReturnSlipList(null);
				}
				
				if(null==poDetailsHelperToCompare) {
					poDetailsHelperToCompare = new PurchaseOrderDetailHelper();
				}
				poDetailsHelperToCompare.generatePODetailsListFromSet(rr.getSupplierPurchaseOrder().getPurchaseOrderDetails());
				poDetailsHelperToCompare.generateCommaDelimitedValues();
				
				if(null==poDetailsHelper) {
				}else {
					poDetailsHelper.generatePODetailsListFromSet(rr.getPurchaseOrderDetails());
					poDetailsHelper.generateCommaDelimitedValues();
					//2014 - ITEM COLORING
 					//poDetailsHelper.generateItemTypesForExistingItems(rsSession);
				}
				this.setSupplier(rr.getSupplierPurchaseOrder().getSupplier());
				this.setPo(rr.getSupplierPurchaseOrder());
				//START Phase 3 - Project 1 - Mark
				if(transactionList.size()>0)
					this.rr.setTransactions(transactionList);
				else {
					transactionList = new ArrayList();
					Transaction transaction = new Transaction();
					transactionList.add(transaction);
				}
				//END Phase 3 - Project 1 - Mark
				this.setRr(rr);
				
				return "receivingReport";
			}else  {
				SupplierInvoice supInv = new SupplierInvoice();
				receivingReportNoList = manager.listAlphabeticalAscByParameter(ReceivingReport.class, "receivingReportNo", session);
				checkVoucherList= disbursementManager.listDisbursementsByParameter(CheckPayments.class, "invoice.supplierInvoiceNo", this.getInvoice().getSupplierInvoiceNo(), session);
				
				Iterator itr = checkVoucherList.iterator();
				
				while (itr.hasNext()){
					CheckPayments chpFromList = (CheckPayments) itr.next();
					tempTotal = tempTotal + chpFromList.getAmountToPay();
				}
				
				supInv = (SupplierInvoice) manager.listSuppliersByParameter(supInv.getClass(), "supplierInvoiceNo", this.getInvoice().getSupplierInvoiceNo(),session).get(0);
				//START Phase 3 - Azhee
				List tempList = transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);transactionMananger.listTransactionByParameterLike(Transaction.class, "transactionReferenceNumber", invoice.getSupplierInvoiceNo(), session);
				//START Phase 3 - Project 1 - Mark
				if(tempList.size()>0) {
					itr = tempList.iterator();
					transactionList = new ArrayList<Transaction>(); 
					while(itr.hasNext()) {
						Transaction transaction = (Transaction)itr.next();
						if(transaction.getIsInUse().equalsIgnoreCase(SASConstants.TRANSACTION_IN_USE)) {
							transactionList.add(transaction);
						}
					}
				}else {
					transactionList = new ArrayList();
					Transaction transaction = new Transaction();
					transactionList.add(transaction);
				}
				this.setTransactionList(transactionList);	
				//END Phase 3 - Project 1 - Mark
				//END Phase 3 - Azhee
				
				if(null==poDetailsHelperToCompare) {
					poDetailsHelperToCompare = new PurchaseOrderDetailHelper();
				}
				poDetailsHelperToCompare.generatePODetailsListFromSet(supInv.getReceivingReport().getPurchaseOrderDetails());
				poDetailsHelperToCompare.generateCommaDelimitedValues();
				
				if(null==poDetailsHelper) {
				}else {
					poDetailsHelper.generatePODetailsListFromSet(supInv.getPurchaseOrderDetails());
					poDetailsHelper.generateCommaDelimitedValues();
					//2014 - ITEM COLORING
 					poDetailsHelper.generateItemTypesForExistingItems(session);
				}
				supInv.setPurchaseOrderDetailsTotalAmount(poDetailsHelper.getTotalAmount());
				this.setSupplier(supInv.getReceivingReport().getSupplierPurchaseOrder().getSupplier());
				this.setPo(supInv.getReceivingReport().getSupplierPurchaseOrder());
				this.setRr(supInv.getReceivingReport());
				//START Phase 3 - Azhee
				this.invoice.setTransactions(transactionList);
				//END Phase 3 - Azhee
				this.setInvoice(supInv);
				
				return "invoice";
			}
		}catch (RuntimeException re) {
			re.printStackTrace();
			if (getSupplierModule().equalsIgnoreCase("profile")) {
				return "profile";
			}else if (getSupplierModule().equalsIgnoreCase("purchaseOrder")) {
				return "purchaseOrder";
			}else if (getSupplierModule().equalsIgnoreCase("receivingReport")) {
				return "receivingReport";
			}else {
				return "invoice";
			}
		}
			finally {
				if(session.isOpen()){
					session.close();
					session.getSessionFactory().close();
				}
		}
		
	}
	private void inlcudePoDetails() {
		if(poDetailsHelper!=null) {
			poDetailsHelper.prepareSetAndList();
		}if(poDetailsHelperToCompare!=null) {
			poDetailsHelperToCompare.prepareSetAndList();
		}
	}
	
	public ReceivingReport getRr() {
		return rr;
	}
	public void setRr(ReceivingReport rr) {
		this.rr = rr;
	}
	
	public SupplierInvoice getInvoice() {
		return invoice;
	}
	public void setInvoice(SupplierInvoice invoice) {
		this.invoice = invoice;
	}
	
	public PurchaseOrderDetailHelper getPoDetailsHelper() {
		return poDetailsHelper;
	}
	public void setPoDetailsHelper(PurchaseOrderDetailHelper poDetailsHelper) {
		this.poDetailsHelper = poDetailsHelper;
	}
	
	public PurchaseOrderDetailHelper getPoDetailsHelperToCompare() {
		return poDetailsHelperToCompare;
	}
	public void setPoDetailsHelperToCompare(PurchaseOrderDetailHelper poDetailsHelperToCompare) {
		this.poDetailsHelperToCompare = poDetailsHelperToCompare;
	}
	
	public String getForWhat() {
		return forWhat;
	}
	public void setForWhat(String forWhat) {
		this.forWhat = forWhat;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public String getSupplierModule() {
		return supplierModule;
	}
	public void setSupplierModule(String supplierModule) {
		this.supplierModule = supplierModule;
	}
	public String getModuleParameter() {
		return moduleParameter;
	}
	public void setModuleParameter(String moduleParameter) {
		this.moduleParameter = moduleParameter;
	}
	public String getModuleParameterValue() {
		return moduleParameterValue;
	}
	public void setModuleParameterValue(String moduleParameterValue) {
		this.moduleParameterValue = moduleParameterValue;
	}

	public SupplierPurchaseOrder getPo() {
		return po;
	}
	public void setPo(SupplierPurchaseOrder po) {
		this.po = po;
}
	public CheckPayments getChp() {
		return chp;
	}
	public void setChp(CheckPayments chp) {
		this.chp = chp;
	}
	public List getPurchaseOrderNoList() {
		return purchaseOrderNoList;
	}
	public void setPurchaseOrderNoList(List purchaseOrderNoList) {
		this.purchaseOrderNoList = purchaseOrderNoList;
	}
	public List getSupplierNoList() {
		return supplierNoList;
	}
	public void setSupplierNoList(List supplierNoList) {
		this.supplierNoList = supplierNoList;
	}
	public List getReceivingReportNoList() {
		return receivingReportNoList;
	}
	public void setReceivingReportNoList(List receivingReportNoList) {
		this.receivingReportNoList = receivingReportNoList;
	}
	public String getForWhatDisplay() {
		return forWhatDisplay;
	}
	public void setForWhatDisplay(String forWhatDisplay) {
		this.forWhatDisplay = forWhatDisplay;
	}
	public List getCheckVoucherList() {
		return checkVoucherList;
	}
	public void setCheckVoucherList(List checkVoucherList) {
		this.checkVoucherList = checkVoucherList;
	}
	public double getTempTotal() {
		return tempTotal;
	}
	public void setTempTotal(double tempTotal) {
		this.tempTotal = tempTotal;
	}
	//START 2013 - PHASE 3 : PROJECT 1: MARK 
		public List getAccountProfileCodeList() {
			return accountProfileCodeList;
		}
		public void setAccountProfileCodeList(List accountProfileCodeList) {
			this.accountProfileCodeList = accountProfileCodeList;
		}
		public List<Transaction> getTransactionList() {
			return transactionList;
		}
		
		public void setTransactionList(List<Transaction> transactionList) {
			this.transactionList = transactionList;
		}
		public List<Transaction> getTransactions() {
			return transactions;
		}
		public void setTransactions(List<Transaction> transactions) {
			this.transactions = transactions;
		}
		//END 2013 - PHASE 3 : PROJECT 1: MARK

}