<%@page import="com.ligitalsoft.model.system.SysUser"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
SysUser user = (SysUser)session.getAttribute("user");
String paths = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<meta http-equiv="x-ua-compatible" content="ie=7" />
<title>中心管理页面</title>
<link type="text/css" rel="stylesheet" href="css/reset.css" />
<link type="text/css" rel="stylesheet" href="css/layout.css" />
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js"></script>
<%--<script type="text/javascript" src="js/jquery-1.5.1.js"></script>--%>
<script  language="JavaScript" type="text/javascript">
 DD_belatedPNG.fix('.main_bg,.index_an li a.img01,.index_an .v span,.index_an li a:hover,.footer,.tz,img,background'); 
 
<%-- $(document).ready(function(){--%>
<%--	$("#cc").jscroll({W:"6px"});--%>
<%--	--%>
<%--});--%>
function closeWin(){
	window.opener = null; //禁止关闭窗口的提示
    window.close(); //自动关闭本窗口
}

</script>  

</head>
<body>

<div id="header">
  <div class="logo floatLeft">扬州综合治税共享服务平台</div>
  <div class="header_R floatRight">
    <div class="header_exit floatRight"> <span class="annul floatLeft"><a href="<%=paths%>/logout.action"></a></span> <span class="exit floatLeft"><a href="javascript:void(0);" onclick="javascript:closeWin();"></a></span> </div>
    <div class="Clearboth"></div>
    <div class="time floatRight">欢迎  <%=user.getUserName()%>  登录平台</div>
  </div>
  <div class="Clearboth"></div>
</div>

<div id="main">
  <div class="main_bg margin0Auto">
    <div class="index_an margin0Auto">
      <ul>
   <li><a href="#"><img src="imgs/img01.png" />文档采集系统</a></li>
   <li><a href="<%=paths%>/menu.action"><img src="imgs/img02.png" />交换共享中心</a></li>
   <li><a href="#"><img src="imgs/img03.png" />共享展示平台</a></li>
   <li><a href="#"><img src="imgs/img04.png" />数据监督系统</a></li>
   <li><a href="#"><img src="imgs/img05.png" />绩效考核系统</a></li>
   <li><a href="#"><img src="imgs/img06.png" />全文检索系统</a></li>
   </ul>
 </div>
  </div> 
</div>
<div class="tz margin0Auto"></div>

</body>
</html>
