//functie LOAD main page, create text document
function loadPageFunction(){
	console.log("Pagina a fost incarcata cu succes");
	var t = document.createTextNode("Ati accesat pagina " + new Date());
	document.body.appendChild(t);
}	

function keyPress() {
  alert("Ai apasat o tasta in campul de logare!");
}

window.onload = function() {
	var height = Math.max(document.body.scrollHeight, document.body.offsetHeight, 
	document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight );
	//modificarea stilului unor elemente
	document.getElementById("abc").style.height = height + "px"
	pgfs = document.querySelectorAll("p");
	for(pg of pgfs){
		var stil = getComputedStyle(pg);
		pg.stil.color = "#801727";	
	}
	//nu merge 
	document.body.lastChild.onclick = function (){
		var t = document.body.lastChild;
		alert(t);
		t.remove();
	}
}