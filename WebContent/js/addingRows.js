function generateRow(){

	var orderTable = document.getElementById("orderDetails");
	var lastRow= orderTable.rows.length;
	var iteration = lastRow;
	alert(iteration);
	var row= orderTable.insertRow(lastRow);
	
	//numbered row
	var cellLeft = row.insertCell(0);
	var textnode = document.createTextNode(iteration);
	cellLeft.appendChild(textNode);
}