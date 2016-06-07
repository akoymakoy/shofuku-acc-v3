///for Edit

function toggleAlert(div1,fieldId) {
	try{toggleDisabled(document.getElementById(div1));}catch(Exception){}
	try{document.getElementById(fieldId).disabled=true;}catch(Exception){}
	try{document.getElementById("parentCodeReadOnly").disabled= true;}catch(Exception){}
	
	try{document.getElementById("bUpdate").disabled=false;}catch(Exception){}
try{document.getElementById("bManageOrderDetails").disabled=false;}catch(Exception){}
try{document.getElementById("bManageItemDetails").disabled=false;}catch(Exception){}
try{document.getElementById("bAddItem").disabled=false;}catch(Exception){}
try{document.getElementById("bDeleteItem").disabled=false;}catch(Exception){}
try{document.getElementById("bGetItem").disabled=false;}catch(Exception){}
try{document.getElementById("payTerm").disabled=true;}catch(Exception){}

}
function toggleDisabled(el) {
	
	try {
			el.disabled = false;
	}
	catch(E){
	}
	if (el.childNodes && el.childNodes.length > 0) {
			for (var x = 0; x < el.childNodes.length; x++) {
				toggleDisabled(el.childNodes[x]);
		}
	}
}
//for New entry

function clearAll(div1,formKo) {
	toggleClear(document.getElementById(div1));
	document.forms[formKo].submit();
	}

function loadIngredient(){
	alert("me");
}

function toggleClear(el) {
		
		try {
		el.value = '';
		
		}
		catch(E){
		}
		if (el.childNodes && el.childNodes.length > 0) {
				for (var x = 0; x < el.childNodes.length; x++) {
					toggleClear(el.childNodes[x]);
			}
		}
	}
	
// for Print
function removeDiv(div1){
	var tbl= document.getElementById(div1);
	tbl.style.height ="800px";
	
}



function showSearchByReferenceNo(){
	var pettyCashSearchTypeTbl=document.getElementById("pettyCashSearchTypeTbl");
	var searchParametersDiv=document.getElementById("searchParameters");
	var searchByReference=document.getElementById("searchByReference");
	var pettyList=document.getElementById("pettyList");
	var cashList=document.getElementById("cashList");
	var checkList=document.getElementById("checkList");
	
	if (document.getElementById("disbursementModule").value == 'PettyCash'){
		searchParametersDiv.style.display = 'block';
		pettyCashSearchTypeTbl.style.display = 'block';
		
		pettyList.style.display = 'block';
		cashList.style.display = 'none';
		checkList.style.display = 'none';
	}
	else if (document.getElementById("disbursementModule").value == 'none'){
		searchByReference.style.display = 'none';
		pettyCashSearchTypeTbl.style.display = 'none';
		searchParametersDiv.style.display = 'none';
	}
	else{
		pettyCashSearchTypeTbl.style.display = 'none';
		searchParametersDiv.style.display = 'block';
		searchByReference.style.display = 'none';
		
		if (document.getElementById("disbursementModule").value == 'CashPayment'){
			pettyList.style.display = 'none';
			cashList.style.display = 'block';
			checkList.style.display = 'none';
		}else{
			pettyList.style.display = 'none';
			cashList.style.display = 'none';
			checkList.style.display = 'block';
		}
	}
	
	
}

function showCustomerList(){
	var seachParameterDiv = document.getElementById("searchParameters");
	var isFormatChk = document.getElementById("isFormatReport");
	
	if (document.getElementById("customerModule").value == 'Customer'){
		seachParameterDiv.style.display  = 'none';
		isFormatChk.checked = false;
	}
	else{
		seachParameterDiv.style.display  ='block';
	}
}

function showSupplierList(){
	
	//var searchByDate = document.getElementById("searchByDate");
//	var supplierMultiList = document.getElementById("supplierMultiList");
	var seachParameterDiv = document.getElementById("searchParameters");
	var isFormatChk = document.getElementById("isFormatReport");
	
	if (document.getElementById("supplierModule").value == 'Supplier'){
		seachParameterDiv.style.display  = 'none';
		isFormatChk.checked = false;
	}
	else{
		seachParameterDiv.style.display  ='block';
	}
}
function showReceivedFromList(){
	
	var orSalesList=document.getElementById("orSalesList");
	var orOthersList=document.getElementById("orOthersList");
	var cashCheckList=document.getElementById("cashCheckList");
	
	if (document.getElementById("receiptsModule").value == 'ORSales'){
		orSalesList.style.display = 'block';
		orOthersList.style.display = 'none';
		cashCheckList.style.display = 'none';
	}else if (document.getElementById("receiptsModule").value == 'OROthers'){
		orSalesList.style.display = 'none';
		orOthersList.style.display = 'block';
		cashCheckList.style.display = 'none';
	}else{
		orSalesList.style.display = 'none';
		orOthersList.style.display = 'none';
		cashCheckList.style.display = 'block';
		
	}
}

function changePettyCashType(){
	var searchParametersDiv=document.getElementById("searchParameters");
	var searchByReference=document.getElementById("searchByReference");
	
	if (document.getElementById("pettyCashSearchType").value == 'ByRef'){
		searchParametersDiv.style.display = 'none';
		searchByReference.style.display = 'block';
	}else if (document.getElementById("pettyCashSearchType").value == 'none'){
		searchParametersDiv.style.display = 'none';
		searchByReference.style.display = 'none';
		pettyList.style.display = 'block';
		cashList.style.display = 'none';
		checkList.style.display = 'none';
	}else{
		searchParametersDiv.style.display = 'block';
		searchByReference.style.display = 'none';
		pettyList.style.display = 'block';
		cashList.style.display = 'none';
		checkList.style.display = 'none';
	}
}
function showFilterTable(){
	var filterTbl=document.getElementById("filterTbl");
	
	if (document.getElementById("isFormatReport").checked){
		filterTbl.style.display = 'block';
	}else{
		filterTbl.style.display = 'none';
	}
}


function isCheckOnly(){
	var amount1= parseFloat(document.getElementById("credit1Amount").value);
	var amount2= parseFloat(document.getElementById("credit2Amount").value);
	var checkOnly= document.getElementById("checkOnly");
	var cashOnly=document.getElementById("cashOnly");
	
	if(amount1==0&&amount2==0){	
	}else if(amount1==0&&amount2>0){
		checkOnly.checked=false;
		cashOnly.checked=true;
		amount1= document.getElementById("credit1Amount").disabled=true;
	}else if(amount2==0&&amount1>0){
		cashOnly.check=false;
		checkOnly.checked=true;
		amount2= document.getElementById("credit2Amount").disabled=true;
	}else{
	}
}
function cashCheckSwap() {
	var fieldToEnable;
	var fieldToDisable
	var checkboxToEnable;
	var checkboxToDisable;
	var currentTotalAmount = document.getElementById("totalAmountOfPurchases").value;
	  for (var i = 0; i < arguments.length; i++){
		  if(i==0){
			  fieldToEnable = document.getElementById(arguments[i]);
		  }
		  if(i==1){
			  fieldToDisable = document.getElementById(arguments[i]);
		  }
		  if(i==2){
			  checkboxToEnable = document.getElementById(arguments[i]);
		  }
		  if(i==3){
			  checkboxToDisable = document.getElementById(arguments[i]);
		  }
	  }
	var isChecked = checkboxToEnable.checked;
	var currentValue = fieldToEnable.valueOf();
	
	if(!isChecked){
		checkboxToEnable.checked = false;
		fieldToDisable.disabled=false;
	}else{
		fieldToEnable.disabled = false;
		fieldToEnable.value=currentTotalAmount;
		fieldToDisable.disabled=true;
		fieldToDisable.value="0.00";
		checkboxToEnable.checked = true;
		checkboxToDisable.checked = false;
	}
	    
}

function toggleDisabledWithEnabled(element) {
	var el = document.getElementById(element);
	try {
			el.disabled = !el.disabled;
	}
	catch(E){
	}
}

function othersSelected(obj){
	if(obj==null){
		obj = document.getElementById("uom");
	}
		if(obj.value=='OTHERS'){
			document.getElementById("UOMTextField").disabled=false;
			document.getElementById("UOMTextField").value="";
			document.getElementById("UOMTextField").style.visibility="visible";
		}else{
			document.getElementById("UOMTextField").style.visibility="hidden";
			document.getElementById("UOMTextField").disabled=true;
		}
}

function getIngredientDetails(itemCode){
	document.forms["finForm"].action = 'loadIngredientAction.action';
	document.forms["finForm"].submit();
}

function cancelEnter(e){
	 e = e || event;
	
return (e.keyCode || event.which || event.charCode || 0) !== 13;
}
document.onkeydown = cancelEnter;  


