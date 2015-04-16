function addRow(tableID) {
	var table = document.getElementById(tableID);
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);
	var colCount = table.rows[0].cells.length;
	for ( var i = 0; i < colCount; i++) {
		var newcell = row.insertCell(i);
		newcell.innerHTML = table.rows[1].cells[i].innerHTML;
		//alert(newcell.childNodes);
		var index = rowCount-1;
		switch (newcell.childNodes[0].type) {
		case "text":
			newcell.childNodes[0].value = "";
			newcell.childNodes[0].name="transactionList["+index+"].amount";
			break;
		case "checkbox":
			newcell.childNodes[0].checked = false;
			break;
		default:
			newcell.childNodes[1].name="transactionList["+index+"].accountEntry.accountCode";
			break;
		}
	}
}

function deleteRow(tableID) {
		var table = document.getElementById(tableID);
		var rowCount = table.rows.length;
		try {
		for ( var i = 0; i < rowCount; i++) {
			var row = table.rows[i];
			var chkbox = row.cells[0].childNodes[0];
			if (null != chkbox && true == chkbox.checked) {
				if (rowCount <= 1) {
					alert("Cannot delete all the rows.");
					break;
				}
				table.deleteRow(i);
				rowCount--;
				i--;
			}

		}
	} catch (e) {
		alert(e);
	}
}


function showTransactionAction(profileName,accountCode){
	var lookupString = "accountCodeActions."+accountCode.value;
	var action = document.getElementById(lookupString).value;
	var fieldToChange = document.getElementById("transactionList["+profileName+"].transactionAction");
	fieldToChange.value = action;
}