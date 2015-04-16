package com.shofuku.accsystem.action.security;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.shofuku.accsystem.controllers.SecurityManager;
import com.shofuku.accsystem.domain.security.UserAccount;
import com.shofuku.accsystem.domain.security.UserRole;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.SASConstants;

public class SearchSecurityAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5977333283601416207L;

	private String securityModule;
	private String moduleParameter;
	private String moduleParameterValue;
	
	private String clicked;
	List securityList;

	SecurityManager manager = new SecurityManager();

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	public String execute() throws Exception{
		Session session = getSession();
		try {
			if (getClicked().equals("true")) {

				if (null != getModuleParameter()&& getSecurityModule().equalsIgnoreCase("userAccount")) {
					
					if (getModuleParameter().equalsIgnoreCase("userName") || getModuleParameter().equalsIgnoreCase("fullName")) {
						securityList = manager.listToolsByParameterLike(
										UserAccount.class, moduleParameter,
										moduleParameterValue,session);
					}else {
						securityList = manager.listAlphabeticalAscByParameter(UserAccount.class, "fullName", session);
						moduleParameterValue="all";
					} 
						if (securityList.size()==0) {
							addActionMessage(SASConstants.NO_LIST);
						}
						
					return "userAccount";
				} else if (null != getModuleParameter()&& getSecurityModule().equalsIgnoreCase("userRole")) {
					
					if (getModuleParameter().equalsIgnoreCase("userRoleName")) {
						securityList = manager.listToolsByParameterLike(
										UserRole.class, moduleParameter,
										moduleParameterValue,session);
					}else  {
						securityList = manager.listAlphabeticalAscByParameter(UserRole.class, "userRoleName", session);
						moduleParameterValue="all";
					} 
						if (securityList.size()==0) {
							addActionMessage(SASConstants.NO_LIST);
						}
						
					return "userRole";
				} 
			}
		moduleParameterValue="";
		return "populateList";
		
		} catch (RuntimeException re) {
			if (getSecurityModule().equals("userAccount")){
				return "userAccount";
			}else {
				return "userRole";
			}
		}finally {
			if(session.isOpen()){
				session.close();
				session.getSessionFactory().close();
			}
		}

	}
	public String getSecurityModule() {
		return securityModule;
	}
	public void setSecurityModule(String securityModule) {
		this.securityModule = securityModule;
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
	public String getClicked() {
		return clicked;
	}
	public void setClicked(String clicked) {
		this.clicked = clicked;
	}
	public List getSecurityList() {
		return securityList;
	}
	public void setSecurityList(List securityList) {
		this.securityList = securityList;
	}
	
}
