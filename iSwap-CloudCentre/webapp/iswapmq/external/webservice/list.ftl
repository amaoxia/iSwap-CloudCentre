<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<#include "/common/taglibs.ftl">
	<#import "pager.ftl" as pager>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
	</head>
	<body>
		<form name="pageForm" action="webInfoAction!list.action" method="post">
		 	<input type="hidden" name="deptId" value="${deptId?default('')}"/>
		    <div class="common_menu">
		      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />WebServices数据源接与入定义</div>
		      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/iswapmq/external/webservice/webInfoAction!addView.action?deptId=${deptId?default('')}','新增webservice数据源','520','400')"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建WS数据源<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
		    </div>
		    <div class="main_c">
			      <table class="main_drop">
			        <tr>
			          <td align="right">
					  服务名称：<input name="conditions[e.wsName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_wsName?default("")}"  type="text" />
			            <input name="" type="submit" value="查询"  class="btn_s" onClick="javascript:subForm('pageForm')"/></td>
			        </tr>
			      </table>    
			      <table class="tabs1"  style="margin-top:0px;">
			        <tr onclick="selectedTD(this);">
			          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="28">序号</th>
			          <th>服务名称</th>
			          <th>ip地址</th>
			          <th>状  态</th>
					  <th>创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th>操作</th>
			        </tr>
			          <#assign i = 0>  
				      <#list listDatas as entity>
				      <#assign i = i+1>
				      <#if i%2=0>
					  <tr  onclick="selectedTD(this);">
					  <#else> 
					  <tr class="trbg" onclick="selectedTD(this);">  
					  </#if> 
				          <td class="tright"><input name="ids" value="${entity.id}" type="checkbox"/></td>
				          <td>${entity_index+1}</td>				
				          <td><a href="javascript:opdg('${path}/iswapmq/external/webservice/webInfoAction!view.action?id=${entity.id}','详细信息','360','255')"  id="hz1">${entity.wsName?default("")}</td>
						  <td>${entity.ipAddress?default("")}</td>
						   <td><#if entity.status=='0'><font class="font_red">禁用</font></#if><#if entity.status=='1'>启用</#if></td>
						  <td>${entity.dateCreate?default("")}</td>
				          <td>
						      <a href="javascript:void(0)" onclick="opdg('${path}/iswapmq/external/webservice/webInfoAction!updateView.action?deptId=${deptId?default('')}&id=${entity.id}','编辑服务','520','380')" class="tabs1_cz" id="hz1"><img src="${path}/images/czimg_edit.gif" />编辑</a>
						 	  <a href="javascript:void(0)"  onclick="del('${path}/iswapmq/external/webservice/webInfoAction!delete.action?ids=${entity.id}');" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />删除</a>
						  	  <#if entity.status=='0'><a href="javascript:void(0);"  onclick="updateStatus('${path}/iswapmq/external/webservice/webInfoAction!updateStatus.action?status=1&ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/jihuo.gif" />启动</a></#if><#if entity.status=='1'><a href="javascript:void(0);" class="tabs1_cz"  onclick="updateStatus('${path}/iswapmq/external/webservice/webInfoAction!updateStatus.action?status=0&ids=${entity.id}');"><img src="${path}/images/small9/dongjie.gif" />禁用</a></#if></td>
						  </td>
			         </tr>
			         </#list>
			      </table>
		     	 <div class="tabs_foot"> 
			      <span class="tfl_btns">
				      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
				      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
				      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
				      <a href="javascript:delMany('webInfoAction!delete.action')" class="tfl_blink">
				      	<b class="hot">删除</b>
				      </a>
				      <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('${path}/iswapmq/external/webservice/webInfoAction!updateStatus.action?status=1');"><b class="">启用</b></a>
			      	  <a href="javascript:void(0)" class="tfl_blink"  onclick="updateStatus('${path}/iswapmq/external/webservice/webInfoAction!updateStatus.action?status=0');"><b class="">禁用</b></a></span> 
			      </span> 
			      <span class="page pageR">
			      	<@pager.pageTag/>
			      </span> 
		      </div>
		    </div>
		    <div class="clear"></div>
		 </form>
		 <#include "/common/commonLhg.ftl">
		 <#include "/common/commonList.ftl">  
	</body>
</html>
<script>  
		//修改状态
		function updateStatus(url){
			document.forms['pageForm'].action = url;
			document.forms['pageForm'].submit();
		}
		</script>
