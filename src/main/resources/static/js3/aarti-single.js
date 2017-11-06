$(document).ready(function() {
	var id = getJsonFromUrl().key;
	console.log(id);
	$.ajax({
		url : "/aarti/" + id,
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
	if(data) {
		var html = '';
		// set page title
		// page heading and desc.
		if(data.name) {
			document.title += ' - ' + data.name;
			var headElem = document.getElementById("heading1");
			html = '<div><h3>' + data.name + '</h3></div>';
			if(data.shortDesc) {
				html += '<div><p>' + data.shortDesc + '</p></div>';
			}
			headElem.innerHTML = html;
			html ='';
		}
	
		var descElem = document.getElementById("desc");
		var langSelectElem = document.getElementById("langSelected");
		var descHtml = '';
		// set language drop-down and main data
		if(data.mainDesc && data.mainDesc.length) {
			for(var i=0;i<data.mainDesc.length; i++) {
				var result = data.mainDesc[i];
				if(result.prefLang) {
					html += '<option value="'+result.lang+'" selected="selected">'+toTitleCase(result.lang)+'</option>';
					descHtml += '<div id="'+ result.lang +'" style="display:block" <p>' + result.content + '</p></div>';
					$("#hdnPrevValue").val(result.lang);
				} else {
					html += '<option value="'+result.lang+'">'+toTitleCase(result.lang)+'</option>';
					descHtml += '<div id="'+ result.lang +'" style="display:none" <p>' + result.content + '</p></div>';
				}
			}
			langSelectElem.innerHTML = html;
			html = '';
		} else {
			descHtml += '<p class="dotted">No data found</p>';
		}
		descElem.innerHTML = descHtml;
		
		// set main image
		if(data.image) {
			var imgElem = document.getElementById("img");
			html = '<a href="#">';
			html += '<img class="img-responsive-mod" src="' + data.image + '" alt="">';
			html += '</a>';
			imgElem.innerHTML = html;
			html = '';
		}
	}
}

function toTitleCase(str) {
    var lcStr = str.toLowerCase();
    return lcStr.replace(/(?:^|\s)\w/g, function(match) {
        return match.toUpperCase();
    });
}

function getContent() {
	var prev_val = $("#hdnPrevValue").val();
	var curr_val = $("#langSelected").val(); 
	
	document.getElementById(prev_val).style.display='none';
	document.getElementById(curr_val).style.display='block';
	$("#hdnPrevValue").val(curr_val);
}