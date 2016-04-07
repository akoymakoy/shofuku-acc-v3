package com.shofuku.accsystem.action.receipts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shofuku.accsystem.controllers.AccountEntryManager;
import com.shofuku.accsystem.controllers.CustomerManager;
import com.shofuku.accsystem.controllers.DisbursementManager;
import com.shofuku.accsystem.controllers.FinancialsManager;
import com.shofuku.accsystem.controllers.ReceiptsManager;
import com.shofuku.accsystem.controllers.TransactionManager;
import com.shofuku.accsystem.domain.customers.DeliveryReceipt;
import com.shofuku.accsystem.domain.financials.Transaction;
import com.shofuku.accsystem.domain.financials.Vat;
import com.shofuku.accsystem.domain.receipts.CashCheckReceipts;
import com.shofuku.accsystem.domain.receipts.OROthers;
import com.shofuku.accsystem.domain.receipts.ORSales;
import com.shofuku.accsystem.domain.security.UserAccount;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.RecordCountHelper;
import com.shofuku.accsystem.utils.SASConstants;

public class AddReceiptsAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	
	Map actionSession;
	UserAccount user;

	ReceiptsManager receiptsManager;
	AccountEntryManager accountEntryManager;
	TransactionManager transactionManager;
	FinancialsManager financialsManager;	
	DisbursementManager disbursementManager;
	CustomerManager customerManager; 
	
	RecordCountHelper rch;
	
	@Override
	public void prepare() throws Exception {
		actionSession = ActionContext.getContext().getSession();
		user = (UserAccount) actionSession.get("user");

		receiptsManager = (ReceiptsManager) actionSession.get("receiptsManager");
		accountEntryManager = (AccountEntryManager) actionSession.get("accountEntryManager");
		transactionManager = (TransactionManager) actionSession.get("transactionManager");
		financialsManager = (FinancialsManager) actionSession.get("financialsManager");
		disbursementManager = (DisbursementManager) actionSession.get("disbursementManager");
		customerManager = (CustomerManager)actionSession.get("customerManager");
		
		rch = new RecordCountHelper(actionSession);
		
	}
	

	private String subModule;
	private String moduleParameter;
	private String forWhat;
	private String forWhatDisplay;

	ORSales orSales;
	OROthers orOthers;
	CashCheckReceipts ccReceipts;
	DeliveryReceipt dr;
	//START 2013 - PHASE 3 : PROJECT 1: MARK
		List accountProfileCodeList;
		List deliveryReceiptNoList;
		List<Transaction> transactionList;
		List<Transaction> transactions;
	//END 2013 - PHASE 3 : PROJECT 1: MARK 


	public String newReceiptEntry() {
		Session session = getSession();
		if (subModule.equalsIgnoreCase("ccReceipts")){
		ccReceipts = new CashCheckReceipts();
		ccReceipts.setCashReceiptNo(rch.getPrefix(
				SASConstants.CASHCHECKRECEIPTS,
				SASConstants.CASHCHECKRECEIPTS_PREFIX));
		return "cashCheckReceipts";
		}else {
			deliveryReceiptNoList = customerManager.listAllCustomerDeliveryReceiptNo(session);
		return "orSales";
		}
	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public String execute() throws Exception {
		Session session = getSession();
		try {
			boolean addResult = false;
			accountProfileCodeList = accountEntryManager.listAlphabeticalAccountEntryProfileChildrenAscByParameter(session);
			deliveryReceiptNoList = customerManager.listAllCustomerDeliveryReceiptNo(session);
			if (getSubModule().equalsIgnoreCase("orSales")) {
				if (validateORSales()) {
				} else {
					List orsList = null;
					orsList = receiptsManager.listReceiptsByParameter(ORSales.class,
							"orNumber", getOrSales().getOrNumber(),session);
					if (!(orsList.isEmpty())) {
						addActionMessage(SASConstants.EXISTS);
					} else {
						
						//add delivery receipt as reference number
						DeliveryReceipt dr = new DeliveryReceipt();
						dr = (DeliveryReceipt) customerManager.listByParameter(DeliveryReceipt.class, "deliveryReceiptNo", orSales.getSalesInvoiceNumber(), session).get(0);
						orSales.setReceivedFrom(dr.getCustomerPurchaseOrder().getCustomer().getCustomerName());
						orSales.setAddress(dr.getCustomerPurchaseOrder().getCustomer().getBillingAddress());
						orSales.setTin(dr.getCustomerPurchaseOrder().getCustomer().getTin());
						orSales.setAmount(dr.getTotalAmount());
						
						//START - 2013 - PHASE 3 : PROJECT 1: MARK
						transactionList = new ArrayList();
						//START - 2016 DEFAULT TRANSACTIONS
						//add account entry profile based on customer no
						accountEntryManager.addDefaultTransactionEntry(transactionList,dr.getCustomerPurchaseOrder().getCustomer().getCustomerNo().toString(), orSales.getTheAmountOf());
						//add cash in bank
						accountEntryManager.addDefaultTransactionEntry(transactionList,SASConstants.CASH_IN_BANK_BDO_CODE, orSales.getTheAmountOf());
						//END - 2016 DEFAULT TRANSACTIONS
						
						//END - 2013 - PHASE 3 : PROJECT 1: MARK
						//START: 2013 - PHASE 3 : PROJECT 4: AZ
						Vat vatDetails = new Vat();
						vatDetails.setAddress(orSales.getAddress());
						//TEST ONLY WHILE WAITING FOR TIN FOR SUPPLIER
						vatDetails.setTinNumber(orSales.getTin());
						vatDetails.setAmount(orSales.getTheAmountOf());
						vatDetails.setVattableAmount(disbursementManager.computeVat(orSales.getTheAmountOf()));
						vatDetails.setVatAmount(disbursementManager.computeVatAmount(vatDetails.getVattableAmount()));
						vatDetails.setVatReferenceNo(orSales.getOrNumber());
						vatDetails.setAddress(orSales.getVatDetails().getAddress());
						vatDetails.setOrNo(orSales.getVatDetails().getOrNo());
						vatDetails.setOrDate(orSales.getOrDate());
						orSales.setVatDetails(vatDetails);
						financialsManager.insertVatDetails(vatDetails, session);							
						//END: 2013 - PHASE 3 : PROJECT 4: AZ
						
						addResult = receiptsManager.addReceiptsObject(orSales,session);
						if (addResult == true) {
							addActionMessage(SASConstants.ADD_SUCCESS);
							forWhat = "true";
							forWhatDisplay="edit";
						} else {
							addActionError(SASConstants.FAILED);
						}
					}
				}
				return "orSales";
			} else if (getSubModule().equalsIgnoreCase("orOthers")) {

				if (validateOROther()) {
				} else {
					List oroList = null;
					oroList = receiptsManager.listReceiptsByParameter(OROthers.class,
							"orNumber", getOrOthers().getOrNumber(),session);
					if (!(oroList.isEmpty())) {
						addActionMessage(SASConstants.EXISTS);
					} else {
						//START - 2013 - PHASE 3 : PROJECT 1: MARK
						transactionList = new ArrayList();
						//START - 2016 DEFAULT TRANSACTIONS
						//add account entry profile based on customer no
						accountEntryManager.addDefaultTransactionEntry(transactionList,SASConstants.OTHER_INCOME_CODE, orOthers.getTheAmountOf());
						//add cash in bank
						accountEntryManager.addDefaultTransactionEntry(transactionList,SASConstants.CASH_IN_BANK_BDO_CODE, orOthers.getTheAmountOf());
						//END - 2016 DEFAULT TRANSACTIONS
						
						//END - 2013 - PHASE 3 : PROJECT 1: MARK
						//START: 2013 - PHASE 3 : PROJECT 4: AZ
						Vat vatDetails = new Vat();
						vatDetails.setAddress(orOthers.getAddress());
						vatDetails.setTinNumber(orOthers.getTin());
						vatDetails.setAmount(orOthers.getTheAmountOf());
						vatDetails.setVattableAmount(disbursementManager.computeVat(orOthers.getTheAmountOf()));
						vatDetails.setVatAmount(disbursementManager.computeVatAmount(vatDetails.getVattableAmount()));
						vatDetails.setVatReferenceNo(orOthers.getOrNumber());
						vatDetails.setAddress(orOthers.getVatDetails().getAddress());
						vatDetails.setOrNo(orOthers.getVatDetails().getOrNo());
						vatDetails.setOrDate(orOthers.getOrDate());
						orOthers.setVatDetails(vatDetails);
						financialsManager.insertVatDetails(vatDetails, session);							
						//END: 2013 - PHASE 3 : PROJECT 4: AZ
						addResult = receiptsManager.addReceiptsObject(getOrOthers(),session);
						if (addResult == true) {
							addActionMessage(SASConstants.ADD_SUCCESS);
							forWhat = "true";
							forWhatDisplay="edit";
						} else {
							addActionError(SASConstants.FAILED);
						}
					}
				}
				return "orOthers";

			} else {
				if (validateCashCheckReceipt()) {
				} else {
					List crList = null;
					crList = receiptsManager.listReceiptsByParameter(
							CashCheckReceipts.class, "cashReceiptNo",
							getCcReceipts().getCashReceiptNo(),session);
					if (!(crList.isEmpty())) {
						addActionMessage(SASConstants.EXISTS);
					} else {
						//START - 2013 - PHASE 3 : PROJECT 1: MARK
						transactionList = new ArrayList();
						//START - 2016 DEFAULT TRANSACTIONS
						//add cash in bank
						accountEntryManager.addDefaultTransactionEntry(transactionList,SASConstants.CASH_IN_BANK_BDO_CODE, ccReceipts.getAmount());
						//END - 2016 DEFAULT TRANSACTIONS
						
						//END - 2013 - PHASE 3 : PROJECT 1: MARK
						//START: 2013 - PHASE 3 : PROJECT 4: AZ
						Vat vatDetails = new Vat();
						vatDetails.setTinNumber(SASConstants.DEFAULT_TIN);
						vatDetails.setAmount(ccReceipts.getAmount());
						vatDetails.setVattableAmount(disbursementManager.computeVat(ccReceipts.getAmount()));
						vatDetails.setVatAmount(disbursementManager.computeVatAmount(vatDetails.getVattableAmount()));
						vatDetails.setVatReferenceNo(ccReceipts.getCashReceiptNo());
						vatDetails.setAddress(ccReceipts.getVatDetails().getAddress());
						vatDetails.setOrNo(ccReceipts.getVatDetails().getOrNo());
						vatDetails.setOrDate(ccReceipts.getCashReceiptDate());
						ccReceipts.setVatDetails(vatDetails);
						financialsManager.insertVatDetails(vatDetails, session);							
						//END: 2013 - PHASE 3 : PROJECT 4: AZ
						addResult = receiptsManager.addReceiptsObject(getCcReceipts(),session);
						if (addResult == true) {
							rch.updateCount(SASConstants.CASHCHECKRECEIPTS,
									"add");
							addActionMessage(SASConstants.ADD_SUCCESS);
							forWhat = "true";
							forWhatDisplay="edit";
						} else {
							addActionError(SASConstants.FAILED);
						}
					}
				}
				return "cashCheckReceipts";
			}
		} catch (RuntimeException re) {
			re.printStackTrace();
			if (getSubModule().equalsIgnoreCase("orSales")) {
				return "orSales";
			} else if (getSubModule().equalsIgnoreCase("orOthers")) {
				return "orOthers";
			} else {
				return "cashCheckReceipts";
			}

		} finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}

	}

	private boolean validateORSales() {

		boolean errorFound = false;
		if ("".equals(getOrSales().getOrNumber())) {
			addFieldError("orSales.orNumber", "REQUIRED");
			errorFound = true;
		}
		 if (null==(getOrSales().getOrDate())){
		 addActionError("REQUIRED: OR Date");
		 errorFound= true;
		 }
		 /*if ("".equals(getOrSales().getReceivedFrom())){
		 addFieldError("orSales.receivedFrom","REQUIRED");
		 errorFound= true;
		 }else {
			 if (getOrSales().getReceivedFrom().trim().length()>100) {
				 addFieldError("orSales.receivedFrom",SASConstants.MAXIMUM_LENGTH_100);
			 }
		 }
		 if ("".equals(getOrSales().getAddress())){
		 addFieldError("orSales.address","REQUIRED");
		 errorFound= true;
		 }else {
			 if (getOrSales().getAddress().trim().length()>200) {
				 addFieldError("orSales.address",SASConstants.MAXIMUM_LENGTH_200);
			 }
		 }
		 if ("".equals(getOrSales().getInFullPartialPaymentOf())){
		 addFieldError("orSales.inFullPartialPaymentOf","REQUIRED");
		 errorFound= true;
		 }
		 if ("".equals(getOrSales().getSalesInvoiceNumber())){
		 addFieldError("orSales.salesInvoiceNumber","REQUIRED");
		 errorFound= true;
		 }*/
		return errorFound;
	}

	private boolean validateOROther() {
		boolean errorFound = false;
		if ("".equals(getOrOthers().getOrNumber())) {
			addFieldError("orOthers.orNumber", "REQUIRED");
			errorFound = true;
		}
		 if (null==(getOrOthers().getOrDate())){
		 addActionError("REQUIRED: OR Date");
		 errorFound= true;
		 }
		 if ("".equals(getOrOthers().getReceivedFrom())){
		 addFieldError("orOthers.receivedFrom","REQUIRED");
		 errorFound= true;
		 }else {
			 if (getOrOthers().getReceivedFrom().trim().length()>100) {
				 addFieldError("orOthers.receivedFrom",SASConstants.MAXIMUM_LENGTH_100);
			 }
		 }
		 if ("".equals(getOrOthers().getAddress())){
		 addFieldError("orOthers.address","REQUIRED");
		 errorFound= true;
		 }else {
			 if (getOrOthers().getAddress().trim().length()>200) {
				 addFieldError("orOthers.address",SASConstants.MAXIMUM_LENGTH_200);
			 }
		 }
		 if ("".equals(getOrOthers().getInFullPartialPaymentOf())){
		 addFieldError("orOthers.inFullPartialPaymentOf","REQUIRED");
		 errorFound= true;
		 }
		 if ("".equals(getOrOthers().getSalesInvoiceNumber())){
		 addFieldError("orOthers.salesInvoiceNumber","REQUIRED");
		 errorFound= true;
		 }
		return errorFound;
	}

	private boolean validateCashCheckReceipt() {
		boolean errorFound = false;
		if ("".equals(getCcReceipts().getCashReceiptNo())) {
			addFieldError("ccReceipts.cashReceiptNo", "REQUIRED");
			errorFound = true;
		}
		 if (null==(getCcReceipts().getCashReceiptDate())){
		 addActionMessage("REQUIRED: Cash Receipt Date");
		 errorFound= true;
		 }
		 if ("".equals(getCcReceipts().getParticulars())){
		 addFieldError("ccReceipts.particulars","REQUIRED");
		 errorFound= true;
		 }else {
			 if (getCcReceipts().getParticulars().trim().length()>200) {
				 addFieldError("ccReceipts.particulars",SASConstants.MAXIMUM_LENGTH_200);
			 }
		 }
		 if (0==(getCcReceipts().getAmount())){
		 addFieldError("ccReceipts.amount","REQUIRED");
		 errorFound= true;
		 }

		return errorFound;

	}

	public CashCheckReceipts getCcReceipts() {
		return ccReceipts;
	}

	public void setCcReceipts(CashCheckReceipts ccReceipts) {
		this.ccReceipts = ccReceipts;
	}

	public String getModuleParameter() {
		return moduleParameter;
	}

	public void setModuleParameter(String moduleParameter) {
		this.moduleParameter = moduleParameter;
	}

	public String getForWhat() {
		return forWhat;
	}

	public void setForWhat(String forWhat) {
		this.forWhat = forWhat;
	}

	public ORSales getOrSales() {
		return orSales;
	}

	public void setOrSales(ORSales orSales) {
		this.orSales = orSales;
	}

	public OROthers getOrOthers() {
		return orOthers;
	}

	public void setOrOthers(OROthers orOthers) {
		this.orOthers = orOthers;
	}

	public String getSubModule() {
		return subModule;
	}

	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}

	public String getForWhatDisplay() {
		return forWhatDisplay;
	}

	public void setForWhatDisplay(String forWhatDisplay) {
		this.forWhatDisplay = forWhatDisplay;
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

		public List getDeliveryReceiptNoList() {
			return deliveryReceiptNoList;
		}

		public void setDeliveryReceiptNoList(List deliveryReceiptNoList) {
			this.deliveryReceiptNoList = deliveryReceiptNoList;
		}

		public DeliveryReceipt getDr() {
			return dr;
		}

		public void setDr(DeliveryReceipt dr) {
			this.dr = dr;
		}
		
		
		//END 2013 - PHASE 3 : PROJECT 1: MARK 
		
}
