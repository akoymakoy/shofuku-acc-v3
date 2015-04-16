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

<div style="color:blue;">	
<table height="55px"></table>
<table width="450px">
				<tr>
					<td>
					<table width="450px">
						<tr>
							<td style="padding-left:120px;text-align:center;padding-bottom:10px;"><s:property value="%{invoice.customerInvoiceNo}"></s:property></td>
						
							<td style="text-align:center;"><s:property value="%{invoice.customerInvoiceDate}"></s:property></td>
						
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;"><s:property value="%{invoice.soldTo}" ></s:property></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align:center;" ><s:property value="%{invoice.address}"></s:property></td>
						</tr>
				
						<tr>
							<td style="text-align:center;"><s:property value="%{invoice.busStyle}" ></s:property></td>
							<td style="text-align:center;"><s:property value="%{invoice.tin}"></s:property></td>
						
						</tr>
						
					</table>
				</p>
		</td>
		</tr>
			
			
		<tr>
			<td>
	
				
					<table width="450px" height="370px" style="padding-top: 0px;color:blue;" >
						<tr valign="top">
							<td>	
								<table width="450px">
								<s:iterator value="poDetailList" status="poDetailList">
								<tr height="5px">
									<td width="45px" height="5px" style="text-align:center;"><s:property value="%{quantity}"/></td>
									<td width="20px" height="5px" style="text-align:center;"><s:property value="%{unitOfMeasurement}"/></td>
									<td width="270px" height="5px" style="text-align:center;"><s:property value="%{description}"/></td>
									<td width="20px" height="5px" style="text-align:center;"><s:property value="%{unitCost}"/></td>
									<td width="75px" height="5px" style="text-align:center;"><s:property value="%{amount}"/></td>
									
								</tr>
								</s:iterator>
								</table>
							</td>
						</tr>
					</table>
				
					<table width="450px" style="text-align:right;">
						<tr>
							<td><s:property value="%{invoice.totalSales}"></s:property></td>
						</tr>
						<tr>
						<td><s:property value="%{invoice.vat}"></s:property></td>
							
						</tr>
						<tr>
							<td><s:property value="%{invoice.totalAmount}"></s:property></td>
					
						</tr>
					</table>
				
				</td>
			</tr>
</table>
</div>
	
		
</body>
</html>