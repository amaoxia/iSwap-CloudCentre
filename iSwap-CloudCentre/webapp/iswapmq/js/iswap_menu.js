/**
 * 说    明：数据交换系统高保真原型临时菜单生成，系统开发时请产品开发人员重写
 * 编写时间：2011.3.27
 * 作    者：陆荣涛
 */

//jQquery cookie插件
jQuery.cookie = function(name, value, options) {   
	 if (typeof value != 'undefined') { // name and value given, set cookie 
		  options = options || {}; 
		 if (value === null) {   
			  value = '';   
			  options.expires = -1;   
		  }   
		 var expires = '';   
		 if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {   
			 var date;   
			 if (typeof options.expires == 'number') {   
				  date = new Date();   
				  date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));   
			  } else {   
				  date = options.expires;   
			  }   
			  expires = '; expires=' + date.toUTCString();   
		  }   
		 var path = options.path ? '; path=' + (options.path) : '';   
		 var domain = options.domain ? '; domain=' + (options.domain) : '';   
		 var secure = options.secure ? '; secure' : '';   
		  document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');   
	  } else {   
		 var cookieValue = null;   
		 if (document.cookie && document.cookie != '') {   
			 var cookies = document.cookie.split(';');   
			 for (var i = 0; i < cookies.length; i++) {   
				 var cookie = jQuery.trim(cookies[i]);   
				 if (cookie.substring(0, name.length + 1) == (name + '=')) {   
					  cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
					 break;   
				  }   
			  }   
		  }   
		 return cookieValue;   
	  }
};

//定义全局xml对象
var globalXML=null;

//cookie设置，用于存储当前点击项
var COOKIE_NAME = 'c_cookie';
function setCookie(c_value){
	var date = new Date();
	date.setTime(date.getTime() + (1000 * 24 * 60 * 60 * 1000));
	$.cookie(COOKIE_NAME,c_value, { path: '/', expires: date });
	return false;
}
setCookie('1');

//定义单击节点的行为：1、清空节点值 2、设置IFRAME URL 节点值
function setNodesBehavior(cValue){
	setCookie(cValue);
	$('.menu3').prevAll().remove();
	$('.menu3').children().remove();
	$('.menu1_drop>ul').children().remove();
	$('.h_title').empty();
	initmenu(globalXML);
	bindMenu();
}

//菜单初始化
function initmenu(xml){
	//遍历menus，查找节点“menu”的节点
	$(xml).find("menu>menu1").each(function(){
		var line="<li onclick=\"setNodesBehavior('"+$(this).attr('tag')+"')\"><a href=\"javascript:void(0)\">"+$(this).attr('name')+"</a></li>\n";
		if(splits()[0]==$(this).attr('tag')){
			$('.h_title').append($(this).attr('name')+"<br/>");
			line = "<li onclick=\"setNodesBehavior('"+$(this).attr('tag')+"')\"><a href=\"javascript:void(0)\" style='background:url(images/top_drop_sbg.gif) 5px 1px repeat-x;color:#014886;margin:0px 5px 0px 0px;'><span style='float:left;'><img src='images/top_drop_leftarrow.gif' style='margin:7px 0px 0px 0px;'/></span><span>"+$(this).attr('name')+"</span></a></li>\n";
		}

		//添加主菜单
		$('.menu1_drop>ul').append(line);

		nextMenu($(this));
	});
}

//遍历下级菜单
function nextMenu(nexMenu){
	var C_String_Array=splits();//c=2
	var currentnexMenuSplit=nexMenu.attr('tag').split('_');//c=2
		if(C_String_Array[currentnexMenuSplit.length-1]!==undefined){
			nexMenu.children().each(function(){
				var currentMenuSplit=$(this).attr('tag').split('_');//c=2_1
				if(C_String_Array[currentMenuSplit.length-1]!==undefined){
					if(C_String_Array[currentMenuSplit.length-1]==$(this).attr('tag')){
						if($(this).children().attr('tag')!=undefined){//判读是否为叶子节点
								var strTemp = "<ul class='menu2'><li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(this).attr('tag')+"')\">"+$(this).attr('name')+"</a><span class='arrow2'></span></li><div class='menu2_drop' style='display:none;'><ul>\n";
								var parents=$(this).parent();
								for(var i=0;i<parents.children().size();i++){//获得同辈元素
									var child=parents.children()[i];
									if($(child).attr('tag')==$(this).attr('tag')){
										strTemp+=	"<li><img src='images/top_drop_leftarrow.gif' align='left' style='margin:8px 0px 0px 6px;'/><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\" style='background:url(images/top_drop_sbg.gif) 5px 1px repeat-x;color:#014886;margin:0px 5px 0px 3px;'>"+$(child).attr('name')+"</a></li>";
										}else{
										strTemp+=	"<li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\">"+$(child).attr('name')+"</a></li>";
										}
									}
									strTemp+="</ul></div></ul>\n";
									$('.menu3').before(strTemp);
									nextMenu($(this));
							
							}else{//如果是叶子节点
								var parents=$(this).parent();
								for(var i=0;i<parents.children().size();i++){
										var child=parents.children()[i];
										if($(child).attr('tag')==$(this).attr('tag')){
											$('.menu3').append("<li><a href='javascript:void(0)' class='menu3_select' onclick=\"setNodesBehavior('"+$(this).attr('tag')+"')\">"+$(this).attr('name')+"</a><img src='images/line.jpg' align='absmiddle' /></li>");
											$("#mainFrame").attr("src",$(this).attr('url'));	//更改iframe内容
											}else{
												$('.menu3').append("<li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\">"+$(child).attr('name')+"</a><img src='images/line.jpg' align='absmiddle' /></li>");
											}
									}
								}
						}
					}else{//如果第一级节点不在数
						if(C_String_Array[currentMenuSplit.length-2]==nexMenu.attr('tag')){
							afterMenu($(this));
						}
					}
				});
	}
}
function afterMenu(nexMenu){
				if(nexMenu.attr('default')=='y'){
					if(nexMenu.children().attr('tag')!=undefined){
							var strTemp="<ul class='menu2'><li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+nexMenu.attr('tag')+"')\">"+nexMenu.attr('name')+"</a><span class='arrow2'></span></li><div class='menu2_drop' style='display:none;'><ul>\n";
							var parents=$(nexMenu).parent();
										for(var i=0;i<parents.children().size();i++){//获得同辈元素
											var child=parents.children()[i];
											if($(child).attr('tag')==nexMenu.attr('tag')){
												strTemp+=	"<li><img src='images/top_drop_leftarrow.gif' align='left' style='margin:8px 0px 0px 6px;' /><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\" style='background:url(images/top_drop_sbg.gif) 5px 1px repeat-x;color:#014886;margin:0px 5px 0px 3px;'>"+$(child).attr('name')+"</a></li>";
												}else{
												strTemp+=	"<li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\">"+$(child).attr('name')+"</a></li>";
												}
											}
							strTemp+="</ul></div></ul>\n";
							$('.menu3').before(strTemp);
							nexMenu.children().each(function(){
								if($(this).attr('default')=='y'){
											afterMenu($(this));
										}
								});
							}else{
								var parents=nexMenu.parent();
								for(var i=0;i<parents.children().size();i++){
										var child=parents.children()[i];
										if($(child).attr('tag')==nexMenu.attr('tag')){
											$('.menu3').append("<li><a href='javascript:void(0)' class='menu3_select' onclick=\"setNodesBehavior('"+nexMenu.attr('tag')+"')\">"+nexMenu.attr('name')+"</a><img src='images/line.jpg' align='absmiddle' /></li>");
											$("#mainFrame").attr("src",nexMenu.attr('url'));	//更改iframe内容
											}else{
												$('.menu3').append("<li><a href='javascript:void(0)' onclick=\"setNodesBehavior('"+$(child).attr('tag')+"')\">"+$(child).attr('name')+"</a><img src='images/line.jpg' align='absmiddle' /></li>");
											}
									}
							}
					}
	}

//当前选中点的字串分析，类似“1_1_2”形式
function splits(){
	var para = $.cookie(COOKIE_NAME);
	var spl=para.split('_');
	var splarray=new Array();
	if(spl[0]==''){
		spl[0]="1";
	}
	var c=spl[0];
	splarray[0]=c;
	for(var i=1;i<spl.length;i++){
		c+="_"+spl[i];
		splarray[i]=c;
	}
	return splarray;
}

//菜单项事件绑定

function bindMenu(){
//菜单收缩
	/****第一层菜单****/
	$('.logo,.logo_h').bind('mouseover', function() {
		$(".menu2_drop").slideUp('fast');
		$('.logo_h').slideDown('fast');
		$('.arrow2').css({'background':'url(./images/arrow_right.png) no-repeat'});
		return false;
	});
	
	/****第二层菜单****/
	$('.menu2').eq(0).bind('mouseover', function() {
		$(".logo_h").slideUp("fast");
		$('.menu2_drop').eq(1).slideUp("fast");
		$('.menu2_drop').eq(2).slideUp("fast");
		$('.menu2_drop').eq(0).slideDown('fast');

		$('.arrow2').eq(0).css({'background':'url(./images/arrow_down.png) no-repeat'});
		$('.arrow2').not($('.arrow2')[0]).css({'background':'url(./images/arrow_right.png) no-repeat'});

		//alert(globalXML);

		return false;
	});

	/****第三层菜单****/
	$('.menu2').eq(1).bind('mouseover', function() {
		$(".logo_h").slideUp("fast");
		$('.menu2_drop').eq(0).slideUp("fast");
		$('.menu2_drop').eq(2).slideUp("fast");
		$('.menu2_drop').eq(1).slideDown('fast');

		$('.arrow2').eq(1).css({'background':'url(./images/arrow_down.png) no-repeat'});
		$('.arrow2').not($('.arrow2')[1]).css({'background':'url(./images/arrow_right.png) no-repeat'});

		return false;
	});

	/****第四层菜单****/
	$('.menu2').eq(2).bind('mouseover', function() {
		$(".logo_h").slideUp("fast");
		$('.menu2_drop').eq(0).slideUp("fast");
		$('.menu2_drop').eq(1).slideUp("fast");
		$('.menu2_drop').eq(2).slideDown('fast');

		$('.arrow2').eq(2).css({'background':'url(./images/arrow_down.png) no-repeat'});
		$('.arrow2').not($('.arrow2')[2]).css({'background':'url(./images/arrow_right.png) no-repeat'});

		return false;
	});

	/****菜单的隐藏****/
	$('body').bind('mouseover', function(event) {
		$(".logo_h").slideUp("fast");
		$(".menu2_drop").slideUp("fast");
		$('.arrow2').css({'background':'url(./images/arrow_right.png) no-repeat'});
		return false;
	});

//收藏菜单控制
	$(".foot_select img").bind('click', function(event) {
		$("#my").toggle("slow");
	});
	$(".mya img").bind('click', function(event) {
		$("#my").toggle("slow");
	});
}

$(document).ready(function(){
	//载入xml  js/iswap_menu.xml
	if(navigator.cookieEnabled == true){
		jQuery.ajax({
			url:"sysmanager/permission/permission!createPermissionXml.action", //XML文件相对路径
			type:"POST",            //发送请求的方式
			dataType:"xml",         //指明文件类型为“xml”
			timeout:1000,           //超时设置，单位为毫秒
			async:false,			//设置为同步方式
			error:function(xml){    //解析XML文件错误时的处理
				$("#Message_error").text("Error loading XML document (加载XML文件出错)"+xml);
				$("#Message_error").show();
				alert(xml);
			},
			success:function(xml){ 
				globalXML = xml;
			}
		});
	}
	initmenu(globalXML);
	bindMenu();
});