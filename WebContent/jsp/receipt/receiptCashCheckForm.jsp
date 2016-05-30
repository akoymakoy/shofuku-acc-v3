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
 <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
	<script type="text/javascript" src="js/deleteConfirmation.js"></script> 
	 <script type="text/javascript" src="js/Transactions.js"></script>
	 <script type='text/javascript'>
	var startWith=4;
	var subMenu=2;
	</script>
<sx:head parseContent="true"/>
<title>Cash/Check Receipts</title>
</head>
<body>
<s:if test="%{forWhat != 'print'}">
<div class="mainForm">
<h4 class="title">RECEIPTS</h4>
	
	<s:form action="newReceiptEntry" validate="true" id="ccForm">
	<div class="form" id="wholeForm">
	<div class="errors">
		<s:actionerror/>
		<s:actionmessage/>
		
	</div>
		<div id="receiptForm">
		<h3 class="form">Cash/Check Payments</h3>
		
			<p>
				<table class="form">
					<tr>
						<th colspan="6">Payment Details</th>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" readOnly="readOnly" label="Cash/Check Receipt No.:" name="ccReceipts.cashReceiptNo" id="crId"/></td>
						<s:hidden name="crNo" value="%{ccReceipts.cashReceiptNo}"/>
						<td class="others">Cash/Check Receipt Date:</td>
						<td><sx:datetimepicker name="ccReceipts.cashReceiptDate" displayFormat="MMM-dd-yy" displayWeeks="5"/></td>
					</tr>
					</table>
					<table class="form">
					<tr>
					<td><s:textfield disabled="%{forWhat}" label="Payee:" size="90" name="ccReceipts.payee"/></td>
					</tr>
					<tr>
					<td><s:textfield disabled="%{forWhat}" label="Particulars:" size="90" name="ccReceipts.particulars"/></td>
					</tr>
					<tr>
						<td width="150px"><s:textfield disabled="%{forWhat}" label="Total Amount: PHP" name="ccReceipts.amount"></s:textfield></td>
					</tr>
					</table>
				<table class="form">
						<tr>
							<th colspan="6">VAT Details</th>
						</tr>
					<tr>
						<!--START: 2013 - PHASE 3 : PROJECT 4: AZ-->
						<td><s:textfield readonly="true" label="Vatable Amount: PHP" name="ccReceipts.vatDetails.vattableAmount"  id="credit1Amount"></s:textfield></td>
						<td><s:textfield readonly="true" label="VAT Amount: PHP" name="ccReceipts.vatDetails.vatAmount"></s:textfield></td>
						<!--END: 2013 - PHASE 3 : PROJECT 4: AZHEE-->
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="TIN:" name="ccReceipts.vatDetails.tinNumber"></s:textfield></td>
						<td><s:textfield disabled="%{forWhat}" label="Reference No:" name="ccReceipts.vatDetails.orNo"></s:textfield></td>
					</tr>
				</table>
					<table class="form">
						<tr>
							<td>Address:</td>
							<td ><s:textfield size="90" disabled="%{forWhat}" name="ccReceipts.vatDetails.address"></s:textfield></td>
						</tr>
					</table>	
				<table class="form">
					<tr>
						<th colspan="6">Check Details</th>
					</tr>
					<tr>
						
						<td><s:textfield disabled="%{forWhat}" label="Check No" name="ccReceipts.checkNo"></s:textfield></td>
						<td><s:textfield disabled="%{forWhat}" label="Check Remarks:" name="ccReceipts.checkRemarks"></s:textfield></td>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Bank Name:" name="ccReceipts.bankName"></s:textfield></td>
						<td><s:textfield disabled="%{forWhat}" label="Bank Account No.:" name="ccReceipts.bankAccountNo"></s:textfield></td>
					</tr>
					<s:if test="%{forWhat == 'print'}">
					<tr>
						<td><s:label label="Received By:"></s:label></td>
					</tr>
					</s:if>
				</table>
				<s:if test="%{forWhatDisplay == 'edit'}">			
					<!--START 2013 - PHASE 3 : PROJECT 1: MARK -->
			<div id ="transactions">
			<table class="form">
				<tr>
					<th>Accounting Entries</th>
				</tr>
			</table>
			<table class="results" id="Transactions">
					<tr class="others">
					<th class="desc">Delete?</th>
					<th class="desc">Accounting Profile</th>
					<th class="desc">Amount</th>
					<th class="desc">Debit/Credit</th>
					</tr>
					<c:forEach items="${transactionList}" var="transactions" varStatus="ctr">
					<tr>
						<td><input type="checkbox" name="chk"/></td>
						<td>
							<select  id="parentCode" name="transactionList[${ctr.index}].accountEntry.accountCode" onChange = "javascript:showTransactionAction([${ctr.index}],this)">
								<c:forEach items="${accountProfileCodeList}" var="profile">
									<option ${profile.accountCode == transactions.accountEntry.accountCode ? 'selected="selected"' : ''}
										value="${profile.accountCode}">${profile.name} [${profile.accountCode}]</option>
								</c:forEach>
							</select>
						</td>
					 	<td><input type="text" name="transactionList[${ctr.index}].amount" value="${transactions.amount}"/></td>
					<c:forEach items="${accountProfileCodeList}" var="profile">
					 		<input type="hidden" name="accountCodeActions.${profile.accountCode}" id="accountCodeActions.${profile.accountCode}" value="${profile.accountingRules.receiptsCheck}"/>
						</c:forEach>
					 	<td><input size="10" readonly="readonly" type="text" id="transactionList[${ctr.index}].transactionAction" name="transactionList[${ctr.index}].transactionAction" value="${transactions.transactionAction}"/></td>
					
					</tr>
					</c:forEach>
				</table>
				<table>
					<tr>
						<td><input class="myButtons" type="button" value="add" onclick="javascript:addRow('Transactions')"></input></td>
						<td><input class="myButtons" type="button" value="delete" onclick="javascript:deleteRow('Transactions')"></input></td>
					</tr>
				</table>
				<p>
			</div>
			</s:if>
		</div>
	</div>
	
	<div class="forButtons">
<p>
		<table class="forButtons" align="center">
			
				<tr>
				    <s:hidden name="subModule" value="cashCheckReceipts"/>
			
				<s:if test="%{forWhatDisplay == 'edit'}">
				
					<td><input class="myButtons" type="button" onclick="javascript:toggleAlert('receiptForm','crId');" value="Edit"></input></td>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" id="bUpdate" value="Update" action="updateReceiptAction"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:receiptConfirmation('ccForm');"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Print" action="printReceiptAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','ccForm');" value="New Entry"></input></td>
				
				</s:if>
				<s:else>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" action="addReceiptAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','ccForm');" value="Cancel"></input></td>
				</s:else>
			</tr>
		</table>
</p>
</div>
</s:form>
</div>
</s:if>
<!--  div print -->
<s:else>
<div class="print">
	<jsp:include page="/jsp/util/companyHeader.jsp"/>

		<h3 class="form">Cash/Check Payments</h3>
		
			<p>
				<table class="form">
					<tr>
						<th colspan="6">Payment Details</th>
					</tr>
					<tr>
						<td><s:textfield label="Cash/Check Receipt No.:" value="%{ccReceipts.cashReceiptNo}"/></td>
						<td><s:textfield label="Cash/Check Receipt Date:" value="%{ccReceipts.cashReceiptDate}" displayFormat="MMM-dd-yyyy"/></td>
					</tr>
					</table>
					<table class="form">
					<tr>
						<td ><s:textfield size="90" label="Payee:" value="%{ccReceipts.payee}"/></td>
					</tr>
					<tr>
						<td ><s:textarea cols="70" rows="3"  label="Particulars:" value="%{ccReceipts.particulars}"/></td>
					</tr>
					<tr>
						<td><s:textfield  label="Total Amount: PHP" value="%{ccReceipts.amount}"></s:textfield></td>
					
					</tr>
					</table>
				<table class="form">
						<tr>
							<th colspan="6">VAT Details</th>
						</tr>
					<tr>
						<!--START: 2013 - PHASE 3 : PROJECT 4: AZ-->
						<td><s:textfield readonly="true" label="Vatable Amount: PHP" value="%{ccReceipts.vatDetails.vattableAmount}"></s:textfield></td>
						<td><s:textfield readonly="true" label="VAT Amount: PHP" value="%{ccReceipts.vatDetails.vatAmount}"></s:textfield></td>
						<!--END: 2013 - PHASE 3 : PROJECT 4: AZHEE-->
					</tr>
					<tr>
						<td><s:textfield readonly="true" label="TIN:" value="%{ccReceipts.vatDetails.tinNumber}"></s:textfield></td>
						<td><s:textfield readonly="true" label="Reference No:" value="%{ccReceipts.vatDetails.orNo}"></s:textfield></td>
					</tr>
				</table>
				<table class="form">
					<tr>
						<th colspan="6">Check Details</th>
					</tr>
					<tr>
						<td><s:textfield  label="Check No" value="%{ccReceipts.checkNo}"></s:textfield></td>
						<td><s:textfield  label="Check Remarks:" value="%{ccReceipts.checkRemarks}"></s:textfield></td>
					</tr>
					<tr>
						<td><s:textfield  label="Bank Name:" value="%{ccReceipts.bankName}"></s:textfield></td>
						<td><s:textfield  label="Bank Account No.:" value="%{ccReceipts.bankAccountNo}"></s:textfield></td>
					</tr>
					
				</table>
				<p></p>
				<table>
					<tr>
						<td class="others">RECEIVED BY:</td>
					</tr>
					<tr>
						<td style="padding-top:10px;">_________________________</td>
					</tr>
				</table>
	</div>
</s:else>

</body>
</html>