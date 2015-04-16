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
<script type="text/javascript" src="js/hideParameter.js"></script>
<script type="text/javascript" src="js/deleteConfirmation.js"></script>
<script type="text/javascript" src="js/expandingSection.js"></script> 
 <link rel="stylesheet" href="menu.css" type="text/css"/>
	<script type="text/javascript" src="js/expandingMenu.js"></script> 	
<sx:head parseContent="true" />	
	 <script type='text/javascript'>
	 
	var startWith=5;
	var subMenu=1;
	</script>
<title>Journal Entry Profile Form</title>
</head>
<body>
<s:if test="%{forWhat != 'print'}">
<div class="mainForm">
	<h4 class="title">Journal Entry Profile</h4>
	<s:form action="loadParentCodeAction" validate="true" id="journalEntryProfileForm">
		<div class="form" id="wholeForm">
			<div class="errors">
				<s:actionerror/>
				<s:actionmessage/>
			
			</div>
			<div id="profileForm">
				<h3 class="form">General Journal Profile</h3>
				<p>
					<table class="form">
						<tr>
							<th colspan="6">Entry Details</th>
						</tr>
						<tr>
							<td> <s:textfield name="jep.entryNo" label="Journal Entry No" id="accountId"></s:textfield><td>
							<s:hidden name="accId" value="%{jep.entryNo}"/>
							<td class="others">Entry Date:</td>
							<td><sx:datetimepicker name="jep.entryDate" displayFormat="MMM-dd-yyyy" displayWeeks="5"></sx:datetimepicker></td>
						</tr>
						
					</table>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Journal Entry Name:" name="jep.entryName" size = "60" ></s:textfield></td> 			
						</tr>
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Module:" name="jep.module" size = "60" ></s:textfield></td> 			
						</tr>
						
					</table>
					
					
					<br/>
					<table class="form">
						<tr>
							<td><s:textfield disabled="%{forWhat}" label="Amount PHP :" name="jep.amount"></s:textfield></td> 			
							<td class="others">Posting Date:</td>
							<td><sx:datetimepicker name="jep.postingDate" displayFormat="MMM-dd-yyyy" displayWeeks="5"></sx:datetimepicker></td>
						<td><s:select disabled="%{forWhat}" label="Accept? (Y/N):" name="jep.isAccepted" 
							value="%{jep.isActive}" list="#{'NO':'NO','YES':'YES'}"></s:select></td> 			
						</tr>
						
					</table>
					<table class="form">
						<tr >
							<td><s:textarea disabled="%{forWhat}" label="Comment:" name="jep.comment" cols="50" rows="5"></s:textarea></td> 			
						</tr>
					</table>
					<s:hidden name="parentCode" value ="%{jep.aep.accountCode}"/> 	
					<s:hidden name="" value ="%{jep.aep.accountCode}"/>  
					<table class="form">
						<tr>
							<th colspan="6">ACCOUNT DETAILS</th>
						</tr>
					</table>
					<table class="list" width="500px">
						<tr>
							<th>DEBIT  ---></th>
							<td>
								<select name="jep.aepCredit.accountCode">
									<c:forEach items="${accountCodeList}" var="profile">
										<option ${profile.accountCode == jep.aepCredit.accountCode ? 'selected' : ''}
												value="${profile.accountCode}">${profile.accountCode} - ${profile.name}
										</option>
									</c:forEach>
								</select>
							</td> 
						</tr>
						<tr>
							
							<th>CREDIT --> </th>
							<td>
								<select name="jep.aepDebit.accountCode" >
									<c:forEach items="${accountCodeList}" var="profile">
										<option ${profile.accountCode == jep.aepDebit.accountCode ? 'selected' : ''}
												value="${profile.accountCode}">${profile.accountCode} - ${profile.name}
										</option>
									</c:forEach>
								</select>
							</td> 
						</tr>		
					</table>
					
				</p>
					
			</div>
		</div>

		<div class="forButtons">
			<p>
				<table class="forButtons" align="center">	
				<tr>
				<s:hidden name="subModule" value="journalEntryProfile"/>
				<s:if test="%{forWhatDisplay == 'edit'}">
				
					<td><input class="myButtons" name="edit" type="button" onclick="javascript:toggleAlert('journalEntryProfileForm','accId');" value="Edit"></input></td>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="Update" id="bUpdate" action="updateFinancialsAction"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Delete" onclick="javascript:financialsConfirmation('accountEntryProfileForm');"></s:submit></td>
					<td><s:submit cssClass="myButtons" type="button" value="Print" action="printFinancialsAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','journalEntryProfileForm');" value="New Entry"></input></td>
				
				</s:if>
				<s:else>
					<td><s:submit disabled="%{forWhat}" cssClass="myButtons" type="button" value="New Entry" name="newJournalEntryProfile" action="addFinancialsAction"></s:submit></td>
					<td><input class="myButtons" name="clear" type="button" onclick="javascript:clearAll('wholeForm','journalEntryProfileForm');" value="Cancel"></input></td>
				   
				</s:else>
					</tr>
				</table>
			</p>
		</div>
	</s:form>
</div>
</s:if>

<!-- div for print -->
<s:else>
<div class="print">
<jsp:include page="/jsp/util/companyHeader.jsp"/>
	<h3 class="form">Journal Entry Profile</h3>
		<p>
				<table class="form">
					<tr>
						<th colspan="6">Entry Details</th>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Entry No:" value ="%{jep.entryNo}"></s:textfield> <td>
						<td><s:textfield disabled="%{forWhat}" label="Entry Date:" value ="%{jep.entryDate}"></s:textfield> <td>
					</tr>
				</table>
				<table class="form">
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Entry Name:" value ="%{jep.entryName}" size="60"></s:textfield> <td>
					</tr>
					
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Account Name:" value ="%{jep.aep.name}" size="60"></s:textfield> <td>
					</tr>
				</table>
				<table class="form">
					
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Amount PHP:" value ="%{jep.amount}"></s:textfield> <td>
						<td><s:textfield disabled="%{forWhat}" label="Posting Date:" value ="%{jep.postingDate}"></s:textfield> <td>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Accept?:" value ="%{jep.isAccepted}"></s:textfield> <td>
					</tr>
				</table>
				<table class="form">
					<tr>
						<td><s:textarea disabled="%{forWhat}" label="Comment:" value ="%{jep.comment}" cols="50" rows="5"></s:textarea> <td>
					</tr>
				</table>
					<table class="form">
					<tr>
						<th colspan="6">Account Details</th>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Account Debit:" value="%{jep.aepDebit.accountCode}" size="60"></s:textfield> <td>
					</tr>
					<tr>
						<td><s:textfield disabled="%{forWhat}" label="Account Credit:" value="%{jep.aepCredit.accountCode}" size="60"></s:textfield> <td>
					</tr>
				</table>
			</p>	
	</div>
</s:else>
</body>
</html>