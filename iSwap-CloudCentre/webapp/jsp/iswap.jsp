<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="core" prefix="c"%>
<%@ taglib uri="fmt" prefix="f"%>
<%@ taglib uri="pager" prefix="p"%>
<%@ taglib uri="fn" prefix="fn"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>iSwap数据交换共享服务平台</title>
		<link
			href="${path}/menuTree/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
			rel="stylesheet" type="text/css" />
		<script src="${path}/menuTree/lib/jquery/jquery-1.3.2.min.js"
			type="text/javascript"></script>
		<script src="${path}/menuTree/lib/ligerUI/js/ligerui.min.js"
			type="text/javascript"></script>
		<script src="${path}/menuTree/lib/ligerUI/js/plugins/ligerTab.js"
			type="text/javascript"></script>
		<script src="${path}/menuTree/lib/ligerUI/js/plugins/ligerMenu.js"
			type="text/javascript"></script>

		<script type="text/javascript">
	var tab = null;
	var accordion = null;
	var tree = null;
	var menu;
	var actionNodeID;
	function itemclick(item, i) {
		alert(actionNodeID + " | " + item.text);
	}

	$(function() {
		//布局
		$("#layout1").ligerLayout({
			leftWidth : 150,
			height : '100%',
			onHeightChanged : f_heightChanged
		});

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height : height
		});

		//面板
		$("#accordion1").ligerAccordion({
			height : height - 24,
			speed : null
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		menu = $.ligerMenu({
			top : 100,
			left : 100,
			width : 120,
			items : [ {
				text : '修改',
				click : itemclick
			} ]
		});
		//树
		$("#tree0").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url || node.data.url == "#")
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			},
			onContextmenu : function(node, e) {
				actionNodeID = node.data.text;
				menu.show({
					top : e.pageY,
					left : e.pageX
				});
				return false;
			}
		});
		$("#tree1").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url || node.data.url == "#")
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree2").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree3").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree4").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree5").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree6").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree7").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree8").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree9").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree10").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree11").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		$("#tree12").ligerTree({
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid);
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});
		tab = $("#framecenter").ligerGetTabManager();
		accordion = $("#accordion1").ligerGetAccordionManager();
		tree = $("#tree0").ligerGetTreeManager();
		$("#pageloading").hide();

	});
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url
		});
	}
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

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: none;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
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
	padding-left: 20px;
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
</style>
	</head>
	<body style="padding: 0px;">
		<div id="pageloading"></div>
		<div id="layout1" style="width: 100%">
			<div position="top" style="background: #102A49; color: White;">
				<div style="margin-top: 15px; margin-left: 800px">
					<span>iSwap数据交换共享服务平台</span>
				</div>
			</div>
			<div position="left" title="功能导航" id="accordion1">
				<c:forEach var="menu" items="${lst}" varStatus="i">
					<c:set value="0" var="falg"></c:set>
					<%@include file="checkChildrenMenu.jsp"%>
					<c:if test="${falg eq 0 }">
						<div title="${menu.menuName}">
							<div style="height: 7px;"></div>
							<c:forEach var="m" items="${menu.childrenPermission}">
								<a class="l-link"
									href="javascript:f_addTab('listpage${m.id}','${m.menuName}','${m.url}')">${m.menuName}</a>
							</c:forEach>
						</div>
					</c:if>
					<c:if test="${falg eq 1 }">
						<div title="${menu.menuName}" class="l-scroll">
							<ul id="tree${i.index}" style="margin-top: 3px;">
								<%@include file="recursive.jsp"%>
							</ul>
						</div>
					</c:if>
				</c:forEach>
				<%--				<div title="中心控制管理" class="l-scroll">--%>
				<%--					<ul id="tree1" style="margin-top: 3px;">--%>
				<%--						<li isexpand="false">--%>
				<%--							<span>中心流程管理</span>--%>
				<%--							<ul>--%>
				<%--								<li--%>
				<%--									url="http://192.168.100.15:5678/iswap/iswapesb/workflow/esbworkflowAction!list.action">--%>
				<%--									<span>流程定制</span>--%>
				<%--								</li>--%>
				<%--								<li--%>
				<%--									url="http://192.168.100.15:5678/iswap/iswapesb/esbtask/esbTaskAction!list.action">--%>
				<%--									<span>任务定制</span>--%>
				<%--								</li>--%>
				<%--							</ul>--%>
				<%--						</li>--%>
				<%----%>
				<%--						<li isexpand="false">--%>
				<%--							<span>应用配置管理</span>--%>
				<%--							<ul>--%>
				<%--								<li--%>
				<%--									url="http://192.168.100.15:5678/iswap/cloudcenter/appMsg/appMsg!list.action">--%>
				<%--									<span>应用注册</span>--%>
				<%--								</li>--%>
				<%--								<li--%>
				<%--									url="http://192.168.100.15:5678/iswap/cloudcenter/cloudNodeMake/cloudNodeMake!list.action">--%>
				<%--									<span>前置机定制</span>--%>
				<%--								</li>--%>
				<%--							</ul>--%>
				<%--						</li>--%>
				<%--					</ul>--%>
				<%--				</div>--%>
				<%--				<div title="前置机管理">--%>
				<%--					<div style="height: 7px;"></div>--%>
				<%--					<a class="l-link"--%>
				<%--						href="javascript:f_addTab('listpage1','桥接管理','http://192.168.100.15:5678/iswap/cloudnode/datasource/datasource!list.action')">桥接管理</a>--%>
				<%--					<a class="l-link"--%>
				<%--						href="javascript:f_addTab('listpage2','流程管理','http://192.168.100.15:5678/iswap/cloudnode/workflow/workflow!list.action')">流程管理</a>--%>
				<%--				</div>--%>
				<%--				<div title="数据传输管理">--%>
				<%--					<div style="height: 7px;"></div>--%>
				<%--					<a class="l-link"--%>
				<%--						href="javascript:f_addTab('listpage3','webservice接入','http://192.168.100.15:5678/iswap/iswapmq/external/webservice/webInfoAction!list.action')">webservice接入</a>--%>
				<%--					<a class="l-link"--%>
				<%--						href="javascript:f_addTab('listpage4','消息接入','http://192.168.100.15:5678/iswap/iswapmq/external/jms/jmsInfoAction!list.action')">消息接入</a>--%>
				<%--				</div>--%>
			</div>
			<div
				<div position="center" id="framecenter">
				<div tabid="home" title="我的主页" style="height: 300px">
					<iframe frameborder="0" name="home" id="home" src="tree.jsp"></iframe>
				</div></div>
			<div position="bottom" style="padding-top: 15px;">
				技术支持
			</div>
		</div>
		<div style="display: none">
			<!-- 流量统计代码 -->
		</div>
	</body>
</html>

