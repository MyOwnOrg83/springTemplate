var baseLink = 'http://localhost:8080';
$(document).ready(function() {
	var id = getJsonFromUrl().key;
	console.log(id);
	$.ajax({
		url : baseLink + "/aarti/" + id,
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("aarti single is called");
			return parseData(resultData);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
			window.location.href='/errorpage';
		},

		timeout : 120000,
	});

});

function getJsonFromUrl() {
	var query = location.search.substr(1);
	var result = {};
	query.split("&").forEach(function(part) {
		var item = part.split("=");
		result[item[0]] = decodeURIComponent(item[1]);
	});
	return result;
}

function parseData(data) {
	document.title += ' - ' + data.name;

	var imgElem = document.getElementById("img");
	html = '<a href="#">';
	html += '<img class="img-responsive" src="' + data.image + '" alt="">';
	html += '</a>';
	imgElem.innerHTML = html;

	var headElem = document.getElementById("heading1");
	html = '<div><h3>' + data.name + '</h3></div>';
	html += '<div><p>' + data.shortDesc + '</p></div>';
	headElem.innerHTML = html;
	html ='';
	var descElem = document.getElementById("desc");
	var langSelectElem = document.getElementById("langSelected");
	if(data.mainDesc) {
		for(var i=0;i<data.mainDesc.length; i++) {
			var result = data.mainDesc[i];
			
			if(langSelectElem[langSelectElem.selectedIndex].value == result.lang) {
				html += '<div id="'+ result.lang +'" style="display:block" <p>' + result.content + '</p></div>';
				$("#hdnPrevValue").val(result.lang);
			} else {
				html += '<div id="'+ result.lang +'" style="display:none" <p>' + result.content + '</p></div>';
			}			
		}
	} else {
		html += '<p class="dotted">' + data.shortDesc + '</p>';
	}
	descElem.innerHTML = html;
}

function getContent() {
	var prev_val = $("#hdnPrevValue").val();
	var curr_val = $("#langSelected").val(); 
	
	document.getElementById(prev_val).style.display='none';
	document.getElementById(curr_val).style.display='block';
	$("#hdnPrevValue").val(curr_val);
}