<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指标模板列表</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>	
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">

<!-- 增删改查 提交js操作-->
<script type="text/javascript">
	function updateStatus(type){
		  document.getElementById('pageForm').action="template!updateStatus.action?status="+type;
		  var state = "";
		  if('0'==type){state="确定冻结改模板？"}
		  if('1'==type){state="确定激活该模板？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}
	//下载文件
	function downLoad(id,status){
		if(status=='0'){
		alert("该模板已禁用,暂不能下载!");
		return;
		}else{
		window.location="${path}/exchange/template/template!downTemplate.action?id="+id;
		}
	}
	//上传文件
	function uploadFile(id,status){
		if(status=='0'){
			alert("该模板已禁用,暂不能上传!");
			return ;
		}else{
		 opdg('${path}/exchange/template/template!updateView.action?id='+id,'上传模板','570','300')
		}
	}
</script>
</head>
<body onclick="parent.hideMenu()">
<form name="pageForm" method="post"  action="${path}/exchange/template/template!list.action">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/template.png"  align="absmiddle" />指标项模板管理</div>
    </div>
    	<div class="main_c">
           <table class="main_drop">
        <tr>
          <td align="right">
		  指标项名称：		  <input name="conditions[e.changeItem.itemName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="<#if serchMap.e_changeItem_itemName?exists>${serchMap.e_changeItem_itemName?default('')}</#if>" 	type="text" />
		 <!-- 所属部门：     <input  name="deptName"  value="${deptName?default("")}" type="text" />-->
          							<input  type="submit" value="查询"  class="btn_s"/></td>
        </tr>
      </table>                              
      <table class="tabs1"  style="margin-top:0px;">
      <tr>
          <th width="20">&nbsp;</th>
          <th width="28">序号</th>
          <th>指标项名称</th>
          <th>模板名称</th>
          <th>模板编码</th>
          <#if center?exists><th>模版所属部门</th></#if> 
          <th>模版下载次数</th>
          <th>是否上传</th>
          <th>状态</th>
          <th>操作</th>
        </tr>
         <#list  listDatas as entity>
        	<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input name="ids" type="checkbox" value="${entity.id}" <#if !entity.uploadState?exists || entity.uploadState=='0'>disabled</#if>/></td>
          <td width="28">${entity_index+1}</td>
          <td>${entity.changeItem.itemName?default('')}</td>
          <td>${entity.templateName?default('-')}</td>
          <td>${entity.changeItem.itemCode?default('-')}</td>
           <#if center?exists><td>${entity.changeItem.sysDept.deptName?default('')}</td></#if>
          <td>${entity.downCount?default('0')}</td>
          <td><#if entity.uploadState?exists><#if entity.uploadState=='1'>已上传<#else>未上传</#if><#else>未上传</#if></td>
          <td><#if entity.state?exists><#if entity.state=='1'>启用<#else>禁用</#if><#else>无</#if></td>
           <td>
           <a href="javascript:void(0)" class="tabs1_cz" id="hz1"  <#if center?exists>onclick="uploadFile('${entity.id}','${entity.state?default('')}')" <#else>style="color:#ccc;"</#if>>
           <img src="${path}/images/small9/up.gif" /><#if entity.templatePath?exists>重新上传<#else>上传</#if></a>
           <a href="#" class="tabs1_cz"   <#if  entity.state?exists> onclick="downLoad('${entity.id}','${entity.state}')"<#else> style="color:#ccc;"</#if>><img src="${path}/images/small9/down.gif" />下载</a>
           <a  <#if  entity.state?exists> href="template!updateStatus.action?ids=${entity.id}&status=<#if entity.state=='1'>0<#else>1</#if><#else>style="color:#ccc;"</#if>" class="tabs1_cz"  >
           <img src="${path}/images/small9/jihuo.gif" /><#if  entity.state?exists><#if entity.state=='0'>启用<#else>禁用</#if><#else>启用</#if></a></td>
        </tr>
        </#list>
       </table>
      <div class="tabs_foot"> 
     		 <span class="tfl_btns">
			      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
			      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			      <#if center?exists>
      			  <a href="javascript:updateStatus('0')"  class="tfl_blink"><b >禁用</b></a>
      			  <a href="javascript:updateStatus('1')"  class="tfl_blink"><b>启用</b></a>
      			      </#if>
      </span>
       <span class="page pageR">
   		<@pager.pageTag/></span></div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>