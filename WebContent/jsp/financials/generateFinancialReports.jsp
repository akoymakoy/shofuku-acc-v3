<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="c" uri="/tld/c.tld"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/financials.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
<script type="text/javascript" src="js/hideParameter.js"></script>
<script type="text/javascript" src="js/deleteConfirmation.js"></script>
 <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
  <sx:head parseContent="true"></sx:head>
 
	 <script type='text/javascript'>
	var startWith=5;
	var subMenu=2;
	</script>
<title>Generate Financial Reports</title>
</head>
<body>

	<div class="mainForm">
		<h4 class="title">Financial Reports</h4>

		<div class="form">

			<s:form action="generateFinancialReportsAction" id="generateFinancialReportsAction">
				<div class="errors">
					<s:actionerror />
					<s:actionmessage />
				</div>
				<div>
					<p>
					<table class="form">
						<tr>
							<td><s:select label="Select a report type:"
									list="#{'00':'---Select One---','01':'Ledger Account','02':'Trial Balance','03':'Income Statement','04':'Balance Sheet','06':'Cost Of Goods Manufactured & Good Sold','07':'Schedule of Changes in Stockholders Equity','08':'General Journal Report','09':'Vat Report'}"
									name="reportType" id="reportType"
									onchange="javascript:showRequiredFieldst(this);" /></td>
							<td colspan="2" style="text-align: left;"><s:submit	label="Generate Report" value="Generate Report"	method="execute"></s:submit></td>
						</tr>
					</table>
					<br />
						<div id="generalReportParameters" style="display: none;">
							<table class="form" cellpadding=5>
							<tr>
								<td colspan=4><h4 class="title">DATE RANGE :</h4></td>
							</tr>
							<tr>
								<td class="others">Date From:</td>
								<td><sx:datetimepicker displayFormat="MMM-dd-yyyy"
										displayWeeks="5" name="dateFrom"></sx:datetimepicker></td>
								<td class="others">Date To:</td>
								<td><sx:datetimepicker displayFormat="MMM-dd-yyyy"
										displayWeeks="5" name="dateTo"></sx:datetimepicker></td>
							</tr>
							</table>
						</div>
						<div id="ledgerAccountReportParameters" style="display: none;">
							<table class="form" cellpadding=5>
									<tr>
										<td>Choose at least one Account Profile:</td>
										<td><select id="accountCodeList" name="accountCodeList" multiple="multiple" size="15">
												<c:forEach items="${accountCodeList}" var="accountProfile">
													<option value="${accountProfile.accountCode}">${accountProfile.accountCode} - ${accountProfile.name}</option>
												</c:forEach>
										</select></td>
									</tr>
							</table>
						</div>
						
						<div id="trialBalanceReportParameters" style="display: none;"></div>
						<div id="incomeStatementReportParameters" style="display: none;"></div>
						<div id="balanceSheetReportParameters" style="display: none;"></div>
						<div id="statementOfCostOfGoodsMfgReportParameters" style="display: none;"></div>
						<div id="statementOfCostOfGoodsSold" style="display: none;"></div>
						<div id="statementOfChangesInShareholdersEquityReportParameters" style="display: none;"></div>
						<div id="journalEntriesReportParameters" style="display: none;"></div>
						<div id="vatReportReportParameters" style="display: none;"></div>
						<div id="checkEncashmentReportParameters" style="display: none;"></div>
						
					</div>
			</s:form>
		</div>
	</div>
</body>
</html>