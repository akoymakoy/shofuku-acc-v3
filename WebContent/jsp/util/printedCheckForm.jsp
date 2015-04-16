<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
  <script type="text/javascript" src="js/expandingMenu.js"></script> 
  <link rel="stylesheet" href="menu.css" type="text/css"/>	
<title>Insert title here</title>
</head>
<body>
<s:form action="printDisbursementAction" validate="true" method="printCheck">
<s:hidden name="subModule" value="%{'forPrint'}"/>
<s:if test="%{forWhat == 'printCheck'}">

<div>
<table height="530px">
</table>
		<table width ="700px">
		<tr>
			<td width="510px" style="text-align:center;"></td>
			<td style="text-align:center;padding-bottom:5px;padding-top:21px;"><s:property value="%{chequeDate}"/></td>
		</tr>
		<tr>
			<td style="text-align:center;padding-top:5px;" width="510px"><s:property value="%{payTo}"/></td>
			<td style="text-align:center;"><s:property value="%{amount}"/></td>
		</tr>

			<tr style="text-align:center;padding-top:10px;">
			<td colspan="2"><s:property value="%{amountInWords}"/></td>
			</tr>
		</table>	
			
</div>
</s:if>
<s:else>
<div class="form">
<h3 class="form">CHEQUE</h3>
		<table class="form">
		<tr>
			<td><s:textfield label="Date:" name="chequeDate"/></td>
			<td><s:textfield label="Pay To:" name="payTo"/></td>
			<td><s:textfield label="Amount:" name="amount"/></td>
		</tr>
		</table>
		<table class="form">
			<tr>
			<td><s:textfield label="Amount in Words:" name="amountInWords" size="90"/></td>
			</tr>
		</table>
		
		<table>
		<tr>
			<td><s:submit cssClass="myButtons" value="PRINT"/></td>
		</tr>
		</table>
</div>

</s:else>

</s:form>
</body>
</html>