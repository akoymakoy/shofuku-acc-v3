
function getReturnSlipItem(itemCode){
	document.forms["returnSlipForm"].action = 'loadReturnSlipItemAction.action';
	document.forms["returnSlipForm"].submit();
}
