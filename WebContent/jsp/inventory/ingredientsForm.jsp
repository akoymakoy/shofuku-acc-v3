<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<LINK Rel="stylesheet" Href="CSS/design.css" Type="text/css">
<script type="text/javascript" src="js/hideParameter.js"></script>
<script type="text/javascript" src="js/deleteConfirmation.js"></script>
<script type="text/javascript" src="js/addingRows.js"></script>
<title>Insert title here</title>
</head>
<body>
<div align="center">
		<img src="images/header2.jpg" width="60%" height="50px" />
		<div class="importPage">
			<s:form action="addOrderDetailAction" validate="true">
				<div class="order">
					
					<table class="order">
						<tr>
							<th>Item Code</th>
							<th>Description</th>
							<th>Quantity</th>
							<th>Unit of Measurement</th>
							<th>Unit Cost</th>
							<th>Amount</th>
						</tr>
						<tr>
							<td><s:textfield size="10" name="orderDetails.itemCode"
									theme="simple"></s:textfield></td>
							<td><s:textfield name="orderDetails.description"
									theme="simple"></s:textfield></td>
							<td><s:textfield size="5" name="orderDetails.quantity"
									theme="simple"></s:textfield></td>
							<td><s:select theme="simple" headerKey=""
									headerValue="--Choose One--"
									list="#{'BAG':'BAG','BAR':'BAR','BOT':'BOT','BOX':'BOX','CAN':'CAN','CUP':'CUP','GAL':'GAL','KGS':'KGS','PAD':'PAD','PCK':'PCK','PCS':'PCS','ROL':'ROL','SET':'SET','TIN':'TIN'}"
									name="orderDetails.unitOfMeasurement" /></td>
							<td><s:textfield size="10" name="orderDetails.unitCost"
									theme="simple"></s:textfield></td>
							<td><s:textfield size="10" name="orderDetails.amount"
									theme="simple"></s:textfield></td>
							<td><s:submit cssClass="myButtons" type="button"
									label="Add Item" action="addOrderDetailAction">
								</s:submit></td>
							<td><s:submit cssClass="myButtons" type="button"
									label="Delete Item" action="deleteOrderDetailAction">
								</s:submit></td>
						</tr>
					</table>
				</div>
			</s:form>
			<s:form action="submitOrderDetailsAction">
				<div class="results">
					<p>
					<td>
					<table border="1px" class="lists">
						<tr class="others">
							<td>Item Code</td>
							<td>Description</td>
							<td>Quantity</td>
							<td>Unit of Measurement</td>
							<td>Unit Cost</td>
							<td>Amount</td>
							
						</tr>
						<s:iterator value="poDetailsHelper.purchaseOrderDetailsList" >
							<tr>
								<td><s:property value="itemCode" /></td>
								<td><s:property value="description" /></td>
								<td><s:property value="quantity" /></td>
								<td><s:property value="unitOfMeasurement" /></td>
								<td><s:property value="unitCost" /></td>
								<td><s:property value="amount" /></td>
							</tr>
						</s:iterator>
					</table>
			</div>
				<div class="forButtons">
					<table class="forButtons" border="1px">
						<tr>
							<td><s:submit cssClass="myButtons" type="button"
									value="Finalize Order Details" action="submitOrderDetailsAction"></s:submit></td>
						</tr>
					</table>
				</div>
			</s:form>


		</div>
	</div>
</body>
</html>