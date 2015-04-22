<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="/tld/c.tld"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
<script type="text/javascript" src="js/expandingSection.js"></script> 
<script type="text/javascript" src="js/hideParameter.js"></script> 
<script type="text/javascript" src="js/deleteConfirmation.js"></script> 
  <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script>
	
	 <script type='text/javascript'>
	var startWith=1;
	var subMenu=0;
	</script> 	
<title>Customer Profile Form</title>
</head>
<body>
<s:if test="%{forWhat != 'print'}">
<div class="mainForm">

	<h4 class="title">CUSTOMER</h4>
	<s:form action="showCustomerProfileForm" validate="true" id="cusForm">
		<div class="form" id="wholeForm">
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
				
			</div>
			<div id="profileForm">
				<h3 class="form">PROFILE</h3>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Profile Details</th>
						</tr>
						<tr>
							<td width="100px"><s:textfield readOnly="readOnly" label="Customer ID:" name="customer.customerNo" id="customerId"></s:textfield></td>
							<s:hidden name="cusId" value="%{customer.customerNo}"/>
							<td><s:select disabled="%{forWhat}" label="Customer Type:" 
								list="#{'F':'Franchise','C':'Company Owned','CC':'Commissary'}" 
								name="customer.customerType"/></td>
							<td><s:textfield disabled="%{forWhat}" label="TIN Number:" name="customer.tin" size="30"></s:textfield></td>
						</tr>
					</table>
					<table class="form">
						<tr>
							<td colspan ="4"><s:textfield disabled="%{forWhat}" label="Customer Name:" size="90" name="customer.customerName"></s:textfield>
							<c:if test="${not empty customer.emailAddress}">
							<a id="emailLink" onclick="javascript:checkEmailAddress();" href="mailto:<s:property value="%{customer.emailAddress}"/>">eMail Me</a>
							</c:if>
							</td>
							
						</tr>
					</table>
					
				</p>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Contact Details</th>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Contact Name:" name="customer.contactName" size="50"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Contact Title:" name="customer.contactTitle" size="30"></s:textfield></td>
							<td><s:textfield disabled="%{forWhat}" label="Email Address:" id="emailAdd" name="customer.emailAddress" size="30"></s:textfield></td>
						
						</tr>
					</table>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Website:" name="customer.website" size="90" id="website"></s:textfield>
							<c:if test="${not empty customer.website}">
							<a onclick="javascript:checkWeb();" href="<s:property value="%{customer.website}"/>">my website</a>
							</c:if>
							</td>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Address:" name="customer.billingAddress" size="90"></s:textfield></td>
						</tr>
					</table>
					<table class="form">
						<tr>
							<th colspan="6">Contact Number Details</th>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Phone Number: " onfocus="parsePhoneNumber(this)" onblur="formatPhoneNumber(this)" name="customer.phoneNumber"></s:textfield></td>
							<td><s:textfield disabled="%{forWhat}" label="Fax Number: " onfocus="parsePhoneNumber(this)" onblur="formatPhoneNumber(this)" name="customer.faxNumber"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Mobile Number: " onfocus="parseMobileNumber(this)" onblur="formatMobileNumber(this)" name="customer.mobileNumber"></s:textfield>
							<s:hidden name="customerStockLevelMap" value="%{customer.customerStockLevelMap}"/>	
							</td>
							
						</tr>
						
					</table>
					
				</p>			
			</div>
		</div>
		
		<div class="forButtons">
			<p>
				<table class="forButtons" align="center">	
					<tr><s:hidden name="subModule" value="profile"/>
					
					<s:if test="%{forWhatDisplay == 'edit'}">
						<td><input class="myButtons" type="button" onclick="javascript:toggleAlert('profileForm','customerId');" value="Edit" ></input></td>
						<td><s:submit disabled="%{forWhat}" id="bUpdate" cssClass="myButtons" type="button" value="Update" action="updateCustomerAction"></s:submit></td>
						<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:customerConfirmation('cusForm')"></s:submit></td>
						<td><s:submit cssClass="myButtons" type="button" value="Print"  action="printCustomerAction"></s:submit></td>
						<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','cusForm');" value="New Entry"></input></td>
						<td><s:submit cssClass="myButtons" type="button" value="Manage Stock Level"  action="showManageStockLevelPageAction"></s:submit></td>
					
					</s:if>
					<s:elseif test="%{forWhat == 'print'}">
					
					</s:elseif>
					<s:else>
						<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" action="addCustomerAction"></s:submit></td>
						<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','cusForm');" value="Cancel"></input></td>
					
					</s:else>
					
					</tr>
				</table>
			</p>
		</div>
	</s:form>
</div>
</s:if>

<s:else>
<div class="print">
	<jsp:include page="/jsp/util/companyHeader.jsp"/>

		
<h3 class="form">PROFILE</h3>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Profile Details</th>
						</tr>
						<tr>
							<td width="100px"><s:textfield label="Customer ID:" value="%{customer.customerNo}"></s:textfield></td>
							<td><s:textfield label="Customer Type:" 
								value="%{customer.customerType}"/></td>
						</tr>
					</table>
					<table class="form">
						<tr>
							<td colspan ="4"><s:textfield label="Customer Name:" size="90" value="%{customer.customerName}"></s:textfield>
							
						</tr>
					</table>
				</p>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Contact Details</th>
						</tr>
						<tr>
							<td><s:textfield label="Contact Name:" value="%{customer.contactName}" size="50"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield label="Contact Title:" value="%{customer.contactTitle}" size="30"></s:textfield></td>
							<td><s:textfield label="Email Address:" value="%{customer.emailAddress}" size="30"></s:textfield></td>
						</tr>
					</table>
					<table class="form">
						
						<tr>
							<td><s:textfield label="Website:" value="%{customer.website}" size="90"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textarea cols="70" rows="3" label="Address:" value="%{customer.billingAddress}"></s:textarea></td>
						</tr>
					</table>
					<table class="form">
						<tr>
							<th colspan="6">Contact Number Details</th>
						</tr>
						<tr>
							<td><s:textfield label="Phone Number:" value="%{customer.phoneNumber}"></s:textfield></td>
							<td><s:textfield label="Fax Number:" value="%{customer.faxNumber}"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield label="Mobile Number:" value="%{customer.mobileNumber}"></s:textfield></td>							
						</tr>
					</table>
				</p>			
		</div>
</s:else>
</body>
</html>