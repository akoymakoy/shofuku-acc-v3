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
<script type="text/javascript" src="js/expandingSection.js"></script>
<link rel="stylesheet" href="menu.css" type="text/css" />
<script type="text/javascript" src="js/expandingMenu.js"></script>
<script type="text/javascript" src="js/hideParameter.js"></script>
<sx:head parseContent="true"></sx:head>
	 <script type='text/javascript'>
	var startWith=3;
	var subMenu=5;
	</script>
<title>Supplier Summary Search Form</title>
</head>

<body>
	<div class="mainForm">
		<h4 class="title">DISBURSEMENT SUMMARY</h4>
		<div class="form">
			<s:form action="generateSummaryAction" validate="true"
				id="searchForm">
				<div>
					<s:hidden name="clicked" id="clicked" value="true" />
					<s:set name="disbursementModule" value="disbursementModule" />
					<p>
					<table class="form">
						<tr>
							<td><s:select label="Disbursement Module:" headerKey="1"
									
									list="#{'PettyCash':'Petty Cash', 'CashPayment':'Cash Payment', 'CheckPayment':'Check Payment'}"
									name="disbursementModule" id="disbursementModule"
									onchange="javascript:showSearchByRefrenceNo();"/></td>
							<td colspan="2" style="text-align: left;"><s:submit
									label="Generate Report" value="Generate and Export Report"></s:submit></td>
						</tr>

					</table>
					<p style="margin-top: 40px"></p>
					
					<div>
						<table class="form" id="pettyCashSearchTypeTbl">
							<tr>
								<td><s:select label="Search By::" headerKey="none"
										list="#{'ByRef':'By Reference No', 'ByDate':'By Date'}"
										name="pettyCashSearchType" id="pettyCashSearchType"
										onchange="javascript:changePettyCashType();" /></td>
							</tr>
						</table>
					</div>
					
					<div>
						<table class="results" id="searchByReference"
							style="display: block;">
							<tr>
								<th align="center">PRINTING Summary Petty Cash with SAME
									Reference No:</th>
							</tr>
							<tr>
								<td>Choose one Reference No : <sx:autocompleter size="40" headerValue="--Choose Module--"
										resultsLimit="-1" list="referenceList" name="referenceNo"></sx:autocompleter></td>
							</tr>
						</table>
					</div>
					
					<div id="searchParameters" style="display: none;">
						
						<table class="form" cellpadding=5>
							<tr>
								<td colspan=4><h4 class="title">SELECT DATE RANGE & PAYEE/s TO BE INCLUDED IN THE REPORT:</h4></td>
							</tr>
							<tr>
								<td class="others">Date From:</td>
								<td><sx:datetimepicker displayFormat="MMM-dd-yyyy" displayWeeks="5" name="dateFrom"></sx:datetimepicker></td>
								<td class="others">Date To:</td>
								<td><sx:datetimepicker displayFormat="MMM-dd-yyyy" displayWeeks="5" name="dateTo"></sx:datetimepicker></td>
							</tr>
							<tr>
								<td>Tick this box for simple format of report: </td>
								<td> <s:checkbox id="isFormatReport" name="isFormatReport" onclick="javascript:showFilterTable();"></s:checkbox> </td>
							</tr>
						</table>
					</div>
					<div id="filterTbl" style="display: none;">	
						<table class="form" cellpadding=5>		
							<tr>
								<td>Choose at least one Payee:</td>
								
								<td>
									<select id="pettyList" name="pettyList" multiple="multiple" style="display: none;">
									<c:forEach items="${pettyList}" var="payee">
										<option value="${payee}">${payee}</option>
									</c:forEach>
									</select>
									<select id="cashList" name="cashList" multiple="multiple" style="display: none;">
									<c:forEach items="${cashList}" var="payee">
										<option value="${payee}">${payee}</option>
									</c:forEach>
									</select>
									<select id="checkList" name="checkList" multiple="multiple" style="display: none;">
									<c:forEach items="${checkList}" var="payee">
										<option value="${payee}">${payee}</option>
									</c:forEach>
									</select>
								</td>
								
							</tr>
							
						</table>
					</div>
					</p>
				</div>
				<div class="errors">
					<s:actionerror />
					<s:actionmessage />
				</div>
			</s:form>
		</div>
	</div>

</body>
</html>