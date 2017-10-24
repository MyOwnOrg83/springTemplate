$(document).ready(function() {

	$.ajax({
		url : "/mainpoojas/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("this is called");
			return parseData(resultData, 'pooja');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : "/mainaartis/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			return parseData(resultData, 'aarti');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : "/mainvrats/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			return parseData(resultData, 'vrat');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : "/mainchalisas/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("this is called");
			return parseData(resultData[0].content, 'chalisa');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
});

function parseData(data, pageType) {
	var mainElem = document.getElementById(pageType+'-portfolio');
//	var data = JSON.parse(jsondata);
	var html = '<div class="container-fluid">'+
        '<div class="row no-gutter popup-gallery">';
//	data.forEach(function(d) {
//	var len = data.length;
	for(var i=0; i<data.length; i++){
		var d = data[i];
		
		html += '<div class="col-lg-4 col-sm-6">'+
		'<a href="/'+ pageType +'-single.html?key='+ d.id +'" class="portfolio-box">'+
	    '<img src="' + d.icon + '" class="img-responsive-mod" alt="">'+
	    '<div class="portfolio-box-caption">'+
        '<div class="portfolio-box-caption-content">'+
        '<div class="project-category text-faded">'+
        	pageType +
        '</div>'+
        '<div class="project-name">'+
        d.name+
        '</div></div></div></a></div>';
	};
	
	html += '</div></div>';
	
	mainElem.innerHTML = html;
}

function toTitleCase(str) {
    var lcStr = str.toLowerCase();
    return lcStr.replace(/(?:^|\s)\w/g, function(match) {
        return match.toUpperCase();
    });
}

function exportToForm(id) {
	
	$.ajax({
		url : "/pooja/"+id,
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("this is called");
//			return parseData(resultData, 'pooja-portfolio');
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
}

$(document).ready(function () {
    $("nav").find("li").on("click", "a", function () {
        $('.navbar-collapse.in').collapse('hide');
    });
});
