package com.shofuku.accsystem.action.receipts;

import java.util.Map;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.shofuku.accsystem.controllers.ReceiptsManager;
import com.shofuku.accsystem.domain.receipts.CashCheckReceipts;
import com.shofuku.accsystem.domain.receipts.OROthers;
import com.shofuku.accsystem.domain.receipts.ORSales;
import com.shofuku.accsystem.domain.security.UserAccount;
import com.shofuku.accsystem.utils.HibernateSessionWatcher;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.SASConstants;

public class DeleteReceiptAction extends ActionSupport implements Preparable{

	private static final long serialVersionUID = 1L;
	
	Map actionSession;
	UserAccount user;

	ReceiptsManager receiptsManager;
	
	
	@Override
	public void prepare() throws Exception {
		actionSession = ActionContext.getContext().getSession();
		HibernateSessionWatcher sessionWatcher = new HibernateSessionWatcher();
		user = sessionWatcher.getAuthenticatedUser(ActionContext.getContext().getSession());

		receiptsManager = (ReceiptsManager) actionSession.get("receiptsManager");
		
	}
	
	
	ORSales orSales;
	OROthers orOthers;
	CashCheckReceipts ccReceipts;
	private String orSNo;
	private String orONo;
	private String crNo;
	private String subModule;
	
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public String execute() throws Exception{
		Session session = getSession();
		try {
			boolean deleteResult;

			if (getSubModule().equalsIgnoreCase("orSales")) {
				deleteResult = receiptsManager.deleteReceiptsByParameter(getOrSNo(), ORSales.class,session);
				if (deleteResult == true) {
					orSales = new ORSales();
					addActionMessage(SASConstants.DELETED);
				} else {
					addActionError(SASConstants.NON_DELETED);
				}
				return "orSalesDeleted";
			} else if (getSubModule().equalsIgnoreCase("orOthers")) {
				deleteResult = receiptsManager.deleteReceiptsByParameter(getOrONo(),
						OROthers.class,session);
				if (deleteResult == true) {
					orOthers = new OROthers();
					addActionMessage(SASConstants.DELETED);
				} else {
					addActionError(SASConstants.NON_DELETED);
				}
				return "orOthersDeleted";
			} else  {
				deleteResult = receiptsManager.deleteReceiptsByParameter(getCrNo(), CashCheckReceipts.class,session);
				if (deleteResult == true) {
					ccReceipts = new CashCheckReceipts();
					addActionMessage(SASConstants.DELETED);
				} else {
					addActionError(SASConstants.NON_DELETED);
				}
				return "cashCheckReceiptsDeleted";
			}
		} catch (RuntimeException re) {
			if (getSubModule().equalsIgnoreCase("orSales")) {
				return "orSalesDeleted";
			}else if (getSubModule().equalsIgnoreCase("orOthers")) { 
				return "orOthersDeleted";
			}else {
				return "cashCheckReceiptsDeleted";
			}
		}  finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}
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
	public CashCheckReceipts getCashCheckReceipt() {
		return ccReceipts;
	}
	public void setCashCheckReceipt(CashCheckReceipts cashCheckReceipt) {
		this.ccReceipts = cashCheckReceipt;
	}
	public String getOrSNo() {
		return orSNo;
	}
	public void setOrSNo(String orSNo) {
		this.orSNo = orSNo;
	}
	public String getOrONo() {
		return orONo;
	}
	public void setOrONo(String orONo) {
		this.orONo = orONo;
	}
	public String getCrNo() {
		return crNo;
	}
	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	public String getSubModule() {
		return subModule;
	}
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	
		
	
}
