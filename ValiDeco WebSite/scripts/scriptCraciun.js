//animatie, addEventListener, setInterval, inserare audio, try catch

el = document.getElementById("animatie")
el.addEventListener("click", move, false);

function move() { 
	var audio = new Audio("./sounds/snow.mp3" ) ;
	
	audio.currentTime = 0;
	audio.play();
	setInterval(function(){
		if(audio.currentTime > 40){
			audio.pause();
				}
	},1000);	

	var pos1 = 0;
	var pos2 = 0;
	
	var elem = document.getElementById("animate");
	var id = setInterval(animation, 5);
	function animation() { 
		if(pos1 == 350 && pos2 != 350) {
			pos2 += 50;
			pos1 = 0;
			elem.style.top = 0;
			elem.style.marginLeft = pos2 + "px";
		}
		else if(pos1 == 350 && pos2 == 350){
			try {
				clearInterval(id);
			}
			catch(err) {
				document.getElementById("error").innerHTML = err.message;
			}
		}
		else {
			pos1++;
			elem.style.top = pos1 + "px";
		}
	}
}

//sortare
document.getElementById("modalitate").onchange = function sortListDir() {
  var list, i, switching, b, shouldSwitch;
  list = document.getElementById("lista");
  imagini = document.getElementsByClassName("grid-item");
  var e = document.getElementById("modalitate");
  var sort = e.options[e.selectedIndex].value;
  switching = true;
  while (switching) {
    switching = false;
    b = list.getElementsByTagName("li");
    for (i = 0; i < (b.length - 1); i++) {
      shouldSwitch = false;
	  var x = parseInt(b[i].innerHTML.split(" ")[0]);
	  var y = parseInt(b[i + 1].innerHTML.split(" ")[0]);
      if (sort == "crescator") {
        if (x > y) {
          shouldSwitch = true;
          break;
        }
      } else if (sort == "descrescator") {
        if (x < y) {
          shouldSwitch= true;
          break;
        }
      }
    }
    if (shouldSwitch) {
      b[i].parentNode.insertBefore(b[i + 1], b[i]);
	  imagini[i].parentNode.insertBefore(imagini[i + 1], imagini[i]);
      switching = true;
    } 
    
  }
}

function rangeSort() {
	var list, j = 0, switching, b, imagini, shouldSwitch;
	var p = document.getElementById("valoare");
	var range = document.getElementById("range");
	p.innerHTML = range.value;
    list = document.getElementById("lista");
	imagini = document.getElementsByClassName("grid-item");
	b = list.getElementsByTagName("li");
	for(i = 0; i < b.length; i++){
			imagini[i].style.display = "inline-block";
			if(b[i].className == "inviz"){
				b[i].classList.remove();
				b[i].classList.add("viz");
			}
	}
	switching = true;
	while (switching) {
		switching = false;
		//for (i = 0; i < b.length; i++) {
		while(j < b.length){
		  shouldSwitch = false;
		  var x = parseInt(b[j].innerHTML.split(" ")[0]);
		  if(x > range.value) {
			  shouldSwitch = true;
			  break;
		  }
		  else j++;
		}
		if (shouldSwitch) {
		  switching = true;
		  //b[i].remove();
		  b[j].classList.add("inviz");
		  //imagini[i].remove();
		  imagini[j].style.display = "none";
		  j++;
		}
   }
}

//search bar
function search() {
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
	list = document.getElementById("lista");
	var figure = document.getElementsByClassName("grid-item");
	var c = list.getElementsByTagName("li");
    li = document.getElementsByTagName("figcaption");
	for(let j = 0; j < c.length; j++){
			if(c[j].className == "inviz"){
				c[j].classList.remove("inviz");
				c[j].classList.add("viz");
			}
	}
    for (i = 0; i < li.length; i++) {
        txtValue = li[i].innerHTML;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            figure[i].style.display = "";
        }
		else {
            figure[i].style.display = "none";
			c[i].classList.add("inviz");
        }
    }
}

window.onload = function() {
}