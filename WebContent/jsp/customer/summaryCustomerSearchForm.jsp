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
    <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
	<script type="text/javascript" src="js/hideParameter.js"></script>
  <sx:head parseContent="true"></sx:head>
	 <script type='text/javascript'>
	var startWith=1;
	var subMenu=5;
	</script>
<title>Customer Summary Search Form</title>
</head>

<body>
<div class="mainForm">
	<h4 class="title">CUSTOMER SUMMARY</h4>
	<div class ="form">
		<s:form action="generateSummaryAction" validate="true" id="searchForm">
			<div>
				<s:hidden name="clicked" id="clicked" value="true"/>
				<s:set name="customerModule" value="customerModule"/>
					<p>
						<table class="form">
							<tr>
								<td>
									<s:select label="Customer Module:" headerKey="none"
									list="#{'Customer':'Profile', 'CustomerPurchaseOrder':'Purchase Order', 'DeliveryReceipt':'Delivery Receipt', 'CustomerSalesInvoice':'Sales Invoice', 'ItemSoldToCustomers':'Items Sold To Customers', 'StatementOfAccount':'Statement of Account'}" 
								name="customerModule" id="customerModule" onchange="javascript:showCustomerList();" />
								</td>
								<td colspan="2" style="text-align:left;"><s:submit label="Generate Report" value="Generate and Export Report" method="generate"></s:submit></td>
							</tr>
						</table>
					</p>
					<div id="searchParameters" style="display: none;">
						
						<table class="form" cellpadding=5>
							<tr>
								<td colspan=4><h4 class="title">SELECT DATE RANGE & CUSTOMER/s TO BE INCLUDED IN THE REPORT:</h4></td>
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
								<td>Choose at least one supplier:</td>
								<td><select id="customerList" name="customerList" multiple="multiple">
									<c:forEach items="${customerList}" var="customer">
										<option value="${customer.customerNo}">${customer.customerName}</option>
									</c:forEach>
									</select>
								</td>
							</tr>
						</table>
					</div>
				</div>
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
			</div>
		</s:form>		
	</div>
</div>
	<script type='text/javascript'>
	showCustomerList();
</script>
</body>
</html>