<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/taglibs.ftl">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>本地目录监听</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
		    <div class="common_menu">
		      <div class="c_m_title"><img src="${path}/images/big/LocalListen.png"  align="absmiddle" />本地目录监听</div>
			  <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" onclick="opdg('${path}/cloudnode/localhostlisten/localhostlisten!addView.action?deptId=${deptId?default('')}','注册本地目录监听','920','580');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />注册本地目录监听<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
		    </div>
		    <div class="main_c">
			    <form  action="${path}/cloudnode/localhostlisten/localhostlisten!list.action" method="post" id="pageForm" name="pageForm">
				    <input type="hidden" name="deptId" value="${deptId?default('')}"/>
				    <table class="main_drop">
				        <tr>
				          <td align="right">监听名称：
				            <input name="listenName" onkeypress="showKeyPress()" type="text" value="${listenName?default("")}"/>  <!-- onpaste="return false"-->所属应用：<select name="appMsgId"><option value="">---请选择---</option><#list appMsgList as app><option value="${app.id}"  <#if appMsgId?exists><#if app.id.toString()==appMsgId>selected</#if></#if> >${app.appName?default('')}</option></#list></select>
				            <input name="" type="button" value="查询"  class="btn_s" onclick="selectFtp();"/></td>
				        </tr>
				      </table>
				      <table class="tabs1" style="margin-top:0px;">
				        <tr>
				          <th width="20">&nbsp;</th>
				          <th width="28">序号</th>
				          <th>监听名称</th>
						  <th>工作流程</th>
						  <th>所属应用</th>
				          <th>状  态</th>
				          <th>创建时间</th>
				          <th width="210">操  作</th>
				        </tr>
				        <#list listDatas as entity>
				        <tr <#if entity_index%2==0>class="trbg"</#if>  onclick="selectedTD(this);">
				          <td><input name="ids" type="checkbox" <#if entity.status?exists&&entity.status=='1'>disabled</#if> value="${entity.id}" /></td>
				          <td width="28">${entity_index+1}</td>
				          <td><a href="javascript:void(0)" onclick="opdg('${path}/cloudnode/localhostlisten/localhostlisten!view.action?id=${entity.id}','本地目录监听信息查看','600','390');">${entity.listenName}</a></td>
				          <td><#if entity.workFlow?exists>${entity.workFlow.workFlowName?default("")}</#if></td>
						  <td><#if entity.appMsg?exists>${entity.appMsg.appName?default("")}</#if></td>
				          <td><#if entity.status=='0'><font class="font_red">禁用</font></#if><#if entity.status=='1'>启用</#if></td>
				          <td>${entity.createDate}</td>
				          <td><a href="javascript:void(0)" class="tabs1_cz" <#if entity.status=='1'> style="color:#ccc;"<#else>onclick="opdg('${path}/cloudnode/localhostlisten/localhostlisten!updateView.action?deptId=${deptId?default('')}&id='+${entity.id},'编辑本地目录监听','920','580');" </#if> ><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;<a href="javascript:void(0)" onclick="opdg('${path}/cloudnode/localhostlisten/localhostlisten!view.action?id=${entity.id}','本地目录监听信息查看','600','390');" class="tabs1_cz"><img src="${path}/images/small9/s_chakan.gif"/>查看</a>&nbsp;<#if entity.status=='0'><a href="javascript:void(0);"  onclick="updateStatus('${path}/cloudnode/localhostlisten/localhostlisten!updateStatus.action?status=1&deptId=${deptId?default('')}&ids=${entity.id}');"  class="tabs1_cz" ><img src="${path}/images/small9/jihuo.gif" />启动</a></#if><#if entity.status=='1'><a href="javascript:void(0);"  onclick="updateStatus('${path}/cloudnode/localhostlisten/localhostlisten!updateStatus.action?status=0&deptId=${deptId?default('')}&ids=${entity.id}');"  class="tabs1_cz" ><img src="${path}/images/small9/dongjie.gif" />禁用</a></#if></td>
				        </tr>
						</#list>
				      </table>
				      <div class="tabs_foot"> 
					      <span class="tfl_btns">
						      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
						      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">
						      	全选
						      </a>
						      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
						      <a href="javascript:void(0)" onclick="delMany('${path}/cloudnode/localhostlisten/localhostlisten!delMany.action');" class="tfl_blink">
							      <b class="hot">
							     	 删除
							      </b>
						      </a>
						      <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('${path}/cloudnode/localhostlisten/localhostlisten!updateStatus.action?status=1');">
							      <b class="">
							      	启用
							      </b>
						      </a>
						      <a href="javascript:void(0)" onclick="updateStatus('${path}/cloudnode/localhostlisten/localhostlisten!updateStatus.action?status=0&deptId=${deptId?default('')}');" class="tfl_blink">
						      	<b class="">
						      		禁用
						      	</b>
						      </a>
						    </span> 
					      	<span class="page pageR"><@pager.pageTag/></span> 
				      </div>
				    </div>
			    </form>
			</div>
		  	<div class="clear"></div>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
		<script>  
		function selectFtp(){
		document.pageForm.submit();
		}
		//删除选中
		function delMany(url) {
			if($("input[name='ids']:checked ").length==0){
				alert("请选择记录！");
				return;
			}
			if (confirm("确定要删除选中记录吗？")) {
				document.forms['pageForm'].action = url;
				document.forms['pageForm'].submit();
			}
		}
		//修改状态
		function updateStatus(url){
			document.forms['pageForm'].action = url;
			document.forms['pageForm'].submit();
		}
		</script>
	</body>
</html>
