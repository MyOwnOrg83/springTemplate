/**
 * 
 */
var checkStatus;

var element = document.createElement('any');
Object.defineProperty(element, 'id', {
	  get:function() {
//element.__defineGetter__('id', function() {
    checkStatus = 'on';
	  }
});

/*setInterval(function() {
    checkStatus = 'off';
    console.log(element);
    console.clear();
    if(checkStatus == 'on') {
    	document.querySelector('#devtool-status').innerHTML = "Please close the developer console to load the page.";
    }
    if (window.console && (window.console.firebug || window.console.exception)) {
//    	  alert("firebug is enabled");
    	}
}, 1000);*/

const { classes: Cc, interfaces: Ci, utils: Cu } = Components;
Cu.import("resource://devtools/shared/Loader.jsm");
var HUDService = devtools.require("devtools/client/webconsole/hudservice");

var hud = HUDService.getBrowserConsole();
hud.jsterm.clearOutput(true);

// for any browser F12 and ctrl+u prevention
$(document).bind("contextmenu",function(e) {
 e.preventDefault();
});

// Only Chrome preventions
window.oncontextmenu = function() {
	return false;
}
$(document).keydown(function(evt) {
	var event = (evt.which || evt.keyCode);
	if (event == 123) {
		return false;
	} else if ((evt.ctrlKey && evt.shiftKey && event == 73)
			|| (evt.ctrlKey && evt.shiftKey && event == 74)
			|| (evt.ctrlKey && evt.shiftKey && event == 67)
			|| (evt.ctrlKey && evt.shiftKey && event == 75)
			|| (evt.ctrlKey && evt.shiftKey && event == 83)
			|| (evt.ctrlKey && event == 85)
			|| (evt.ctrlKey && event == 65)
			|| (evt.ctrlKey && event == 67)) {
		return false;
	}
});
document.onkeypress = function (event) {
    event = (event || window.event);
    if (event.keyCode == 123) {
       //alert('No F-12');
        return false;
    }
}
document.onmousedown = function (event) {
    event = (event || window.event);
    if (event.keyCode == 123) {
        //alert('No F-keys');
        return false;
    }
}
document.onkeydown = function (event) {
    event = (event || window.event);
    if (event.keyCode == 123) {
        //alert('No F-keys');
        return false;
    }
}