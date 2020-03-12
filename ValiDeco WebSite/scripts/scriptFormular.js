var pasteProd = ["Cos cu flori din portelan la rece si elemente pascale", "Aranjament cu flori din portelan la rece si elemente pascale", "Cos cu flori din portelan la rece si elemente pascale", "Ou de Paste decorat cu portelan la rece","Ou de Paste decorat cu portelan la rece","Ou de Paste decorat cu portelan la rece","Ou de Paste decorat cu portelan la rece","Ou de Paste decorat cu portelan la rece", "Ou de Paste decorat cu portelan la rece","Cos cu flori din portelan la rece si elemente pascale", "Figurina iepuras de paste handmade"]
var sticleProd = ["Sticla de vin decorata cu atat si flori din portelan la rece</strong> semnificand un ciorchine de struguri", "Recipient de cafea decorat cu ata si boabe de cafea", "Sticla de bautura acoolica decorata cu atat, ghiveci decorat cu ata si recipient pentru cafea", "Recipient de depozitare decorat cu diferite tipuri de ata si flori din plastic", "Sticla de ulei decorata cu ata si material","Recipient de depozitare decorat cu ata si figurine din plastic", "Sticle pentru bautura alcoolica decorate cu ata", "Sticla pentru bautura alcoolica decorata cu ata, material si flori din plastic","Sticla decorat cu ata si vopsita","Sticla pentru bautura decorata cu ata, material si vopsita","Sticle pentru bautura alcoolica decorate cu ata", "Sticla decorata cu ata, material si vopsita","Pahare pentru miri si nasi decorate cu ata","Pahare pentru miri si nasi decorate cu ata", "Pahare pentru miri si nasi decorate cu ata", "Pahare pentru miri si nasi decorate cu ata"]
var martisorProd = ["Martisoare de prins in piept realizate din portelan la rece","Martisoare de prins in piept realizate din portelan la rece", "Martisor cu flori realizate din portelan la rece ","Martisor cu flori realizate din portelan la rece ","Martisor cu flori realizate din portelan la rece ","Martisor cu flori realizate din portelan la rece ","Martisor special cu flori realizate din portelan la rece pe material","Martisor special cu flori realizate din portelan la rece pe material","Martisor special cu flori realizate din portelan la rece pe material","Martisor cu flori realizate din portelan la rece","Cana tip martisor realizata din portelan la rece","Martisor cu flori realizate din portelan la rece"]
var floriProd = ["Vaza cu flori de vara", " Aranjament cu flori", "Vaza cu flori de vara", "Tablou cu diverse flori", "Vaza decorata cu flori","Aranjament cu diverse flori colorate","Aranjament cu flori","Coperta de carte decorata cu flori","Tablou cu maci si spice de grau","Tablou cu buchet de flori","Tablou cu un buchet de flori colorate","Tablou cu flori si tulpini pictate"," Cos cu flori", "Vaza cu maci ", "Vaza cu maci si spice de grau","Vaza cu maci si spice de grau","Vaza cu flori"]
var craciunProd = ["Coronita pentru usa decorativa de iarna", "Aranjament de iarna cu lumanare", "Aranjament de iarna cu lumanare ","Aranjament pentru masa de Craciun", "Sania lui Mos Craciun"," Aranjament pentru masa de Craciun","Aranjament pentru masa de Craciun","Aranjament pentru masa de Craciun","Aranjament pentru masa de Craciun","Coronita pentru usa decorativa de iarna","Coronita pentru usa decorativa de iarna","Brad decorativ din conuri","Armata din omuleti de zapada"]

//try catch, creare dinamica de titlu + stilizare 
function creareH1() {
	var body = document.getElementsByTagName("body")[0];
	try{
		var h = document.createElement("h1");
		if (h === undefined) throw "nu s-a putut crea";
	}
	catch(err) {
		document.getElementById("error").innerHTML = err.message;
	}
	var t = document.createTextNode("Formular trimitere comanda");
	h.appendChild(t);
	document.body.prepend(h);
	h.style.color = "#801727";
	h.style.textAlign = "center";
	h.style.background = "white";
}

function sendJSON() {
	let result = document.querySelector('.result');
	let name = document.getElementsByName("client")[0];
	let mail = document.getElementsByName("mail")[0];
	let parola = document.getElementsByName("parola")[0];
	
	let xhr = new XMLHttpRequest(); 
	let url = "clienti.json"; 

	xhr.open("POST", url, true); 
	xhr.setRequestHeader("Content-Type", "application/json"); 
	xhr.onreadystatechange = function () { 
		if (xhr.readyState === 4 && xhr.status === 200) { 
			result.innerHTML = this.responseText; 
		} 
	}; 
	var data = JSON.stringify({ "name": client.value, "email": email.value, "parola":parola.value });  
	xhr.send(data); 
}

//select in select, createElement
document.getElementById("categorie").onchange = function produse() {
	document.getElementById("mySelect").innerHTML = "";
	var select = document.getElementById("categorie");
	var prod =  select.options[select.selectedIndex].value;
	j = 1;
	if(prod == "paste") {
		var x = document.getElementById("mySelect");
		for(let i of pasteProd){
			var option = document.createElement("option");
			option.text = j +"." + i;
			x.add(option);
			j++;
		}
	}
	if(prod == "sticle") {
		var x = document.getElementById("mySelect");
		for(let i of sticleProd){
			var option = document.createElement("option");
			option.text = j +"." + i;
			x.add(option);
			j++;
		}
	}
	if(prod == "martisor") {
		var x = document.getElementById("mySelect");
		for(let i of martisorProd){
			var option = document.createElement("option");
			option.text = j +"." + i;
			x.add(option);
			j++;
		}
	}
	if(prod == "flori") {
		var x = document.getElementById("mySelect");
		for(let i of floriProd){
			var option = document.createElement("option");
			option.text = j +"." + i;
			j++;
			x.add(option);
		}
	}
	if(prod == "craciun") {
		var x = document.getElementById("mySelect");
		for(let i of craciunProd){
			var option = document.createElement("option");
			option.text = j +"." + i;
			j++;
			x.add(option);
		}
	}
}

//setTimeout
ok = 0;
document.getElementById("card").onchange =  function cardOnline() {
	setTimeout(function timp(){
		if(ok == 0){
			window.location='index.html';
		}
	}, 30000)
	clearTimeout();
	p = document.getElementById("dateCard");
	p2 = document.getElementById("cvvCard");
	p.innerHTML = "";
	p2.innerHTML="";
	if (document.getElementById("card").checked) {
		p.innerHTML = "Numar card:";
		document.getElementById('nrCard').style.display = "inline-block";
		p2.innerHTML = "CVV:";
		document.getElementById('cvvCard').style.display = "inline-block";
		document.getElementById('trimCard').style.display = "inline-block";
	}
}

document.getElementById("cash").onchange =  function()  {
	p = document.getElementById("dateCard");
	p2 = document.getElementById("cvvCard");
	document.getElementById('nrCard').style.display = "none";
	document.getElementById('cvvCard').style.display = "none";
	document.getElementById('trimCard').style.display = "none";
	p.innerHTML = "";
	p2.innerHTML = "";
}

document.getElementById("trimCard").onclick = function() {
	ok = 1;
	document.getElementById("salvatCard").innerHTML = "Salvat !";
	document.getElementById('trimCard').style.display = "none";
}


document.getElementById("cashRamb").onchange =  function (){
	p = document.getElementById("dateCard");
	p2 = document.getElementById("cvvCard");
	document.getElementById('nrCard').style.display = "none";
	document.getElementById('cvvCard').style.display = "none";
	document.getElementById('trimCard').style.display = "none";
	p.innerHTML = "";
	p2.innerHTML = "";
}


window.onload = function(){
	document.getElementById('nrCard').style.display = "none";
	document.getElementById('cvvCard').style.display = "none";
	document.getElementById('trimCard').style.display = "none";
	document.title = "Formular comenzi";
	var body = document.getElementsByTagName("body")[0];
	body.style.backgroundImage = "url('./images/background.jpeg')";
	creareH1();
    pgfs = document.querySelectorAll("p");
	for(pg of pgfs){
		pg.style.color = "black";	
		pg.style.fontWeight = "bold";
		pg.style.fontSize = "18px";
		pg.style.background = "white";
	}
	labels = document.querySelectorAll("label");
	for(lbl of labels){
		lbl.style.color = "#801727";	
		lbl.style.fontWeight = "bold";
		lbl.style.background = "white";
	}
}