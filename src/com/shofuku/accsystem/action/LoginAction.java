package com.shofuku.accsystem.action;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;


public class LoginAction extends ActionSupport {
/**
	 * <p> Validate a user login. </p>
	 */
		 
	public String execute() throws Exception {
	  
		if (getUsername().length() == 0 || getPassword().length() ==0 ){
			validate(); 
			
		}
		else if(!getUsername().equals("Admin") || !getPassword().equals("Admin")){
			addActionError("Invalid user name or password! Please try again!");
	  
			return ERROR;
		}
	  if(getUsername().equals("Admin") && getPassword().equals("Admin")){
		  return SUCCESS;
	  }else{
		  return NONE;
	  }
	}


	  // ---- Username property ----

	  /**
	 * <p>Field to store User username.</p>
	 * <p/>
	 */
	  private String username = null;


	  /**
	 * <p>Provide User username.</p>
	 *
	 * @return Returns the User username.
	 */
	  public String getUsername() {
	  return username;
	  }

	  /**
	 * <p>Store new User username</p>
	 *
	 * @param value The username to set.
	 */
	  public void setUsername(String value) {
	  username = value;
	  }

	  // ---- Username property ----

	  /**
	 * <p>Field to store User password.</p>
	 * <p/>
	 */
	  private String password = null;


	  /**
	 * <p>Provide User password.</p>
	 *
	 * @return Returns the User password.
	 */
	  public String getPassword() {
	  return password;
	  }

	  /**
	 * <p>Store new User password</p>
	 *
	 * @param value The password to set.
	 */
	  public void setPassword(String value) {
	  password = value;
	  }
	  
	  @Override
		public void validate() {
			if (getUsername().length() == 0){
				addFieldError("username", "Required: username");
				
			}
			if (getPassword().length() == 0){
				addFieldError("password", "Required: password");
			}
		}

}


