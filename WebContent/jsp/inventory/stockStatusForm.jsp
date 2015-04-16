<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
  <script type="text/javascript" src="js/expandingSection.js"></script> 
  <script type="text/javascript" src="js/onChangeType.js"></script>
  <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
  <sx:head parseContent="true"></sx:head>
  	
	 <script type='text/javascript'>
	var startWith=2;
	var subMenu=10;
	</script>
<title>Inventory Summary Search Form</title>
</head>

<body >
<div class="mainForm">
	<h4 class="title">Generate Stock Status Report </h4>
	<div class ="form">
	
		<s:form action="generateStockStatusReportAction" validate="true" id="searchForm">
			<div>
				<s:hidden name="clicked" id="clicked" value="true"/>
				<s:set name="inventoryModule" value="inventoryModule"/>
					<p>
						<table class="form">
							<%--<tr>
							 <td><s:submit value="Generate XML(TEMP)" action="generateXMLAction">	</s:submit></td>
							
							<td><s:textfield name="month" label="Month:" value="%{month}"></s:textfield></td>
							<td><s:textfield name="year" label="Year:" value="%{year}"></s:textfield></td> 
							
							<td></td>
							</tr>
							--%>
							<tr>
								<td><b>Note: This may take time to generate , press click only ONCE to avoid errors</b></td>
							</tr>
						</table>
						<p></p>
						<table class="form" id="searchByDateTbl">
						<tr>
						
								<td class="others">Date From:</td>
								<td>
									<sx:datetimepicker displayFormat="MMM-dd-yyyy" displayWeeks="5" name="dateFrom"></sx:datetimepicker>
								</td>
								<td class="others">Date To:</td>
								<td>
									<sx:datetimepicker displayFormat="MMM-dd-yyyy" displayWeeks="5" name="dateTo"></sx:datetimepicker>
								</td>
								<td><s:submit value="Generate Stock Status Report"></s:submit></td>
							</tr>
							
						</table>
						
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