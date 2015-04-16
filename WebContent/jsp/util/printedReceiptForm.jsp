<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="javascript:window.print();">
<table height="390px">
	<tr>
	<tr>
</table>
	<s:if test="%{subModule == 'orSales'}">
		<div >
			<p>
			<table>
				<tr>
					<td width="200px" style="padding-top: 45px;">
						
						<table  width="200px">
						<tr>
							<td width="70px"><s:property value="%{orSales.salesInvoiceNumber}"/></td>
							<td><s:property value="%{orSales.amount}"/></td>
						</tr>
						</table>
						
						<p  style="padding-bottom: 80px;padding-top: 11px;">
						<table class="form" style="text-align: right;" width="200px">
							
							
							<tr>
								<td><p style="padding-left: 50px;"></p></td>
								<td width="90px"><s:property value="%{orSales.cash}"/></td>
							</tr>
							<tr>
								<td><p style="padding-left: 50px;"></p></td>
								<td colspan="2"><s:property value="%{orSales.check}"/></td>
								
							</tr>
							<tr>
								<td colspan="2" style="padding-top: 5px;"><s:property value="%{orSales.bankCheckNo}"/></td>
							</tr>
							
							<tr>
								<td colspan="2" style="padding-top: 5px;"><s:property value="%{orSales.total}"/></td>
							</tr>
							
						</table>
						</p>
				</td>
				
				
				
				<td width="470px"  style="padding-top: 61px;padding-bottom: 20px;">	
					
					<table width ="470px" class="form" style="text-align: right;">
					
					<tr>
						
						<td colspan="2" style="text-align:center;padding-bottom: 5px;"><s:property value="%{orSales.orNumber}"/></td>
					</tr>
					<tr>
						<td width="280px" style="text-align:right;padding-top: 5px;"></td>
						<td colspan="2" style="text-align:left;"><s:property value="%{orSales.orDate}"/></td>
					</tr>
					<tr>	
						<td colspan="2" style="text-align:center;padding-top: 15px;"><s:property value="%{orSales.receivedFrom}"/></td>
					</tr>
					
					<tr>
						<td colspan="2" style="text-align:center;"><s:property value="%{orSales.address}"/></td>
					
						
					</tr>
					<tr>
						
						<td width="280px" style="text-align:right;padding-bottom: 20px;"><s:property value="%{orSales.busStyle}"/></td>
						<td ><s:property value="%{orSales.tin}"/></td>
						
					</tr>
					<tr >
						<td rowspan="2" width="280px" style="text-align:right;padding-bottom: 20px;" height=""><s:property value="%{orSales.amountInWords}"/></td>
						<td ></td>
					</tr>
					<tr>	
						<td colspan="2" style="text-align:right;padding-top: 5px;" height="10px"><s:property value="%{orSales.theAmountOf}"/></td>
					</tr>
					<tr>
						<td style="padding-top: 5px;"><s:property value="%{orSales.inFullPartialPaymentOf}"/></td>
						<td style="padding-top: 5px;"><s:property value="%{orSales.amount}"/></td>
					</tr>
					</table>
				
			</td>
			</tr>
			</table>
		</div>
		</s:if>
		<s:if test="%{subModule == 'orOthers'}">
		<div style="color:blue;">
			<p>
			<table>
				<tr>
					<td width="200px" style="padding-top: 42px;">
						
						<table width="200px">
						<tr>
							<td width="70px"><s:property value="%{orOthers.salesInvoiceNumber}"/></td>
							<td><s:property value="%{orOthers.amount}"/></td>
						</tr>
						</table>
						
						<p  style="padding-bottom: 80px;padding-top: 11px;">
						<table class="form" style="text-align: right;" width="200px">
							
							
							<tr>
								<td><p style="padding-left: 50px;"></p></td>
								<td width="90px"><s:property value="%{orOthers.cash}"/></td>
							</tr>
							<tr>
								<td><p style="padding-left: 50px;"></p></td>
								<td colspan="2"><s:property value="%{orOthers.check}"/></td>
								
							</tr>
							<tr>
								<td colspan="2" style="padding-top: 5px;"><s:property value="%{orOthers.bankCheckNo}"/></td>
							</tr>
							
							<tr>
								<td colspan="2" style="padding-top: 5px;"><s:property value="%{orOthers.total}"/></td>
							</tr>
							
						</table>
						</p>
				</td>
				
				
				
				<td width="470px"  style="padding-top: 61px;padding-bottom: 20px;">	
					
					<table width ="470px" class="form" style="text-align: right;">
					
					<tr>
						
						<td colspan="2" style="text-align:center;padding-bottom: 5px;"><s:property value="%{orOthers.orNumber}"/></td>
					</tr>
					<tr>
						<td width="280px" style="text-align:right;padding-top: 5px;"></td>
						<td colspan="2" style="text-align:left;"><s:property value="%{orOthers.orDate}"/></td>
					</tr>
					<tr>	
						<td colspan="2" style="text-align:center;padding-top: 15px;"><s:property value="%{orOthers.receivedFrom}"/></td>
					</tr>
					
					<tr>
						<td colspan="2" style="text-align:center;padding-bottom: 10px;"><s:property value="%{orOthers.address}"/></td>
					
						
					</tr>
					<tr>
						
						<td width="280px" style="text-align:right;padding-bottom: 20px;"><s:property value="%{orOthers.busStyle}"/></td>
						<td ><s:property value="%{orOthers.tin}"/></td>
						
					</tr>
					<tr >
						<td rowspan="2" width="280px" style="text-align:right;padding-bottom: 20px;"><s:property value="%{orOthers.amountInWords}"/></td>
						<td ></td>
					</tr>
					<tr>	
						<td colspan="2" style="text-align:right;padding-top: 5px;" height="10px"><s:property value="%{orOthers.theAmountOf}"/></td>
					</tr>
					<tr>
						<td style="padding-top: 5px;"><s:property value="%{orOthers.inFullPartialPaymentOf}"/></td>
						<td style="padding-top: 5px;"><s:property value="%{orOthers.amount}"/></td>
					</tr>
					</table>
				
			</td>
			</tr>
		</table>
		</div>
		</s:if>

		
		

</body>
</html>