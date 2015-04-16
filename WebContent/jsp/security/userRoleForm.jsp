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
	var startWith=6;
	var subMenu=2;
	</script>
<title>User Account Profile</title>
</head>
<body>
<s:if test="%{forWhat != 'print'}">
<div class="mainForm">
	<h4 class="title">User Role</h4>
	<s:form action="showUserRoleForm" validate="true" id="userRoleForm">
		<div class="form" id="wholeForm">
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
			
			</div>
			<div id="profileForm">
				<h3 class="form">User Role Profile</h3>
				<p>
					<table class="form">
						<tr>
							<th colspan="9">Profile Details</th>
						</tr>
						<tr>
							<td>
								<s:textfield label="Role Name:" name="role.userRoleName"></s:textfield></td>
								<s:hidden name="userRoleId" value="%{role.userRoleId}"/>
						</tr>
					</table>
				</p>
				<p>
					<table class="form">
						<tr>
							<th colspan="9">Role Details</th>
						</tr>
						<tr class="others" >
							<td colspan="9">SUPPLIER</td>
						</tr>
						<tr>
							<td>Supplier</td><td><s:select name="role.supplier" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>  
							<td>Supplier PO</td><td><s:select name="role.supplierPurchaseOrder" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Receiving Report</td><td><s:select name="role.supplierReceivingReport" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Supplier Invoice</td><td><s:select name="role.supplierInvoice" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Supplier Summary</td><td><s:select name="role.supplierSummary" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="9">CUSTOMER</td>
						</tr>
						<tr>
							<td>Customer</td><td><s:select name="role.customer" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Customer PO</td><td><s:select name="role.customerPurchaseOrder" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Delivery Receipt</td><td><s:select name="role.customerDeliveryReceipt" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Customer Invoice</td><td><s:select name="role.customerInvoice" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Customer Summary</td><td><s:select name="role.customerSummary" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						
						</tr>
						<tr class="others" >
							<td colspan="9">INVENTORY</td>
						</tr>
						<tr>
							<td>Raw Materials</td><td><s:select name="role.rawMaterial" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Traded Items</td><td><s:select name="role.tradedItem" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Finished Good</td><td><s:select name="role.finishedGood" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Order Requisition</td><td><s:select name="role.inventoryOR" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>FPTS</td><td><s:select name="role.inventoryFPTS" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Return Slip</td><td><s:select name="role.inventoryRS" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Inventory Summary</td><td><s:select name="role.inventorySummary" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="9">DISBURSEMENT</td>
						</tr>
						<tr>
							<td>Petty Cash</td><td><s:select name="role.disbursementPettyCash" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Cash Payment</td><td><s:select name="role.disbursementCashPayment" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Check Payment</td><td><s:select name="role.disbursementCheckPayment" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Check Voucher</td><td><s:select name="role.disbursementCheckVoucher" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Disbursement Summary</td><td><s:select name="role.disbursementSummary" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="9">RECEIPT</td>
						</tr>
						<tr>
							<td>OR Sales</td><td><s:select name="role.receiptsOrSales" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>OR Others</td><td><s:select name="role.receiptsOrOther" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>Cash/Check Receipts</td><td><s:select name="role.receiptsCheck" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Receipts Summary</td><td><s:select name="role.receiptSummary" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="9">FINANCIALS</td>
						</tr>
						<tr>
							<td>Account Entry Profile</td><td><s:select name="role.accountEntryProfile" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Journal Entry Profile</td><td><s:select name="role.journalEntryProfile" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>Financial Reports</td><td><s:select name="role.financialReport" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr class="others" >
							<td colspan="9">TOOLS</td>
						</tr>
						<tr>
							<td>Template Generator</td><td><s:select name="role.templateGenerator" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
							<td>User Profile</td><td><s:select name="role.userProfile" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
						<tr>
							<td>User Role</td><td><s:select name="role.userRole" list="#{'NOT ALLOWED':'NOT ALLOWED','ALLOWED':'ALLOWED'}"></s:select></td>
						</tr>
					</table>
				</p>			
			</div>
		</div>

		<div class="forButtons">
			<p>
				<table class="forButtons" align="center">	
					<tr>
						<s:hidden name="subModule" value="userRole"/>
						<s:if test="%{forWhatDisplay == 'edit'}">
						
						<td><input class="myButtons" name="edit" type="button" onclick="javascript:toggleAlert('profileForm','supplierId');" value="Edit"></input></td>  
						<!--<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:securityConfirmation('userRoleForm');"></s:submit></td> -->
						<!--<td><s:submit cssClass="myButtons" type="button" value="Print" action="printSupplierAction"></s:submit></td> -->
							<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="Update" id="bUpdate" action="updateSecurityAction"></s:submit></td>
							<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','userRoleForm');" value="New Entry"></input></td>
						</s:if>
						<s:else>
							<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" name="newUserProfile" action="addSecurityAction"></s:submit></td>
							<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','userRoleForm');" value="Cancel"></input></td>
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
	<h3 class="form">PROFILE</h3>
			
	</div>
</s:else>
</body>
</html>