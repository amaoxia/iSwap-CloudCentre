<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
		<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>	
	</head>
	<body>
		<form action="cloudNodeInfo!list.action" method="post" name="pageForm" id="pageForm">
			<div class="frameset_w" style="height:100%">
			    <div class="common_menu">
			      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />前置机管理</div>
				  <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz1" onclick="opdg('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!addView.action','注册前置机',600,585);"><b>
				  <img src="${path}/images/cmb_xj.gif" class="cmb_img" />注册前置机<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
			    </div>
		    <div class="main_c">
			    <table class="main_drop">
			        <tr>
			          <td align="right">
			          <#-- 
			          	所属应用：
			            <select name="appId">
			            <option value="">--全部--</option>
			            <#list appMsgs as entity>  
			                <option value="${entity.id}" <#if appId?exists><#if appId==entity.id>selected</#if></#if>>${entity.appName?default('')}</option>
			            </#list>
			            </select>
			            -->
			          	前置机名称：
			            <input name="nodesName" value="${nodesName?default("")}" onkeypress="showKeyPress()" type="text" /><!--onpaste="return false"禁止粘贴-->
			            <input name="" type="button" value="查询" onclick="subForm('pageForm')" class="btn_s"/></td>
			        </tr>
			     </table>
			     <table class="tabs1" style="margin-top:0px;">
			        <tr>
			          <th width="20">&nbsp;</th>
			          <th width="28">序号</th>
			          <th width="25%">前置机名称</th>
					  <th>前置机IP</th>
					  <th>所属部门</th>
					  <#-- <th>所属应用</th> -->
					  <th>运行状态</th>
			          <th>部署状态</th>
			          <th>操  作</th>
			        </tr>
			        <#list listDatas as entity>
					<tr <#if entity_index%2==0>class="trbg"</#if> onclick="selectedTD(this);" >
			          <td><input type="checkbox" name="ids" value="${entity.id}"/></td>
			          <td width="28">${entity_index+1}</td>
			          <td><a href="javascript:opdg('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!view.action?id=${entity.id}','查看前置机',500,350);" title="${entity.nodesName?default('')}">${subStr(entity.nodesName?default(""),20)}</a></td>
			          <td>${entity.address?default("")}</td>
			          <td><div title="${entity.sysDeptNames?default('')}">${subStr(entity.sysDeptNames?default(''),15)}</div></td>
			          <#-- <td><#if entity.appMsgNames?exists><#if entity.appMsgNames=='null'>(空)<#else>${entity.appMsgNames?default('')}</#if><#else>(空)</#if></td>-->
					  <td>
						  <#if entity.status==1>正常</#if>
						  <#if entity.status==0><span style="color: red;">无连接</span></#if>
					  </td>
					  <td>
						  <#if entity.status==1>已部署</#if>
						  <#if entity.status==0><span style="color: red;">未部署</span></#if>
					  </td>
			          <td>
				          <a id="hz2" <#if entity.status==1>style="color:#ccc;"<#else>href="javascript:opdg('${path}/cloudcenter/cloudNodeInfo/cloudNodeInfo!updateView.action?id=${entity.id}','编辑前置机',600,580)"</#if> class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;
				          <a id="hz4" <#if entity.status==1>style="color:#ccc;"<#else>href="javascript:del('cloudNodeInfo!delete.action?ids=${entity.id}');"</#if> class="tabs1_cz" ><img src="${path}/images/small9/czimg_sc.gif" />删除</a>&nbsp;
				          <#if entity.status==1><a id="hz4" href="javascript:undeploy('${entity.id}');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />取消部署</a>&nbsp;</#if>
						  <#if entity.status==0><a id="hz4" href="javascript:deploy('${entity.id}');" class="tabs1_cz" ><img src="../../images/small9/s_quxiaobushu.gif" />部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署</a>&nbsp;</#if>
				          <a id="hz4" <#if entity.status==1>href="javascript:testConn('${entity.address?default("")}','${entity.port?default("")}');"<#else> style="color:#ccc;"</#if> class="tabs1_cz" ><img src="../../images/small9/s_ceshi.gif" />环境测试</a>
			          </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
			  		<span class="tfl_btns">
			  			<img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" />
			  			<a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			  			<a href="javascript:delMany('cloudNodeInfo!delete.action');" class="tfl_blink"><b class="hot">删除</b></a>
			  		</span> 
			      <span class="page pageR">
			      	<@pager.pageTag/>
			      <span>
			    </div>
		    	<div class="clear"></div>
			</div>
		<form>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
		<script type="text/javascript">
			function saveTheForm(){
				document.forms['saveForm'].submit();
			}
			
			function changeStatus(id,s){
			 	s=s=='1'?0:1;
			 	window.location.href="cloudNodeInfo!changeStatus.action?id="+id+"&status="+s;
			 }
			 
			function reloadPage(){
				window.location.href="cloudNodeInfo!list.action";
			}
			
			function deploy(id){
				if(confirm("确定要部署吗？")){
					window.location.href="cloudNodeInfo!deploy.action?id="+id;
				}
			}
			
			function undeploy(id){
				if(confirm("确定要取消部署吗？")){
					window.location.href="cloudNodeInfo!unDeploy.action?id="+id;
				}
			}
			
			function testConn(ip,port){
				jQuery.ajax({
				    url: 'cloudNodeInfo!connTest.action?ip='+ip+'&port='+port,
				    type: 'post',
				    dataType: 'text',
				    timeout: 10000,
				    error:function(data){
				    	alert("连接失败!");
				    },
				    success: function(data){
				        alert(data);
				    }
				});
			}	
		</script>
	</body>
</html>
