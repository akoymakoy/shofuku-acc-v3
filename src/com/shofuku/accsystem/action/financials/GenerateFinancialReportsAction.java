package com.shofuku.accsystem.action.financials;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shofuku.accsystem.controllers.AccountEntryManager;
import com.shofuku.accsystem.controllers.CustomerManager;
import com.shofuku.accsystem.controllers.FinancialsManager;
import com.shofuku.accsystem.controllers.ReportAndSummaryManager;
import com.shofuku.accsystem.controllers.SupplierManager;
import com.shofuku.accsystem.domain.customers.Customer;
import com.shofuku.accsystem.domain.financials.AccountEntryProfile;
import com.shofuku.accsystem.domain.financials.JournalEntryProfile;
import com.shofuku.accsystem.domain.security.UserAccount;
import com.shofuku.accsystem.domain.suppliers.Supplier;
import com.shofuku.accsystem.utils.DateFormatHelper;
import com.shofuku.accsystem.utils.ExportSearchResultsHelper;
import com.shofuku.accsystem.utils.FinancialReportsPoiHelper;
import com.shofuku.accsystem.utils.HibernateSessionWatcher;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.SASConstants;

public class GenerateFinancialReportsAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -5015769544134286832L;
	

	Map actionSession;
	UserAccount user;

	SupplierManager supplierManager;
	CustomerManager customerManager;
	AccountEntryManager accountEntryManager;
	ReportAndSummaryManager reportAndSummaryManager;
	FinancialsManager financialsManager;
	
	FinancialReportsPoiHelper poiHelper;

	public void prepare() throws Exception {
		
		actionSession = ActionContext.getContext().getSession();
		HibernateSessionWatcher sessionWatcher = new HibernateSessionWatcher();
		user = sessionWatcher.getAuthenticatedUser(ActionContext.getContext().getSession());

		supplierManager 		= (SupplierManager) 	actionSession.get("supplierManager");
		customerManager 		= (CustomerManager) 	actionSession.get("customerManager");
		accountEntryManager		= (AccountEntryManager) actionSession.get("accountEntryManager");
		reportAndSummaryManager = (ReportAndSummaryManager) actionSession.get("reportAndSummaryManager");
		financialsManager 		= (FinancialsManager) 	actionSession.get("financialsManager");
		
		poiHelper = new FinancialReportsPoiHelper(actionSession);
		
	}

	private static final Logger logger = Logger
			.getLogger(ExportSearchResultsHelper.class);
	
	//General Variables
	InputStream excelStream;
	String contentDisposition;
	String documentFormat = "xls";
	AccountEntryProfile aep;
	JournalEntryProfile jep;
	String reportType;
	String ledgerType;
	String filename="";
	String dateFrom="";
	String dateTo="";
	
	//Ledger Account Variables
	List supplierList;
	List customerList;
	List accountCodeList;
	
	/*LEGEND:
	 * 	01-	Ledger account
		02-	Trial balance
		03-	Income Statement
		04-	Balance Sheet
		05-	Statement of Cost of Goods Manufactured ->removed
		06-	Statement of Cost of Goods Sold.
		07-	Statement of Changes in Shareholders' Equity
		08-	Journal Entries  --> General Journal
		09-	VAT Report
		10-	Check Encashment
		11- Aging of Account Payable
		12 Aging of Account Receivable
		
	 * */
	public String execute() throws Exception{
		Session session = getSession();

		DateFormatHelper dfh= new DateFormatHelper();
		ServletContext servletContext = ServletActionContext
				.getServletContext();
		accountCodeList = accountEntryManager.listAlphabeticalAccountEntryProfileAscByParameter(AccountEntryProfile.class, "accountCode", session);
		try {
			
			
			if (reportType.equals("01")) {
				//01-	Ledger account
				
				// TODO: APPLY THIS LOGIC FOR LOGIN 
				// get the data from session
				
				//Map sess = ActionContext.getContext().getSession();
				//sess.put("user",user.getUserName());
				//sess.put("role",user.getRole());

				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateLedgerAccountsReport(dateFrom,dateTo,supplierList,customerList, session);
				filename= SASConstants.LEDGER_REPORTS_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("02")) {
				//02-	Trial balance
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateTrialBalanceReport(dateFrom,dateTo,session);
				filename= SASConstants.TRIAL_BALANCE_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("03")) {
				//03 - Income Statement
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateIncomeStatement(dateFrom,dateTo,session);
				filename= SASConstants.BALANCE_SHEET_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("04")) {
				//04 - Balance Sheet
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateBalanceSheet(dateFrom,dateTo,session);
				filename= SASConstants.BALANCE_SHEET_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("05")) {
				//05-	Statement of Cost of Goods Manufactured
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateCoGM(dateFrom,dateTo,session);
				filename= SASConstants.BALANCE_SHEET_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("06")) {
				//06-	Statement of Cost of Goods Sold.
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateCoGS(dateFrom,dateTo,session);
				filename= SASConstants.BALANCE_SHEET_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("07")) {
				//07-	Statement of Changes in Shareholders' Equity
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateScheduleOfChangesInStockholdersEquity(dateFrom,dateTo,session);
				filename= SASConstants.BALANCE_SHEET_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("08")) {
				//08-	Journal Entries Report
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateJournalEntries(dateFrom,dateTo,session);
				filename= SASConstants.JOURNAL_ENTRIES_FILENAME + dfh.getDateToday();

			} else if (reportType.equals("09")) {
				//09-	VAT Report
				poiHelper.setReportType(reportType);
				excelStream = poiHelper.generateVatReport(dateFrom,dateTo,session);
				filename= SASConstants.VAT_REPORT_FILENAME + dfh.getDateToday();
			} else if (reportType.equals("10")) {
				generateCheckEncashmentReport();
			} else if (reportType.equals("11")) {
				//TODO: assign a filename
				//TODO: remove hardocded "accountProfileEntry" put it in constants
				filename= SASConstants.CHART_OF_ACCOUNT_FILENAME + dfh.getDateToday();
				String subModule = "AccountEntryProfile";
				excelStream = reportAndSummaryManager.generateSummary(servletContext,
						dateFrom, dateTo, subModule,session);
			}else {
				 preloadLists(session);
				 actionSession.put("logined","true");
				 actionSession.put("context", new Date());
				return INPUT;
			}
			session = getSession();
			preloadLists(session);
			

		} catch (RuntimeException re) {
			re.printStackTrace();
		} finally {
			if (session.isOpen()) {
				session.close();
				session.getSessionFactory().close();
			}
		}
	return SUCCESS;
	}
	
	private void preloadLists(Session session) {
		// Pre-load all lists needed 
		
		supplierList = supplierManager.listAlphabeticalAscByParameter(Supplier.class, "supplierId", session);
		customerList = customerManager.listAlphabeticalAscByParameter(Customer.class, "customerNo", session);
		
	}
	

	private void generateCheckEncashmentReport() {
		
	}

	private void generateVatReport() {
		// TODO Auto-generated method stub
		
	}

	private void generateJournalEntries() {
		// TODO Auto-generated method stub
		
	}

	private void generateStatementOfShareholdersEquity() {
		// TODO Auto-generated method stub
		
	}

	private void generateCostOfGoodsMfg() {
		// TODO Auto-generated method stub
		
	}

	private void generateBalanceSheet() {
		// TODO Auto-generated method stub
		
	}

	private void generateIncomeStatement() {
		// TODO Auto-generated method stub
		
	}

	private void generateTrialBalance() {
		// TODO Auto-generated method stub
		
	}

	private void generateLedgerAccountReport() {
		// TODO Auto-generated method stub
	}

	
	
	
	/*
	 * GETTERS / SETTERS
	 * 
	 * */
	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public String getLedgerType() {
		return ledgerType;
	}

	public void setLedgerType(String ledgerType) {
		this.ledgerType = ledgerType;
	}
	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public List getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List customerList) {
		this.customerList = customerList;
	}
	public List getSupplierList() {
		return supplierList;
	}
	public String getDocumentFormat() {
		return documentFormat;
	}

	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}
	public void setSupplierList(List supplierList) {
		this.supplierList = supplierList;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getContentDisposition() {
		return "filename=\""+ filename +".xls\"";
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	String getExcelContentType() {
		return documentFormat == "xlsx" ? "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
				: "application/vnd.ms-excel";
	}

	public List getAccountCodeList() {
		return accountCodeList;
	}

	public void setAccountCodeList(List accountCodeList) {
		this.accountCodeList = accountCodeList;
	}

	
	
}
