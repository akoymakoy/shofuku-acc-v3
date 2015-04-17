package com.shofuku.accsystem.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.shofuku.accsystem.controllers.CustomerManager;
import com.shofuku.accsystem.domain.customers.Customer;
import com.shofuku.accsystem.domain.customers.CustomerStockLevel;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.POIUtil;
import com.shofuku.accsystem.utils.SASConstants;

public class ManageStockLevelAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileUpload;
	Customer customer;
	String result;
	List stockLevelList;
	String cusId;
	CustomerManager manager = new CustomerManager();
	POIUtil poiUtil = new POIUtil();
	private String forWhat = "false";
	private String forWhatDisplay ="edit";
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public String execute() {
		Session session = getSession();
		
		customer = (Customer) manager.listByParameter(Customer.class, "customerNo", cusId, session).get(0);
		Iterator itr = customer.getCustomerStockLevelMap().keySet().iterator();
		stockLevelList = new ArrayList();
		while(itr.hasNext()) {
			String key = (String)itr.next();
			CustomerStockLevel csl = (CustomerStockLevel)customer.getCustomerStockLevelMap().get(key);
			stockLevelList.add(csl);
		}
		
	return "input";
	}
	
	public String readStockLevelExcel() {
		Session session = getSession();
		//customer = this.getCustomer();
		customer = (Customer) manager.listByParameter(Customer.class, "customerNo", cusId, session).get(0);
		
		if (null == fileUpload) {
			addActionError(SASConstants.NO_LIST);
		}else {
			customer = poiUtil.readCustomerStockLevelForm(customer, fileUpload, session);
			manager.updateCustomer(customer, session);
			
			Iterator itr = customer.getCustomerStockLevelMap().keySet().iterator();
			stockLevelList = new ArrayList();
			while(itr.hasNext()) {
				String key = (String)itr.next();
				CustomerStockLevel csl = (CustomerStockLevel)customer.getCustomerStockLevelMap().get(key);
				stockLevelList.add(csl);
			}
		}
		
		return "updated";
	}
	
	public String showCustomerProfile() {
		Session session = getSession();
			customer = (Customer) manager.listByParameter(Customer.class, "customerNo", cusId, session).get(0);
		return "showCustomer";
	}
	
	
	
	//getter and setter		
	public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List getStockLevelList() {
		return stockLevelList;
	}

	public void setStockLevelList(List stockLevelList) {
		this.stockLevelList = stockLevelList;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public String getForWhat() {
		return forWhat;
	}

	public void setForWhat(String forWhat) {
		this.forWhat = forWhat;
	}

	public String getForWhatDisplay() {
		return forWhatDisplay;
	}

	public void setForWhatDisplay(String forWhatDisplay) {
		this.forWhatDisplay = forWhatDisplay;
	}
	
	

}
