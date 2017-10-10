var baseLink = 'http://localhost:8080';
var total;
var pageId = 0;
$(document).ready(function() {
	
	$.ajax({
		url : baseLink + "/poojaCount",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("pooja main is called "+resultData);
			total = resultData;
			pageIdElem = document.getElementById('currPageId');
			pageId = pageIdElem.getAttribute('show-main')
			return showPage(pageId);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("pooja main:Could not get pooja count");
//			window.location.href='/errorpage';
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : "/category",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("cat is called "+resultData);
			return setFilter(resultData);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("pooja main:Could not get pooja cat");
//			window.location.href='/errorpage';
		},

		timeout : 120000,
	});
});

function setFilter(cats) {
	var filt = document.getElementById('filt-cat');
	var html = '<button id="catSelected" class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select Category <span class="caret"></span></button>';
	html += '<ul class="dropdown-menu">';
	html += '<li><a href="#" onclick="changeFilter(\'catSelected\',1, \'Category\')">Category</a></li>';
	for(var i in cats){
		var cat = cats[i];
		if((cat.type && cat.type.toUpperCase() == 'POOJA') || cat.name.toUpperCase() == "ALL" ) {
			html += '<li><a href="#" onclick="changeFilter(\'catSelected\','+ cat.id +', \''+ cat.name +'\')">'+ cat.name +'</a></li>';
		}
	}
	html += '</ul>';	
	filt.innerHTML = html;
	
	filt = document.getElementById('filt-item');
	html = '<button id="itemSelected" class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Select Items/Page <span class="caret"></span></button>';
	html += '<ul class="dropdown-menu">';
	html += '<li><a href="#" onclick="changeFilter(\'itemSelected\',3, \'Items on page\')">Items/Page</a></li>';
	html +=	'<li><a href="#" onclick="changeFilter(\'itemSelected\',2, \'2\')">2</a></li>';
	html += '<li><a href="#" onclick="changeFilter(\'itemSelected\',3, \'3\')">3</a></li>';
	html += '<li><a href="#" onclick="changeFilter(\'itemSelected\',5, \'5\')">5</a></li>';
	html += '<li><a href="#" onclick="changeFilter(\'itemSelected\',10, \'10\')">10</a></li>';
	html += '</ul>';
	
	filt.innerHTML = html;
}

function changeFilter(filterId, itemId, itemName) {
	console.log("selected filter is "+ filterId);
	var currCatId = document.getElementById('filt-cat').getAttribute('show-filt');
	var currItemPageCount = document.getElementById('filt-item').getAttribute('show-item');
	if(filterId === 'catSelected') {
		var elem = document.getElementById('catSelected');
		elem.innerHTML = itemName+' <span class="caret"></span>';
		document.getElementById('filt-cat').setAttribute('show-filt', itemId);
		currCatId = itemId;
	} else if(filterId === 'itemSelected') {
		var elem = document.getElementById('itemSelected');
		elem.innerHTML = itemName+' <span class="caret"></span>';
		document.getElementById('filt-item').setAttribute('show-item', itemId);
		currItemPageCount = itemId;
	}
	$.ajax({
		url : "/poojaCountByFilter/"+currCatId+"/"+currItemPageCount,
		type : "GET",
		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("pooja count by cat is called "+resultData);
			total = resultData;
			
			showPage(0);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("pooja main:Could not get pooja count by cat");
//			window.location.href='/errorpage';
		},

		timeout : 120000,
	});
}

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
	var content = results;
	for(var i in content){
		var html = '';
		var result = content[i];
		console.log(result);
		html += '<div class="row">';
        html += '<div class="col-md-6">';
        html += '<a href="pooja-single.html?key='+result.id+'">';
        html += '<img class="img-responsive-mod-vrat" src="'+ result.image+'" alt="">';
        html += '</a>';
        html += '</div>';
        html += '<div class="col-md-6">';
        html += '<h3>'+result.name+'</h3>';
      /*  html += '<h4>About</h4>'; */
        html += '<p>'+result.shortDesc+'</p>';
        html += '<a class="btn btn-primary" href="pooja-single.html?key='+result.id+'">View More <span class="glyphicon glyphicon-chevron-right"></span></a>';
        html += '</div>';
        html += '</div>';
        $myDiv.append(html + '</li><hr>'); // append list item to li
    }

    $('#myList').html($myDiv); // append all li to myList
    
    
}

function showPage(pageId) {
	catId = document.getElementById('filt-cat').getAttribute('show-filt');
	itemPage = document.getElementById('filt-item').getAttribute('show-item');
	$.ajax({
		url : baseLink + "/poojas/"+pageId+"/"+catId+"/"+itemPage,
		type : "GET",
		async : 'false',
		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("pooja main is called"+pageId);
			parseData(resultData);
			parsePaging(pageId, total);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("show page pooja main:error is called");
//			window.location.href='/errorpage';
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
    	if(pageId === i) {
    		html += ' class="active"><a onclick=showPage('+ i +'); return false;>' + (i+1) +'</a>';
    	} else {
    		html += '><a onclick=showPage('+ i +'); return false;>'+(i+1)+'</a>';
    	}
        html += '</li>';
    }
    
    html += '<li><a onclick=showPage('+ ((pageId+1) > total-1 ? total-1 : (pageId+1)) +'); return false;>&raquo;</a></li></ul>';
    
    pagingElem.innerHTML = html;
}
