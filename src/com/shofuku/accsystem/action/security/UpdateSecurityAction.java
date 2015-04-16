package com.shofuku.accsystem.action.security;

import java.util.List;

import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.shofuku.accsystem.controllers.SecurityManager;
import com.shofuku.accsystem.domain.security.UserAccount;
import com.shofuku.accsystem.domain.security.UserRole;
import com.shofuku.accsystem.utils.HibernateUtil;
import com.shofuku.accsystem.utils.SASConstants;

public class UpdateSecurityAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 934004434406221113L;
	String subModule;
	UserAccount user;
	UserRole role;
	private String forWhat;
	private String forWhatDisplay;
	
	private int userId;
	private int userRoleId;
	List roleList = null;
	String tempPassword;
	
	SecurityManager securityManager = new SecurityManager();
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

public String execute() throws Exception {
	Session session = getSession();
	try {
		forWhatDisplay="edit";
		if (getSubModule().equalsIgnoreCase("userAccount")) {
			return updateUserAccount(session);
		}else {
			return updateUserRole(session);
		}
	
	}catch (RuntimeException re) {
		re.printStackTrace();
		if (getSubModule().equalsIgnoreCase("userAccount")) {
			return "userAccountUpdated";
		}else {
			return "userRoleUpdated";
		}
		
	}finally {
		if(session.isOpen()){
			session.close();
			session.getSessionFactory().close();
		}
	}
	
}

private String updateUserRole(Session session) {
	// TODO Auto-generated method stub
	boolean updateResult = false;
	role.setUserRoleId(userRoleId);
if (validateUserRoleAccount()) {
	//	loadRoleList();
	}else {
		
			updateResult = securityManager.updateSecurity(role, session); 
			if (updateResult == true) {
				addActionMessage(SASConstants.UPDATED);
				forWhat="true";
				forWhatDisplay ="edit";
			} else {
				addActionError(SASConstants.FAILED);
				}
		}
return "userRoleUpdated";
}

private boolean validateUserRoleAccount() {
	// TODO Auto-generated method stub
	boolean errorFound = false;
	
	if ("".equals(role.getUserRoleName())) {
		 addFieldError("role.userRoleName", "REQUIRED");
		 errorFound = true;
	}
	return errorFound;
}

private boolean validateUserAccount() {
	boolean errorFound = false;
	
	if ("".equals(user.getUserName())) {
		 addFieldError("user.userName", "REQUIRED");
		 errorFound = true;
	}if ("".equals(user.getPassword())) {
		 addFieldError("user.password", "REQUIRED");
		 errorFound = true;
	}if ("".equals(getTempPassword())) {
		 addFieldError("tempPassword", "REQUIRED");
		 errorFound = true;
	}else {
	 if (!(tempPassword.equals(user.getPassword()))) {
		 addFieldError("tempPassword", "PASSWORDS did not match");
		 errorFound = true;
	 	}
	}
	return errorFound;
}

public String loadRoleList() {
	// TODO Auto-generated method stub
	Session session = getSession();
	roleList= securityManager.listAlphabeticalAscByParameter(UserRole.class, "userRoleId",  session);
	return "userAccount";
}

private String updateUserAccount(Session session) {
	// TODO Auto-generated method stub
	boolean updateResult = false;
	user.setUserId(userId);
	if (validateUserAccount()) {
		loadRoleList();
	}else {
		UserRole userRole = (UserRole) securityManager.listSecurityByParameter(UserRole.class, "userRoleName", user.getRole().getUserRoleName(), session).get(0);
		this.user.setRole(userRole);
			updateResult = securityManager.updateSecurity(user, session);
			if (updateResult == true) {
					addActionMessage(SASConstants.UPDATED);
					forWhat="true";
					forWhatDisplay ="edit";
				} else {
					addActionError(SASConstants.FAILED);
					}
				}
	
	loadRoleList();
return "userAccountUpdated";
}


public String getSubModule() {
	return subModule;
}

public void setSubModule(String subModule) {
	this.subModule = subModule;
}

public UserAccount getUser() {
	return user;
}

public void setUser(UserAccount user) {
	this.user = user;
}

public UserRole getRole() {
	return role;
}

public void setRole(UserRole role) {
	this.role = role;
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

public List getRoleList() {
	return roleList;
}

public void setRoleList(List roleList) {
	this.roleList = roleList;
}

public String getTempPassword() {
	return tempPassword;
}

public void setTempPassword(String tempPassword) {
	this.tempPassword = tempPassword;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getUserRoleId() {
	return userRoleId;
}

public void setUserRoleId(int userRoleId) {
	this.userRoleId = userRoleId;
}


}
