var baseLink = 'http://localhost:8080';
$(document).ready(function() {
	var id = getJsonFromUrl().key;
	console.log(id);
	$.ajax({
		url : baseLink + "/pooja/" + id,
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("pooja single is called");
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
	/*var nameElem = document.getElementById("name");
	var html = '<h1 class="page-header">' + data.name + '</h1>';
	nameElem.innerHTML = html;*/

	var imgElem = document.getElementById("img");
	html = '<a href="#">';
	html += '<img class="img-responsive" src="' + data.image + '" alt="">';
	html += '</a>';
	imgElem.innerHTML = html;

	var descElem = document.getElementById("desc");
	html = '<h3>' + data.name + '</h3>';
	html += '<h4>Description</h4>';
	if(data.mainDesc) {
		html += '<p>' + data.mainDesc + '</p>';
	} else {
		html += '<p>' + data.shortDesc + '</p>';
	}
	descElem.innerHTML = html;
}