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
var subMenu=1;
</script>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
<sx:head parseContent="true"/>
<title>Customer Purchase Order Form</title>
</head>
<body>
<s:if test="%{forWhat != 'print'}">
<div class="mainForm">
	<h4 class="title">CUSTOMER</h4>
	<s:form action="newCustomerEntryAction" validate="true" id="poForm" enctype="multipart/form-data" >
		<div class="form" id="wholeForm">
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
				
			</div>
		<h3 class="form" onclick="javascript:collapseSection('arrow1','div1')"><img id="arrow1" src="images/expand2.jpg"/>PROFILE</h3>
		<div id="div1" class="sectionDiv">
			<p>
				<table class="form">
					<tr>
						<th colspan="6">Profile Details</th>
					</tr>
					<tr>
						<td width="120px"><s:textfield disabled="true" label="Customer ID:" value="%{custpo.customer.customerNo}"></s:textfield></td>
						<td><s:textfield label="Customer Type:" 
								value="%{custpo.customer.customerType}"/></td>
					</tr>
				</table>
				<table class="form">	
					<tr>
						<td colspan="4"><s:textfield disabled="true" label="Customer Name:"  size="70" value="%{custpo.customer.customerName}"></s:textfield>
					</tr>
				</table>
				</p>
				<p>
				<table class="form">
					<tr>
						<th colspan="6">Contact Details</th>
					</tr>
					<tr>
						<td><s:textfield disabled="true" label="Contact Name:" value="%{custpo.customer.contactName}" size="50"></s:textfield>
						<td><s:textfield disabled="true" label="Contact Title:" value="%{custpo.customer.contactTitle}"></s:textfield>
					</tr>
					
					</table>
				<table class="form">
					<tr>
						<td><s:label label="Email Address:"/><a href="mailto:<s:property value="%{custpo.customer.emailAddress}"/>"><s:property value="%{custpo.customer.emailAddress}"/></a></td>
					</tr>
					<tr>
						<td><s:label label="Website:"/><a href="<s:url value="%{custpo.customer.website}"/>" target="_blank"><s:property value="%{custpo.customer.website}"/></a></td>
					</tr>
					<tr>
						<td><s:textfield disabled="true" label="Address:" value="%{custpo.customer.billingAddress}" size="90"></s:textfield></td>
					</tr>
				</table>
				<table class="form">
					<tr>
						<td><s:textfield disabled="true" label="Phone Number:" value="%{custpo.customer.phoneNumber}"></s:textfield>
						<td><s:textfield disabled="true" label="Mobile Number:" value="%{custpo.customer.mobileNumber}"></s:textfield>
						<td><s:textfield disabled="true" label="Fax Number:" value="%{custpo.customer.faxNumber}"></s:textfield>
					</tr>
				</table>
				
			</p>			
		</div>
		<div id="po2Form">
			<h3 class="form">PURCHASE ORDER</h3>
			
				<p>
					<table class="form">
						<tr>
							<th colspan="6">PO Details</th>
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" readOnly="readOnly" label="Purchase Order No:" name="custpo.customerPurchaseOrderId" id="cpoId"></s:textfield></td>
							<s:hidden name="custpoid" value="%{custpo.customerPurchaseOrderId}"/>
							<td class="others">Purchase Order Date:</td>
							<td><sx:datetimepicker name="custpo.purchaseOrderDate" displayFormat="MMM-dd-yyyy" displayWeeks="5"></sx:datetimepicker></td>
							
						</tr>
						<tr>
							<td class="others">Customer No:</td>
							<td><sx:autocompleter listValue="customerNo" list="customerNoList" maxlength="50" resultsLimit="-1" name="custpo.customer.customerNo"></sx:autocompleter></td>
						<td class="others">Delivery Date:</td>
							<td><sx:datetimepicker name="custpo.dateOfDelivery" displayFormat="MMM-dd-yyyy" displayWeeks="5"></sx:datetimepicker></td>
						</tr>
						<tr>	
							<td class="others">Payment Date:</td>
							<td><sx:datetimepicker name="custpo.paymentDate" displayFormat="MMM-dd-yyyy" displayWeeks="5"></sx:datetimepicker></td>
							<td><s:select disabled="%{forWhat}" label="Payment Term:" 
								list="#{'COD':'COD','7DAYS':'7DAYS','15DAYS':'15DAYS','30DAYS':'30DAYS','45DAYS':'45DAYS','60DAYS':'60DAYS','90DAYS':'90DAYS'}" 
								name="custpo.paymentTerm"/></td>
						</tr>
						<tr>	
						<td><s:textfield disabled="%{forWhat}" label="Total Amount: PHP" name="custpo.totalAmount" id="cpoId"></s:textfield></td>
						<!-- <td><s:textfield disabled="%{forWhat}" label="Ordered By:" name="custpo.orderedBy" ></s:textfield></td>	 -->
					
						</tr>
					</table>
				</p>
				<div>
				<s:if test="%{forWhat == 'print'}">
					<table class="form">
					<tr>
						<th colspan="4">Customer Details</th>
						</tr>
						<tr>
						<tr>
							<td ><s:property value="%{custpo.customer.customerName}"></s:property></td>
							<td ><s:property value="%{custpo.customer.faxNumber}"></s:property></td>
							
						</tr>
						<tr>
							<td colspan="4" style="text-align:left;"><s:property value="%{custpo.customer.billingAddress}"></s:property></td>
						</tr>
					</table>
					</s:if>
				<table class="form">
							<tr>
								<th colspan="7">Order Details</th>
							</tr>
							
							<tr>
							<s:if test="%{forWhat != 'print'}">
								<td class="others">NOTE: Import *.xls file NOT .xlsx</td>
								<td colspan = "4">
										<s:file size="70" theme="simple"  cssClass="myButtons" name="fileUpload" />
																	
								</td>
								<td colspan="2" style="text-align: left;">
								<s:submit theme="simple" type="button" id="bManageOrderDetails" disabled="%{forWhat}"
								value="Import Order Details" name="checkCustomerOrderingForm"
								action="checkCustomerOrderingForm"/>
								
								</td>
								</s:if>
								<s:hidden name="parentPage" value="CustomerPurchaseOrder"/>
								<s:hidden name="forWhat" value="%{forWhat}"/>
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsItemCode"     value="%{poDetailsHelper.hiddenDelimetedOrderDetailsItemCode}"></s:hidden>
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsDescription"	 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsDescription}"></s:hidden>
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsQuantity"     value="%{poDetailsHelper.hiddenDelimetedOrderDetailsQuantity}"></s:hidden> 
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsUOM" 		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsUOM}"></s:hidden>
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsCost"		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsCost}"></s:hidden> 
								<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsAmount"		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsAmount}"></s:hidden>
								<!--START: 2013 - PHASE 3 : PROJECT 4: AZ-->
									<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsIsVattable"		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsIsVattable}"></s:hidden>
									<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsVatAmount"		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsVatAmount}"></s:hidden>
									<s:hidden name="poDetailsHelper.hiddenDelimetedOrderDetailsVattableAmount"		 value="%{poDetailsHelper.hiddenDelimetedOrderDetailsVattableAmount}"></s:hidden>
									<!--END: 2013 - PHASE 3 : PROJECT 4: AZ-->
								
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsItemCode"     	value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsItemCode}"></s:hidden>
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsDescription"	value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsDescription}"></s:hidden>
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsQuantity"       value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsQuantity}"></s:hidden> 
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsUOM" 		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsUOM}"></s:hidden>
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsCost"		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsCost}"></s:hidden> 
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsAmount"		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsAmount}"></s:hidden>
								<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsIsVattable"		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsIsVattable}"></s:hidden>
									<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsVatAmount"		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsVatAmount}"></s:hidden>
									<s:hidden name="poDetailsHelperToCompare.hiddenDelimetedOrderDetailsVattableAmount"		    value="%{poDetailsHelperToCompare.hiddenDelimetedOrderDetailsVattableAmount}"></s:hidden>
									
								
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsItemCode"     	value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsItemCode}"></s:hidden>
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsDescription"	value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsDescription}"></s:hidden>
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsQuantity"       value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsQuantity}"></s:hidden> 
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsUOM" 		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsUOM}"></s:hidden>
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsCost"		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsCost}"></s:hidden> 
								<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsAmount"		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsAmount}"></s:hidden>
							<!--START: 2013 - PHASE 3 : PROJECT 4: AZ-->
									<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsIsVattable"		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsIsVattable}"></s:hidden>
									<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsVatAmount"		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsVatAmount}"></s:hidden>
									<s:hidden name="poDetailsGrouped.hiddenDelimetedOrderDetailsVattableAmount"		    value="%{poDetailsGrouped.hiddenDelimetedOrderDetailsVattableAmount}"></s:hidden>
									<!--END: 2013 - PHASE 3 : PROJECT 4: AZ-->
							</tr>
					</table>
					<p>
				<table class="results">
					<tr valign="top">
					<td>
						<table class="compare">
							<tr class="others">
								<td class="desc">Item Code</td>
								<td class="desc">QTY</td>
								<td class="header" width="300px">Description</td>
								
								<td>UOM</td>
								<td>Unit Cost</td>
								<td>Amount</td>
							</tr>
							<s:iterator value="poDetailsHelper.purchaseOrderDetailsList">
								<tr>
									<td class="desc"><s:property value="itemCode" /></td>
									<td class="desc"><s:property value="quantity" /></td>
									
									<td class="desc"><s:property value="description" /></td>
									<td><s:property value="unitOfMeasurement" /></td>
									<td><s:property value="unitCost" /></td>
									<td><s:property value="amount" /></td>
								</tr>
							</s:iterator>
						<tr>	
							<td colspan="5" class="total">Total: PHP</td>
							<td class="totalAmount"><s:property value="poDetailsHelper.totalAmount"/></td>
						</tr>
					</table>
			</td>
			<td>
			
					<table class="compare">
					<tr>
						<td class="others" colspan="6">ITEMS HERE ARE NOT AVAILABLE IN INVENTORY DATABASE</td>
					</tr>
					<tr>
						<td class="others" colspan="6" style="color:red;">..Please add first before proceeding..</td>
					</tr>
					<tr class="others">
								<td class="desc">Item Code</td>
								<td class="desc">Quantity</td>
								<td class="header" width="300px">Description</td> 
								
								<td>UOM</td> 
								<td>Unit Cost</td>
								<td>Amount</td>
					</tr>
					<s:iterator value="poDetailsHelperToCompare.purchaseOrderDetailsList">
						<tr>
							<td class="desc"><s:property value="itemCode" /></td>
							<td class="desc"><s:property value="quantity" /></td>
							<td class="desc"><s:property value="description" /></td>
							
							<td><s:property value="unitOfMeasurement" /></td>
							<td><s:property value="unitCost" /></td>
							<td><s:property value="amount" /> </td>
						</tr>
					</s:iterator>
					<tr>
							<td colspan="5" class="total">Total: PHP</td>
							<td class="totalAmount"><s:property value="%{poDetailsHelperToCompare.totalAmount}"/></td>
					</tr>
					</table>
			</td>
			</tr>
			</table>
			</p>
			</div>
		</div>
	</div>
		
		<div class="forButtons">
			<p>
				<table class="forButtons" align="center">
					<tr><s:hidden name="subModule" value="purchaseOrder"/>
						
					<s:if test="%{forWhat == 'true'}">
						<td><input class="myButtons" name="edit" type="button" onclick="javascript:toggleAlert('po2Form','cpoId');" value="Edit"></input>
						<td><s:submit disabled="%{forWhat}" id="bUpdate" cssClass="myButtons" type="button" value="Update" action="updateCustomerAction"></s:submit></td>
						<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:customerConfirmation('poForm');"></s:submit></td>
						<td><s:submit cssClass="myButtons" type="button" value="Print"  action="printCustomerAction"></s:submit></td>
						<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','poForm');" value="New Entry"></input></td>
					
					</s:if>
					
					<s:else>
						<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" name="newSupplierPurchaseOrder" action="addCustomerAction"></s:submit></td>
							<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','poForm');" value="Cancel"></input></td>
						
					</s:else>
					</tr>
				</table>
			</p>
			
		</div>
	</s:form>
</div>
</s:if>

<s:else>
<!-- div for print -->
<div class="print">
		<jsp:include page="/jsp/util/companyHeader.jsp"/>

		<h3 class="form">PURCHASE ORDER</h3>
			
				<p>
					<table class="form">
						<tr>
							<th colspan="6">PO Details</th>
						</tr>
						<tr>
							<td><s:textfield label="Purchase Order No:" value="%{custpo.customerPurchaseOrderId}"></s:textfield></td>
							<td><s:textfield label="Purchase Order Date" value="%{custpo.purchaseOrderDate}"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield  label="Customer No:" value="%{custpo.customer.customerNo}"></s:textfield></td>
							<td><s:textfield label="Delivery Date:" value="%{custpo.dateOfDelivery}"></s:textfield></td>
						</tr>
						<tr>
							<td><s:textfield label="Payment Date:" value="%{custpo.paymentDate}"></s:textfield></td>
							<td><s:textfield  label="Payment Term:" value="%{custpo.paymentTerm}"/></td>
						</tr>
						<tr>	
							<td><s:textfield  label="Total Amount: PHP" value="%{custpo.totalAmount}"></s:textfield></td>
						</tr>
					</table>
				</p>
				
				
				<table class="form">
					<tr>
						<th colspan="4">Customer Details</th>
					</tr>
						<tr>
							<td colspan="2" style="font-weight:bold;font-size:18px;"><s:property value="%{custpo.customer.customerName}"></s:property></td>
							<td width="150px"><s:property value="%{custpo.customer.faxNumber}"></s:property></td>
						</tr>
						<tr>
							<td colspan="4" style="text-align:left;font-size:15px;"><s:property value="%{custpo.customer.billingAddress}"></s:property></td>
						</tr>
					</table>
				<table class="form">
					<tr>
						<th colspan="6">Order Details</th>
					</tr>
				</table>	
				<table class="lists" border="1px">
						
						<tr class="others">
								<td class="desc">Item Code</td>
								<td class="desc">QTY</td>
								<td class="header" width="300px">Description</td>
								<td>UOM</td>
								<td>Unit Cost</td>
								<td>Amount</td>
							</tr>
						<s:iterator value="poDetailsHelper.purchaseOrderDetailsList">
							<tr>
								<td class="desc"><s:property value="itemCode" /></td>
								<td class="desc"><s:property value="quantity" /></td>
								<td class="desc"><s:property value="description" /></td>
								<td><s:property value="unitOfMeasurement" /></td>
								<td><s:property value="unitCost" /></td>
								<td><s:property value="amount" /></td>
							</tr>
						</s:iterator>
						<tr>
							<td colspan="5" class="total">Total: PHP</td>
							<td class="totalAmount"><s:property value="%{custpo.totalAmount}"/></td>
					</tr>		
				</table>
				<p></p>
				<table>
						<tr>
							<td  class="others">PREPARED BY:</td>
						</tr>
						<tr>
							<td style="padding-top:10px;">_____________________</td>
						</tr>
				</table>
</div>
</s:else>
</body>
</html>