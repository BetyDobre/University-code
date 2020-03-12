//video

function addVideo() {
	var iframe = document.createElement('iframe');
	iframe.src = 'https://www.youtube.com/embed/v=WFaQ6xucqo0';
	iframe.width = '560';
	iframe.height = '315';

	var bottom = document.getElementById('bottom');
	bottom.appendChild(iframe);
}

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
      } 
	  else if (sort == "descrescator") {
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
	addVideo();
}