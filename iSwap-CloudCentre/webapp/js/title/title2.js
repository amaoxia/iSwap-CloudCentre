jQuery.fn.toolTip = function() { 
this.unbind().hover( 
function(e) { 
this.t = this.title; 
this.title = ''; 
$('body').append( '<p id="p_toolTip" style="display:none; max-width:320px;text-align:left;"><img id="img_toolTip_Arrow" src="images/arrow.gif" />' + this.t + '</p>' ); 
var tip = $('p#p_toolTip').css({ "position": "absolute", "padding": "10px 5px 5px 10px", "left": "5px", "font-size": "14px", "background-color": "white", "border": "1px solid #a6c9e2","line-height":"160%", "-moz-border-radius": "5px", "-webkit-border-radius": "5px", "z-index": "9999"}); 
var target = $(this); 
var position = target.position(); 
this.top = (position.top - 8); this.left = (position.left + target.width() + 5); 
$('p#p_toolTip #img_toolTip_Arrow').css({"position": "absolute", "top": "8px", "left": "-6px" }); 
tip.css({"top": this.top+"px","left":this.left+"px"}); 
tip.fadeIn("slow"); 
}, 
function() { 
this.title = this.t; 
$("p#p_toolTip").fadeOut("slow").remove(); 
} 
); 
}; 