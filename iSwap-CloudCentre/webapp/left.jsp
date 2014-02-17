<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="core" prefix="c"%>
<%@ taglib uri="fmt" prefix="f"%>
<%@ taglib uri="pager" prefix="p"%>
<%@ taglib uri="fn" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>iSwap数据交换共享服务平台</title>
<link href="${path}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/header.css" rel="stylesheet" type="text/css" />
<link href="${path}/js/liger/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<link rel="stylesheet" href="${path}/css/zTreeStyle/zTreeIcons.css"
	type="text/css">
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<script type="text/javascript"
	src="${path}/js/ztree/jquery.ztree-2.6.js"></script>
<script src="${path}/js/liger/lib/ligerUI/js/ligerui.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var menuArray = [];
	menuArray["中心控制管理"] = 1;
	menuArray["前置机定制管理"] = 2;
	menuArray["数据源表管理"] = 3;
	menuArray["平台管理"] = 4;
	menuArray["应用指标管理"] = 5;
	
	var tab = null;
	var accordion = null;
	$(function() {
		$('#headMenu li').click(function() {
			$('#headMenu li').removeClass('curr');
			$(this).addClass('curr');
			changeStatics($(this).attr("value"), menuArray[$(this).attr("menuname")]);//默认加载第一个功能菜单
		});
		$("#logout").click(function(){
			if (confirm("确定要退出系统吗")) {
				window.location = "${path}/logout.action";
			}
		});
		$('#headMenu li:first').trigger("click");//默认加载第一个功能菜单 
		//布局
		$("#layout1").ligerLayout({
			topHeight : 68,
			bottomHeight : 25,
			leftWidth : 190,
			space : 4,
			onHeightChanged : f_heightChanged
		});
		$("#framecenter").ligerTab({
			height : '100%'
		});
		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		//树
		tab = $("#framecenter").ligerGetTabManager();
		accordion = $("#accordion1").ligerGetAccordionManager();
		$("#pageloading").hide();
		
		changeTreeHeight();
	});
	function f_heightChanged(options) {
		$(".l-tab-content").height($("#framecenter").height() - $(".l-tab-links").height());
		$(".l-tab-content-item").height($(".l-tab-content").height());
		changeTreeHeight();
	}
	
	function changeTreeHeight(){
		$('#tree').height($('.l-layout-left').height());
	}
	
	window.onresize = f_heightChanged;
</script>

<style type="text/css">
body,html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

a {
	color: Black;
	text-decoration: none;
}

a:hover {
	color: Red;
	text-decoration: underline;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}

/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 31px;
	line-height: 31px;
	background: url('lib/images/top.jpg') repeat-x bottom;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	color: #E7E7E7;
	padding-left: 35px;
	line-height: 26px;
	background: url('lib/images/topicon.gif') no-repeat 10px 5px;
}

.l-topmenu-welcome {
	position: absolute;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 2px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: #E7E7E7;
	text-decoration: underline
}
</style>
</head>
<body style="padding: 0px;">
	<div id="pageloading"></div>
	<div id="layout1"
		style="width: 99.2%; margin: 0 auto; margin-top: 4px;">
		<div position="top"
			style="background: #102A49; color: White; overflow: hidden; line-height: 30px; font-family: Verdana, 微软雅黑, 黑体">
			<div class="header">
				<div class="logo"></div>
				<div class="main_nav">
					<ul id="headMenu">
						<c:forEach var="ls" items="${lst}">
							<li value="${ls.id}" uri="${ls.url}" menuname="${ls.menuName}">${ls.menuName}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="top_btn">
					<img src="${path}/images/pic01.png" width="11" height="10" /> 欢迎您
					<font color="#4AEDF7">${user.userName}</font> <a href="#" class="topBtn">修改密码</a>
					<a href="javascript:void(0);" class="topBtn" id="logout">安全退出</a>
				</div>
			</div>
		</div>
		<div position="left" title="主要菜单" id="accordion1">
			<ul id="tree" class="tree"
				style="height: 100%; width: 100%; overflow: auto;"></ul>
		</div>
		<div position="center" id="framecenter" style="background:url(${path}/images/main_bg.jpg) #FFF bottom right no-repeat;">
			<div tabid="home" title="我的主页" style="height: 100%;">
				<iframe frameborder="0" name="home" id="home" src="welcome.htm" allowtransparency="true"></iframe>
			</div>
		</div>
		<div position="bottom" class="footer">2012 版权所有</div>
	</div>
</body>
<script type="text/javascript">
	var globalCode;
	var zTree;//树
	var setting;//参数设置
	var zTreeNodes = [];//数据
	setting = {
		async : true,
		isSimpleData : true,
		treeNodeKey : "id",
		treeNodeParentKey : "pid",
		callback : {
			click : zTreeOnClick
		}

	};
	function zTreeOnClick(event, treeId, treeNode) {
		var tabName = treeNode.name;
		if (sysNum == 3) {
			if (treeNode.isParent) {
				return;
			} else {
				if (treeNode.uri) {
				} else {
					treeNode.uri = getPUri(treeNode);
					if (treeNode.uri.lastIndexOf('?') != -1) {
						treeNode.uri += "&";
					} else {
						treeNode.uri += "?";
					}
					treeNode.uri += "deptId=" + treeNode.id + "&deptName="
							+ treeNode.name;//encodeURI(treeNode.name)
				}
			}
			if(treeNode.tabname&&treeNode.tabname.length>=4){
				tabName = tabName + "("+treeNode.tabname.substring(0,2)+")";
			}
		}
		if (treeNode.uri != null && treeNode.uri.length > 0) {
			var tabid = $(treeNode).attr("tabid");
			if (!tabid) {
				tabid = new Date().getTime();
				$(treeNode).attr("tabid", tabid);
			}
			f_addTab(tabid, tabName, treeNode.uri);
		}
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
		$(".l-tab-content-item iframe").attr("allowtransparency",true);
	}

	var tmpUrl = "";
	function setAsyncUrl(treeNode) {
		if (treeNode == null) {
			tmpUrl = "cloudNodeMenu.action?id=" + globalCode+"&sysNum="+sysNum;
		} else {
			if (treeNode.level == 1) {
				tmpUrl = "childrenMenu.action?parentId=" + treeNode.id+"&sysNum="+sysNum;
			}
			if (treeNode.level == 2) {
				tmpUrl = "childrenMenu.action?parentId=" + treeNode.id+"&sysNum="+sysNum;
			}
		}
		return tmpUrl;
	}
	var sysNum;
	function changeStatics(code, num) {
		sysNum = num;
		switch (num) {
			case 1: {
				globalCode = code;
				setting.async = true;
				setting.asyncUrl = "centerControlMenu.action?id=" + code+"&sysNum="+sysNum;
				zTree = $("#tree").zTree(setting, zTreeNodes);
				break;
			}
			case 2: {
				globalCode = code;
				setting.asyncUrl = setAsyncUrl;
				zTree = $("#tree").zTree(setting, zTreeNodes);
				break;
			}
			case 3: {
				globalCode = code;
				setting.asyncUrl = getAsyncUrl;
				zTree = $("#tree").zTree(setting, zTreeNodes);
				break;
			}
			case 4: {
				globalCode = code;
				setting.async = true;
				setting.asyncUrl = "centerControlMenu.action?id=" + code +"&sysNum="+sysNum;
				zTree = $("#tree").zTree(setting, zTreeNodes);
				break;
			}
			case 5: {
				globalCode = code;
				setting.async = true;
				setting.asyncUrl = "centerControlMenu.action?id=" + code +"&sysNum="+sysNum;
				zTree = $("#tree").zTree(setting, zTreeNodes);
				break;
			}
		}
	}
	
	function getAsyncUrl(treeNode) {
		var url = "childrenMenu.action?parentId=" + globalCode+"&sysNum="+sysNum;
		if (treeNode && treeNode.data != 'undefined') {
			url = "${path}/sysmanager/dept/dept!getChildDeptById.action?data="
					+ treeNode.data;
		}
		return url;
	}

	function getPUri(treeNode) {
		var tempNode = treeNode;
		var uri = '';
		while (tempNode && !tempNode.uri) {
			tempNode = tempNode.parentNode;
		}
		treeNode.tabname = tempNode.name;
		uri = tempNode.uri;
		return uri;
	}

	function hideMenu() {
	}
</script>
</html>