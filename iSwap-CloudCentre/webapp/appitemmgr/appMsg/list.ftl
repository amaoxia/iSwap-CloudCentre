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
			<form action="${path}/appitemmgr/appMsg/appMsg!list.action" method="post" name="pageForm" id="pageForm">
			    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/app.png"  align="absmiddle" />
			      	应用注册管理
			      </div>
				  <div class="c_m_btn"> 
				  	<span class="cm_btn_m">
					  	<a href="javascript:void(0)" id="hz1" onclick="opdg('${path}/appitemmgr/appMsg/appMsg!addView.action','注册应用',500,390);">
						  	<b>
							  	<img src="${path}/images/cmb_xj.gif" class="cmb_img" />
							  	注册应用
							  	<a href="javascript:void(0)">
							  		<img src="${path}/images/bullet_add.png" class="bullet_add" />
							  	</a>
						  	</b>
					  	</a>
				  	</span>
				  	<span class="cm_btn_m">&nbsp;</span>
				  </div>
		    	</div>
			    <div class="main_c">
				    <table class="main_drop">
				        <tr>
				          <td align="right">应用名称：
				            <input name="conditions[e.appName,string,like]" value="${serchMap.e_appName?default("")}" type="text" onkeypress="showKeyPress()" onpaste="return false" />
				            
				            <input name="" type="button" value="查询" onclick="subForm('pageForm')" class="btn_s"/></td>
				        </tr>
				      </table>
				      <table class="tabs1" style="margin-top:0px;">
				        <tr>
				          <th width="20">&nbsp;</th>
				          <th width="28">序号</th>
				          <th width="25%">应用名称</th>
						  <!--<th>应用标识</th>-->
						  <th>状  态</th>
				          <th>创建时间</th>
				          <th width="220">操  作</th>
				        </tr>
				        <#list listDatas as entity>
						<tr <#if entity_index%2==0>class="trbg"</#if> onclick="selectedTD(this);" >
				          <td><input type="checkbox" name="ids" value="${entity.id}"/></td>
				          <td width="28">${entity_index+1}</td>
				          <td><a href="javascript:opdg('${path}/appitemmgr/appMsg/appMsg!view.action?id='+${entity.id},'查看应用',450,220);" >${entity.appName?default("")}</a></td>
				         <#--  <td>${entity.appCode?default("")}</td>-->
						  <td>
						  <#if entity.status==1>已启用</#if>
						  <#if entity.status==0><span style="color: red;">已禁用</span></#if>
						  </td>
				          <td>${entity.createDate?default("")}</td>
				          <td width="220">
				          	<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/appitemmgr/appMsg/appMsg!view.action?id='+${entity.id},'查看应用',450,220);" class="tabs1_cz">
				          		<img src="${path}/images/small9/s_chakan.gif" />查看
				         	 </a> 
				          	  <a id="hz2" href="javascript:opdg('${path}/appitemmgr/appMsg/appMsg!updateView.action?id='+${entity.id},'编辑应用',500,390)" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;
					          <a id="hz4" href="javascript:del('appMsg!delete.action?ids=${entity.id}');" class="tabs1_cz" >
						          <img src="${path}/images/small9/czimg_sc.gif" />
						          	删除
						      </a>&nbsp;
					          <a id="hz4" href="javascript:changeStatus('${entity.id}','${entity.status}');" class="tabs1_cz" >
						          <#if entity.status==1>
						          <img src="${path}/images/small9/dongjie.gif" />禁用</#if>
								  <#if entity.status==0>
								  <img src="${path}/images/small9/jihuo.gif" />启用</#if>
				          	  </a>
				          </td>
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
				      	<a href="javascript:delMany('appMsg!delete.action');" class="tfl_blink">
				      		<b class="hot">
				      			删除
				      		</b>
				      	</a>
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
					<!--页面上弹出窗口-->
				function saveTheForm(){
					document.forms['saveForm'].submit();
					
				}
				 function changeStatus(id,s){
				 	s=s=='1'?0:1;
				 	window.location.href="appMsg!changeStatus.action?id="+id+"&status="+s;
				 }
				function reloadPage(){
					window.location.href="appMsg!list.action";
				}
			</script>	
	</body>
</html>
