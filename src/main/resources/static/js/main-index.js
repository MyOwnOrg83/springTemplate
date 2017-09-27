var baseLink = 'http://localhost:8080';
$(document).ready(function() {

	$.ajax({
		url : baseLink+"/mainpoojas/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			console.log("this is called");
			return parseData(resultData[0].content, 'pooja');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : baseLink+"/mainaartis/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			return parseData(resultData[0].content, 'aarti');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log("error is called");
		},

		timeout : 120000,
	});
	
	$.ajax({
		url : baseLink+"/mainvrats/3",
		type : "GET",

		dataType : 'json',
		success : function(resultData) {
			//here is your json.
			// process it
			return parseData(resultData[0].content, 'vrat');
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
		'<a href="'+ baseLink +'/'+ pageType +'-single.html?key='+ d.id +'" class="portfolio-box">'+
	    '<img src="' + d.icon + '" class="img-responsive-mod" alt="">'+
	    '<div class="portfolio-box-caption">'+
        '<div class="portfolio-box-caption-content">'+
        '<div class="project-category text-faded">'+
        'Pooja '+
        '</div>'+
        '<div class="project-name">'+
        d.name+
        '</div></div></div></a></div>';
	};
	
	html += '</div></div>';
	
	mainElem.innerHTML = html;
}

function exportToForm(id) {
	
	$.ajax({
		url : "http://localhost:8080/pooja/"+id,
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