<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>iSwapV7.0政务资源数据交换共享服务平台</title>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/iswap_menu.js" type="text/javascript"></script>
<script src="js/iswap_common.js" type="text/javascript"></script>
<link href="css/header.css" rel="stylesheet" type="text/css" />
<link href="css/footer.css" rel="stylesheet" type="text/css" />
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
		function logoOut(){
			if(confirm("确定要退出系统吗?")){
				window.location="logout.action";
			}
		}
</script>
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" class="table">
<tr>
<td id="results"></td>
</tr>
  <tr>
    <td><div class="header">
        <div class="frameset_w">
          <div class="header">
            <div class="logo"><span class="arrow1"></span></div>
            <div class="logo_h" id="logo_h" style="display:none;">
              <div class="logo_img"><span class="arrow3"></span></div>
              <div class="menu1_drop">
                <ul>
                </ul>
              </div>
            </div>
            <div class="h_main">
              <div class="h_top">
              	<div class="h_title"></div> 
              	<div class="t_main">
                    <span class="logo_s"><img src="images/logo_s2.png"/><a href="${path}/index.jsp"><img src="images/nav_map.jpg" style="cursor:pointer;" title="回首页"/></a></span> 
					<span class="clothes"><img src="images/clothes_blue.png" /><img src="images/clothes_yellow.png"  /><img src="images/clothes_green.png"  /></span> 
					<span class="close"><img src="images/close.png" name="Image2" width="37" height="26" border="0" id="Image2"  onclick="logoOut()"/></span> 
              	</div>
              </div>
              <div class="t_menu">
                <ul class="menu3"></ul>
              </div>
            </div>
          </div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td class="main"><iframe frameborder="0" id="mainFrame" scrolling="no" height="200" src="loading.html"></iframe></td>
  </tr>
  <tr>
    <td><div class="foot">
        <div class="frameset_w1">
          <div class="footer">
            <div id="footL"> <span>${roleName?default('')}：</span><span>${userName?default('')}</span> <span style="padding-left:24px;">技术支持：</span><span>13910975893</span> </div>
            <div id="footR"> <span><a href="javascript:void(0)" class="foot_select"><img src="images/f_sc.png" align="absmiddle" /></a></span> <span><a href="javascript:void(0)"><img src="images/f_jl.png" align="absmiddle" /></a></span> <span><a href="javascript:void(0)"><img src="images/f_help.png" align="absmiddle" /></a></span> <span class="gy"><a href="javascript:void(0)">关于</a></span></div>
          </div>
        </div>
      </div>
      <div id="my" style="display:none;">
        <div class="myt"> <span>我的收藏</span>
          <div class="mya"><a href="javascript:void(0)"><img src="images/close_h.png" /></a></div>
        </div>
        <ul class="myc">
          <li><a href="javascript:void(0)" class="myc_select">流程管理</a></li>
          <li><a href="javascript:void(0)">运行管理</a></li>
          <li><a href="javascript:void(0)">新建流程内容</a></li>
          <li><a href="javascript:void(0)">部署流程内容</a></li>
        </ul>
        <div style="height:3px; background:url(images/my_4.png) no-repeat;"></div>
      </div>
      <div id="popnav" style="display:none;">
      <img src="images/app_map_2.png" border="0" usemap="#Map" />      </div>
    </td>
  </tr>
</table>

<map name="Map" id="Map">
<area shape="rect" coords="602,0,635,28" href="#" id="mm1" />
<area shape="rect" coords="240,395,362,473" href="#" onclick="window.location.href='index.html?c=11';$('#popnav').hide('slow')" />
<area shape="rect" coords="72,222,180,312" href="index.html?c=10" onclick="window.location.href='index.html?c=10';$('#popnav').hide('slow')" />
<area shape="rect" coords="118,343,232,427" href="index.html?c=10_2" onclick="window.location.href='index.html?c=10_2';$('#popnav').hide('slow')" />
<area shape="rect" coords="378,370,505,450" href="index.html?c=7" onclick="window.location.href='index.html?c=7';$('#popnav').hide('slow')" />
<area shape="rect" coords="446,265,584,365" href="index.html?c=8" onclick="window.location.href='index.html?c=8';$('#popnav').hide('slow')" />
<area shape="rect" coords="452,155,578,244" href="index.html?c=1" onclick="window.location.href='index.html?c=1';$('#popnav').hide('slow')" />
<area shape="rect" coords="334,36,461,137" href="index.html?c=9" onclick="window.location.href='index.html?c=9';$('#popnav').hide('slow')" />
<area shape="rect" coords="208,35,328,136" href="index.html?c=3" onclick="window.location.href='index.html?c=3';$('#popnav').hide('slow')" />
<area shape="rect" coords="98,110,203,194" href="index.html?c=3_4" onclick="window.location.href='index.html?c=3_4';$('#popnav').hide('slow')" />
</map>

<script type="text/javascript">
	$(document).ready(function() {
		$("#mainFrame").width($("body").width() + "px");
		$("#mainFrame").height($("body").height() - 112 + "px");
	});
	$(window).resize(function(arg) {
		$("#mainFrame").width($("body").width() + "px");
		$("#mainFrame").height($("body").height() - 112 + "px");
	});
	//点击IFRAME隐藏菜单
	function hideMenu(){
		$(".logo_h").slideUp("fast");
		$(".menu2_drop").slideUp("fast");
		$('.arrow2').css({'background':'url(./images/arrow_right.png) no-repeat'});
	}
	window.onscroll=function(){
		$("back_div").style.width=document.body.scrollWidth;
		$("back_div").style.height=document.body.scrollHeight;
	}
	$(document).ready(function(){
		//应用导航图层居中
		$("#popnav").each(function(i,o){   
			$(o).css("left",(($(document).width())/2-(parseInt($(o).width())/2))+"px");   
			$(o).css("top",(($(document).height())/2-(parseInt($(o).height())/2))+"px");   
		});
		//点击顶端按钮收藏

		$("#nagimg").bind('click', function(event) {
			$("#popnav").toggle("slow");
		});
		$("#mm1").bind('click', function(event) {
			$("#popnav").toggle("slow");
		});
	});
</script></body>
</html>
