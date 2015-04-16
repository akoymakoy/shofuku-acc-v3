//for Delete Confirmation
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
function clearAll(div1,formKo) {
	toggleClear(document.getElementById(div1));
	document.forms[formKo].submit();
	
	}

function supplierConfirmation(formKo) {
		
		var answer = confirm("Are you sure you want to delete this record?");
		if (answer){
			 
			 document.forms[formKo].action = 'deleteSupplierAction.action';
			 document.forms[formKo].submit();
			 clearAll(document.getElementById("wholeForm"),formKo);
			
		}
		else{
			alert("RECORD HAS BEEN RETAINED");
			 document.forms[formKo].action = 'showSearchSupplierForm.action';
			 document.forms[formKo].submit();
		}
	}

function customerConfirmation(formKo) {
	
	var answer = confirm("Are you sure you want to delete this record?");
	if (answer){
		 document.forms[formKo].action = 'deleteCustomerAction.action';
		 document.forms[formKo].submit();
		 clearAll(document.getElementById("wholeForm"),formKo);
			
	}
	else{
		alert("RECORD HAS BEEN RETAINED");
		 document.forms[formKo].action = 'showSearchCustomerForm.action';
		 document.forms[formKo].submit();
	}
}
function disbursementConfirmation(formKo) {
	
	var answer = confirm("Are you sure you want to delete this record?");
	if (answer){
		 document.forms[formKo].action = 'deleteDisbursementAction.action';
		 document.forms[formKo].submit();
		 clearAll(document.getElementById("wholeForm"),formKo);
			alert("delete");
	}
	else{
		alert("RECORD HAS BEEN RETAINED");
		
	document.forms[formKo].action = 'showSearchDisbursementForm.action';
	document.forms[formKo].submit();
	}
}
function receiptConfirmation(formKo) {
	
	var answer = confirm("Are you sure you want to delete this record?");
	if (answer){
		 document.forms[formKo].action = 'deleteReceiptAction.action';
		 document.forms[formKo].submit();
		 clearAll(document.getElementById("wholeForm"),formKo);
			
	}
	else{
		alert("RECORD HAS BEEN RETAINED");
		 document.forms[formKo].action = 'showSearchReceiptForm.action';
		 document.forms[formKo].submit();
	}
}
function inventoryConfirmation(formKo) {
		
		var answer = confirm("Are you sure you want to delete this record?");
		if (answer){
			
			document.forms[formKo].action = 'deleteInventoryAction.action';
			document.forms[formKo].submit();
			
		}
		else{
			alert("RECORD HAS BEEN RETAINED");
			 document.forms[formKo].action = 'showSearchInventoryForm.action';
			 document.forms[formKo].submit();
		}
		
}

function financialsConfirmation(formKo) {
	
	var answer = confirm("Are you sure you want to delete this record?");
	if (answer){
		
		document.forms[formKo].action = 'deleteFinancialsAction.action';
		document.forms[formKo].submit();
		
	}
	else{
		alert("RECORD HAS BEEN RETAINED");
		 document.forms[formKo].action = 'showSearchFinancialsForm.action';
		 document.forms[formKo].submit();
	}
	
}


