<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
<script type="text/javascript" src="js/expandingSection.js"></script> 
<script type="text/javascript" src="js/hideParameter.js"></script> 
<script type="text/javascript" src="js/deleteConfirmation.js"></script> 
  <link rel="stylesheet" href="menu.css" type="text/css"/>
   <script type='text/javascript'>
var startWith=1;
var subMenu=0;
</script>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
<sx:head parseContent="true"/>

<title>Manage Stock Level Page</title>
</head>
<body>
	<div class="mainForm">
	<h4 class="title">Manage Stock Level</h4>
		<s:form action="manageStockLevelPageAction" validate="true" id="stockLevelPage" method="post" enctype="multipart/form-data">
				<s:hidden name="cusId" value="%{customer.customerNo}"/>	
				
				<s:actionerror/>
				<s:actionmessage/>	
			<table border=0 class="form">
				<tr valign="top">
					<td class="others">NOTE: Import *.xls file NOT .xlsx</td>
					<td colspan = "4">
						<s:file size="70" theme="simple"  cssClass="myButtons" name="fileUpload" />
					</td>
					<td colspan="2" style="text-align: left;">
						<s:submit theme="simple" type="button" 
											value="Import Stock Level" name="manageStockLevelPage"
											action="manageStockLevelPageAction"/>
					</td>
				</tr>
			</table>
			
		
			<div class="results">
			<table class="results">
				<tr class="others" align="left">
					<td class="desc" width="20px">Item Code</td>
					<td class="desc" width="5px">Stock Level</td>
				</tr>
				<s:iterator value="stockLevelList" >
				<tr align="left">
					<td><s:property value="itemCode" /></td>
					<td><s:property value="stockLevel" /></td>
				</tr>
				</s:iterator>
			</table>
			
		</div>
		<table class="form">
				<tr align="left">
					<td ><s:submit label="Submit" cssClass="myButtons" value="Return to Customer Profile" 
					action="showCustomerProfileAction"></s:submit></td>
				</tr>
			</table>
		</s:form>
	</div>
</body>
</html>