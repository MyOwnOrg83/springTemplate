//var baseLink = 'http://localhost:8080';
var total;
var pageId = 0;
$(document).ready(function() {
	
	$.ajax({
		url : "/aartiCount",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("aarti main is called"+resultData);
			total = resultData;
			pageIdElem = document.getElementById('currPageId');
			pageId = pageIdElem.getAttribute('show-main')
			return showPage(pageId);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("aarti main:Could not get pooja count");
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

function parseData(results) {
	var $myDiv = $('<li>'); // create li 
	var content = results[0].content;
	for(var i in content){
		var html = '';
		var result = content[i];
		console.log(result);
		html += '<div class="row">';
        html += '<div class="col-md-6">';
        html += '<a href="aarti-single.html?key='+result.id+'">';
        html += '<img class="img-responsive-mod-vrat" src="'+ result.image+'" alt="">';
        html += '</a>';
        html += '</div>';
        html += '<div class="col-md-6">';
        html += '<h3>'+result.name+'</h3>';
//        html += '<h4>About</h4>';
        html += '<p>'+result.shortDesc+'</p>';
        html += '<a class="btn btn-primary" href="aarti-single.html?key='+result.id+'">View More <span class="glyphicon glyphicon-chevron-right"></span></a>';
        html += '</div>';
        html += '</div>';
        $myDiv.append(html + '</li><hr>'); // append list item to li
    }

    $('#myList').html($myDiv); // append all li to myList
    
    
}

function showPage(pageId) {
	$.ajax({
		url : "/aartis/"+pageId,
		type : "GET",
		async : 'false',
		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("show page aarti main is called"+pageId);
			parseData(resultData);
			parsePaging(pageId, total);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("show page aarti main:error is called");
			window.location.href='/errorpage';
		},

		timeout : 120000,
	});
}

function parsePaging(pageId, total) {
	var pagingElem = document.getElementById("paging");
    document.getElementById('currPageId').setAttribute('show-main', pageId);
    var html = '<ul class="pagination">';
    	html += '<li>';
        html += '<a onclick=showPage('+ (((pageId-1) < 0) ? 0 : (pageId-1)) +'); return false;>&laquo;</a>';
        html += '</li>';
    
    for(var i=0; i<total; i++) {
    	html += '<li';
    	if(pageId == i) {
    		html += ' class="active"><a onclick=showPage('+ i +'); return false;>' + (i+1) +'</a>';
    	} else {
    		html += '><a onclick=showPage('+ i +'); return false;>'+(i+1)+'</a>';
    	}
        html += '</li>';
    }
    
    html += '<li><a onclick=showPage('+ ((pageId+1) > total-1 ? total-1 : (pageId+1)) +'); return false;>&raquo;</a></li></ul>';
    
    pagingElem.innerHTML = html;
}
