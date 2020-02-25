function openMenu() {
	var x = document.getElementById("myTopnav");
	if (x.className === "topnav") {
		x.className += " responsive";
	} else {
		x.className = "topnav";

	}
}

function on() {
	document.getElementById("serviceButtons").style.display = "block";
}

function off() {
	document.getElementById("serviceButtons").style.display = "none";
}

/* Open when someone clicks on the span element */
function openDiv(id) {
	document.getElementById(id).style.display = "block";
}

/* Close when someone clicks on the "x" symbol inside the overlay */
function closeDiv(id) {
	document.getElementById(id).style.display = "none";
}

/*Text highlighting*/
var tempText;
var newText;

function addColor(idName, letter, n) {
	if (n == 0) {
		tempText = idName.textContent;
	}
	if (idName.nodeType != 1 || !idName.hasAttribute('edited')) {
		if (idName.nodeType == 3) {
			newText = document.createElement('span');
			newText.innerHTML = idName.textContent;
			newText.setAttribute('edited', true);
			
			var text = newText.innerHTML.split('').map(function(el) {
				if (el == letter) {
					return '<span class="highlight">' + el + '</span>';	
				} else {
					i = i + 1;
					return el;
				}
			
			}).join('');

			newText.innerHTML = text;
			idName.parentNode.replaceChild(newText, idName);
		}
		for (var i = 0; i < idName.childNodes.length; i++) {
			addColor(idName.childNodes[i], letter, 1);
		}
	}
}


function removeColor() {
	document.getElementById('phrase').textContent = tempText;
}


function turnCaesar(a){
	document.getElementById("disk1").style.transform = "rotate("+ (-1)*a*13.8461538 +"deg)";
}