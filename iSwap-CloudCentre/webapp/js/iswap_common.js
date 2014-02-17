/**
 * 说    明：数据交换系统通用方法
 * 编写时间：2011.4.14
 * 作    者： 
 */

//取消标签冒泡事件，用于通用表格
$(document).ready(function(){
	//取消A
	$(".tabs1 a").click(function(event) {
		event.stopPropagation();
	});
	$(".tabs1 input").click(function(event) {
		event.stopPropagation();
		//alert($(this).parent().parent().css("background-image"));
		/*
		if($(this).parent().parent().css("background-image")==''){
			$(this).parent().parent().css({"background-image":"url(images/tabs_tr.jpg)"});
		}else{
			$(this).parent().parent().css({"background-image":""});
		}
		*/
	});
	$(".tabs3 a").click(function(event) {
		event.stopPropagation();
	});
	$(".tabs3 input").click(function(event) {
		event.stopPropagation();
	});
});



//页签切换效果，用于数据共享/数据查询
$(document).ready(function(){
	for(var i=0; i<$('.ltab').children().size(); i++){
		$('.ltab').children().eq(i).click(function(){
			$(this).children().addClass("ltab_s");
			$(this).siblings().children().removeClass("ltab_s");
			$("#tree").attr("src",$(this).attr("ifrmUrl"));
		});
	}
});

