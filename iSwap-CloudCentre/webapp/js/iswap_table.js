$(document).ready( function() {

//重置表格
	if(setBaseTbHeight){
		setBaseTbHeight();
	}
	
	$('<div>').attr('id', 'abc').prependTo($('body'));

	$("#abc").append($("table.tabs1").find("tr").eq(0).clone(true));
	
	$("#abc").children().wrap("<table class='tabs1' style='margin-top:0px;'></table>");
	$("#abc").append("<div id='tbl' style='width:98%;"+getHeight()+"margin:0 auto;overflow-x:hidden'></div>");
	
	$("table.tabs1").eq(1).find("tr").eq(0).nextAll().each( function(i){
		$("#tbl").append($(this).clone(true));
	});

	$("#tbl").wrapInner("<table class='tabs1' style='margin-top:0px;width:100%;'></table>");

	$("table.tabs1").eq(2).replaceWith($("#abc"));


//调整表头宽度
	window.resizeTblHeadWidth = function() {
		$("table.tabs1").eq(1).find("tr").eq(0).find("td").each ( function(i) {
			var tdLength = $("table.tabs1").eq(1).find("tr").eq(0).find("td").length;
			if (i < tdLength-1) {
				var th = $("table.tabs1").eq(0).find("tr").eq(0).find("th");
				var No1 = $(this).offset().left;
				var No2 = $(this).next("td").offset().left;
				var tdWidth = No2 - No1;
				th.eq(i).css("width", tdWidth+"px");
			}
		});
	}

//调整滚动条高度
	window.resizeScrollHeight = function () {
		var iFrameHeight = $(top.document.getElementById("mainFrame")).height();
		var divHeight = $("#tbl").height();
		var restHeight = iFrameHeight-160;

		if (divHeight > restHeight) {
			$("#tbl").css({"height":restHeight+"px" , "overflow-y":"auto" });
		}else{
			$("#tbl").css({"height":$("#tbl").children().height()+"px" , "overflow-y":"hidden" });
		}
	}
	resizeTblHeadWidth();
	resizeScrollHeight();

});

$(window).resize( function() {
	resizeTblHeadWidth();
	resizeScrollHeight();
});
function getHeight(){
	if(getDivHeight){
		return "height:"+getDivHeight()+"px;";
	}
}
