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
  <script type="text/javascript" src="js/onChangeType.js"></script>
  <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script>
	<script type="text/javascript" src="js/hideParameter.js"></script> 	
  <sx:head parseContent="true"></sx:head>
 	 <script type='text/javascript'>
	var startWith=4;
	var subMenu=4;
	</script>
<title>Receipt Summary Search Form</title>
</head>

<body>
<div class="mainForm">
	<h4 class="title">RECEIPT SUMMARY</h4>
	<div class ="form">
		<s:form action="generateSummaryAction" validate="true" id="searchForm">
			
			<div>
				<s:hidden name="clicked" id="clicked" value="true"/>
				<s:set name="receiptModule" value="receiptModule"/>
					<p>
						<table class="form">
							<tr>
								<td>
									<s:select label="Receipt Module:" headerKey="-1" 
								list="#{'ORSales':'OR Sales', 'OROthers':'OR Other Income', 'CashReceipts':'Cash Check Receipts'}" 
								name="receiptsModule" onchange="javascript:showReceivedFromList()" id="receiptsModule"/>
								</td>
								<td colspan="2" style="text-align:left;"><s:submit label="Generate Report" value="Generate and Export Report"></s:submit></td>
						</tr>
						</table>
						
						<div id="searchParameters" style="display: block;">
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
									<select id="orSalesList" name="orSalesList" multiple="multiple" style="display: block;">
									<c:forEach items="${orSalesList}" var="ors">
										<option value="${ors.receivedFrom}">${ors.receivedFrom}</option>
									</c:forEach>
									</select>
									<select id="orOthersList" name="orOthersList" multiple="multiple" style="display: none;">
									<c:forEach items="${orOthersList}" var="oro">
										<option value="${oro.receivedFrom}">${oro.receivedFrom}</option>
									</c:forEach>
									</select>
									<select id="cashCheckList" name="cashCheckList" multiple="multiple" style="display: none;">
									<c:forEach items="${cashCheckList}" var="ccr">
										<option value="${ccr.particulars}">${ccr.particulars}</option>
									</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</div>
					</p>
				</div>
				<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
			</div>
		</s:form>		
	</div>
</div>

</body>
</html>