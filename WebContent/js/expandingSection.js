function collapseSection(imageNya,div) {
  var tbl = document.getElementById(div);
  var imageKo = document.getElementById(imageNya);
  
  if(tbl.style.display == "block"){
	  tbl.style.display = "none";
	  imageKo.style.visibility="visible";
	  imageKo.src = "images/expand2.jpg";
  }else{
	  imageKo.src = "images/down2.jpg";
	  tbl.style.display = "block";
  }
}



  
  