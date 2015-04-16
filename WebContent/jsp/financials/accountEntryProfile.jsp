<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="/tld/c.tld"%>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
<script type="text/javascript" src="js/hideParameter.js"></script>
<script type="text/javascript" src="js/deleteConfirmation.js"></script>
<script type="text/javascript" src="js/expandingSection.js"></script> 
 <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
	 <script type='text/javascript'>
	var startWith=5;
	var subMenu=0;
	</script>
<title>Account Entry Profile Form</title>
</head>
<body>
<s:if test="%{forWhatDisplay != 'print'}">
<div class="mainForm">
	<h4 class="title">Account Entry Profile</h4>
	<s:form action="loadParentCodeAction" validate="true" id="accountEntryProfileForm">
		<div class="form" id="wholeForm">
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
			
			</div>
			<div id="profileForm">
				<h3 class="form">Account Entry Profile</h3>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Profile Details</th>
						</tr>
						<tr>
							<c:set var="aepCode" value="${aep.accountCode}"/>
							<td> <label>Account Code: </label>	<input type="text" name="aep.accountCode" value ="${aepCode}" id="accountId" ><td>
							<s:hidden name="accId" value="%{aep.accountCode}"/>
							
							<td class="others">Parent Account:</td>
							<s:if test="%{forWhatDisplay != 'edit'}">
							<td>
									 <select id="parentCode" name="aep.parentCode">
									 	<option value="none">SELECT PARENT CODE</option>
										<c:forEach items="${accountCodeList}" var="profile">
											<option ${profile.accountCode == aep.parentCode ? 'selected' : ''}
												value="${profile.accountCode}">${profile.accountCode} - ${profile.name}</option>
										</c:forEach>
								</select>
							</td> 
							</s:if>
							<s:else>
								<td>
									 <select id="parentCodeReadOnly" name="aep.parentCode" disabled="disabled">
										<c:forEach items="${accountCodeList}" var="profile">
											<option ${profile.accountCode == aep.parentCode ? 'selected' : ''}
												value="${profile.accountCode}">${profile.accountCode} - ${profile.name}</option>
										</c:forEach>
								</select>
								<s:hidden name="parentCode" value ="%{aep.parentCode}"/> 
							</td>  
							</s:else>			
						</tr>
					</table>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Account Name:" name="aep.name" size = "50" ></s:textfield></td> 			
						</tr>
						<tr>
							<td><s:select disabled="%{forWhat}" label="Account Classification:" name="aep.classification" list="#{'ASSET':'ASSET','LIABILITIES':'LIABILITIES','SHAREHOLDERS EQUITY':'SHAREHOLDERS EQUITY','COST OF GOODS SOLD':'COST OF GOODS SOLD','EXPENSES':'EXPENSES'}"></s:select></td> 			
						
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" readOnly="readOnly" label="Hierarchy" name="aep.hierarchy" size = "50"></s:textfield></td> 			
						</tr>
						<tr>	
						</tr>
						
					</table>
					<table class="form">
						<tr>
							<td><s:select disabled="%{forWhat}" label="Report Type:" name="aep.reportType" list="#{'NONE':'NONE','INCOME STATEMENT':'Income Statement','BALANCE SHEET':'Balance Sheet'}"></s:select></td> 			
						
							<td><s:select disabled="%{forWhat}" label="FS Balance :" name="aep.reportAction" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td> 			
						
						<td><s:select disabled="%{forWhat}" label="Active? (Y/N):" name="aep.isActive" 
							 list="#{'ACTIVE':'ACTIVE','NOT_ACTIVE':'INACTIVE'}"></s:select></td> 		
						</tr>
					</table>
					
					<table class="form">
					<tr>
					<td>Total Amount Per Account</td>
					<td>
					<s:textfield name="tempTotalAmountPerAccount"/>
					</td></tr>
					</table>
					
					<table class="form">
						<tr>
							<th colspan="6">Accounting Entry Rules</th>
						</tr>
						<tr class="others" >
							<td colspan="6">SUPPLIER</td>
						</tr>
						<tr>
							<td>Receiving Report</td><td><s:select name="aep.accountingRules.supplierReceivingReport" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>Supplier Invoice</td><td><s:select name="aep.accountingRules.supplierInvoice" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="6">CUSTOMER</td>
						</tr>
						<tr>
							<td>Delivery Receipt</td><td><s:select name="aep.accountingRules.customerDeliveryReceipt" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>Customer Invoice</td><td><s:select name="aep.accountingRules.customerInvoice" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="6">INVENTORY</td>
						</tr>
						<tr>
							<td>FPTS</td><td><s:select name="aep.accountingRules.inventoryFPTS" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>Order Requisition</td><td><s:select name="aep.accountingRules.inventoryOR" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr>
							<td>Return Slip</td><td><s:select name="aep.accountingRules.inventoryRS" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="6">DISBURSEMENT</td>
						</tr>
						<tr>
							<td>Petty Cash</td><td><s:select name="aep.accountingRules.disbursementPettyCash" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>Cash Payment</td><td><s:select name="aep.accountingRules.disbursementCashPayment" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr>
							<td>Check Payment</td><td><s:select name="aep.accountingRules.disbursementCheckPayment" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>Check Voucher</td><td><s:select name="aep.accountingRules.disbursementCheckVoucher" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="6">RECEIPT</td>
						</tr>
						<tr>
							<td>OR Sales</td><td><s:select name="aep.accountingRules.receiptsOrSales" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
							<td>OR Others</td><td><s:select name="aep.accountingRules.receiptsOrOther" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
						<tr>
							<td>Cash/Check Receipts</td><td><s:select name="aep.accountingRules.receiptsCheck" list="#{'NONE':'NONE','DEBIT':'DEBIT','CREDIT':'CREDIT'}"></s:select></td>
						</tr>
					</table>
				</p>
					
			</div>
		</div>

		<div class="forButtons">
			<p>
				<table class="forButtons" align="center">	
				<tr>
				<s:hidden name="subModule" value="accountEntryProfile"/>
				<s:if test="%{forWhatDisplay == 'edit'}">
				
					<td><input class="myButtons" name="edit" type="button" onclick="javascript:toggleAlert('accountEntryProfileForm','accId');" value="Edit"></input></td>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="Update" id="bUpdate" action="updateFinancialsAction"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:financialsConfirmation('accountEntryProfileForm');"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Print" action="printFinancialsAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','accountEntryProfileForm');" value="New Entry"></input></td>
				
				</s:if>
				<s:else>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" name="newAccountEntryProfile" action="addFinancialsAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','accountEntryProfileForm');" value="Cancel"></input></td>
				   
				</s:else>
					</tr>
				</table>
			</p>
		</div>
	</s:form>
</div>
</s:if>

<!-- div for print -->
<s:else>
<div class="print">
<jsp:include page="/jsp/util/companyHeader.jsp"/>
	<h3 class="form">Account Entry Profile</h3>
		<p>
				<table class="form">
						<tr>
							<th colspan="6">Account Details</th>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Account Code:" value ="%{aep.accountCode}"></s:textfield> <td>
							<td><s:textfield disabled="%{forWhat}" label="Parent Account:" value ="%{aep.parentCode}" ></s:textfield> <td>
						</tr>
					</table>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Account Name:" value="%{aep.name}" size = "50" ></s:textfield></td> 			
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Account Classification" value="%{aep.classification}" size = "50"></s:textfield></td> 			
						</tr>
						<tr>	
						</tr>
						
					</table>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Report Type:" value="%{aep.reportType}" ></s:textfield></td> 			
						
							<td><s:textfield disabled="%{forWhat}" label="FS Balance :" value="%{aep.reportAction}" ></s:textfield></td> 			
						
						<td><s:textfield disabled="%{forWhat}" label="Active? (Y/N):" value="%{aep.isActive}"></s:textfield></td> 			
								
						</tr>
						
					</table>
			</p>	
	</div>
</s:else>
</body>
</html>