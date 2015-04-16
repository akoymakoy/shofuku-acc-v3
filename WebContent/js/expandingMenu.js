// Node Functions

if(!window.Node){
  var Node = {ELEMENT_NODE : 1, TEXT_NODE : 3};
}

function checkNode(node, filter){
  return (filter == null || node.nodeType == Node[filter] || node.nodeName.toUpperCase() == filter.toUpperCase());
}

function getChildren(node, filter){
  var result = new Array();
  var children = node.childNodes;
  for(var i = 0; i < children.length; i++){
    if(checkNode(children[i], filter)) result[result.length] = children[i];
  }
  return result;
}

function getChildrenByElement(node){
  return getChildren(node, "ELEMENT_NODE");
}

function getFirstChild(node, filter){
  var child;
  var children = node.childNodes;
  for(var i = 0; i < children.length; i++){
    child = children[i];
    if(checkNode(child, filter)) return child;
  }
  return null;
}

function getFirstChildByText(node){
  return getFirstChild(node, "TEXT_NODE");
}

function getNextSibling(node, filter){
  for(var sibling = node.nextSibling; sibling != null; sibling = sibling.nextSibling){
    if(checkNode(sibling, filter)) return sibling;
  }
  return null;
}
function getNextSiblingByElement(node){
        return getNextSibling(node, "ELEMENT_NODE");
}

// Menu Functions & Properties

var activeMenu = null;

function showMenu() {
	
  if(activeMenu){
    activeMenu.className = "";
    getNextSiblingByElement(activeMenu).style.display = "none";
  }
  if(this == activeMenu){
//alert("OFF");
    activeMenu = null;
  } else {
    this.className = "active";
    getNextSiblingByElement(this).style.display = "block";
    activeMenu = this;
  }  
  return false;
}

function initMenu(){
	var menus, menu, text, a, i; // Sets initially displayed section
	menus = getChildrenByElement(document.getElementById("menu"));
	for(i = 0; i < menus.length; i++)
	{
	menu = menus[i];
	text = getFirstChildByText(menu);
	a = document.createElement("a");

	menu.replaceChild(a, text);
	a.appendChild(text);
	a.href = "#";
	a.onclick = showMenu;
	a.onfocus = function(){this.blur()};
	a.id="mLink"+i
	if(i==startWith){
			var activeElement = (getNextSiblingByElement((activeMenu=document.getElementById(a.id))).id)+subMenu +"";
			getNextSiblingByElement((activeMenu=document.getElementById(a.id))).style.display = "block";
	}
	}

//	alert (activeElement);
	document.getElementById(activeElement).style.backgroundColor="#FFFFFF";
}

if(document.createElement) window.onload = initMenu;

function formatPhoneNumber(phoneNumber){
	var number = phoneNumber.value;
	if (number != ""){
		
		if (number.indexOf("-")<=-1){
		}else{
			var area = number.substring(0,number.indexOf("-"));
			
			var wholeNumber = number.substring(number.indexOf("-")+1,number.length);
			
			var firstNumber = wholeNumber.substring(0,3);
			var secondNumber = wholeNumber.substring(3,5);
			var thirdNumber = wholeNumber.substring(5,wholeNumber.length);
			
			var formattedNumber = "(" + area + ") " + firstNumber + "-" + secondNumber + "-" + thirdNumber;
			phoneNumber.value = formattedNumber;
		}
	}
}
function parsePhoneNumber(phoneNumber){
	var number = phoneNumber.value;
	number = number.split(' ').join('');
	number = number.replace(/-|\/|\(/g, "");
	number = number.replace(/\)/g, "-");
	phoneNumber.value = number;
}


function formatMobileNumber(mobileNumber){
	
	var number = mobileNumber.value;
	number = number.replace(/-|\/|\(/g, "");
	
	if (number != ""){
	var area = number.substring(0,4);
	var firstNumber = number.substring(4,7);
	var secondNumber = number.substring(7,9);
	var thirdNumber = number.substring(9,11);
	
	
	var formattedNumber = "(" + area + ") " + firstNumber + "-" + secondNumber + "-" + thirdNumber;
	mobileNumber.value = formattedNumber;
	}
}
function parseMobileNumber(mobileNumber){
	var number = mobileNumber.value;
	if (number != ""){
	number = number.split(' ').join('');
	number = number.replace(/-|\/|\(/g, "");
	number = number.replace(/\)/g, "");
	
	mobileNumber.value = number;
	}
}

function checkValidEmail(emailAddress) {
	email = emailAddress.value;
	atPos = email.indexOf("@");
	stopPos = email.lastIndexOf(".");
	message = "";

	if (email == "") {
	message = "Not a valid Email address" + "\n";
	alert(message);
	}

	if (atPos == -1 || stopPos == -1) {
	message = "Not a valid email address";
	alert(message);
	}

	if (stopPos < atPos) {
	message = "Not a valid email address";
	alert(message);
	}

	if (stopPos - atPos == 1) {
	message = "Not a valid email address";
	alert(message);
	}

}
function checkEmailAddress(){
	var emailMe = document.getElementById("emailAdd").value;
	if (emailMe == ""){
		alert("Please put EMAIL ADDRESS to use this function");
		history.go(1);
	}else{
		
	}
}
function checkWeb(){
	var web = document.getElementById("website").value;
	if (web == ""){
		alert("Please put valid URL to use this function");
		history.go(-1);
	}else{
		
	}
}
