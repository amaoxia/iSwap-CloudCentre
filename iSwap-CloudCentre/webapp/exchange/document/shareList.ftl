<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><span class="c_m_t_img01"></span>文档数据共享发布</div>
    </div>
    <div class="main_c">
      <table class="main_drop">
        <tr>
          <td align="right">
          <form id="pageForm" name="pageForm" action="${path}/exchange/document/document!shareList.action" method="post">
         	 指标名称：<input name="conditions[e.itemName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_itemName?default("")}"/>
	      	所属部门：<input type="text" name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="${deptName?default('')}"/>
	      	状态：<select name="status" ><option value="" <#if !status?exists||status==''>selected</#if>>---请选择状态---</option><option value="1" <#if status?exists><#if status=='1'></#if>selected</#if>>已发布</option><option value="0" <#if status?exists><#if status=='0'>selected</#if></#if>>未发布</option></select> &nbsp;&nbsp;
	        <input name="" type="submit"  value="查询"  class="btn_s"/>	
          </td>
        </tr>
      </table>
      <table class="tabs1">
        <tr onclick="selectedTD(this);">
          <th width="20" class="tright"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28" class="tright">序号</th>
          <th>指标名称</th>
          <th>所属部门</th>
          <th>创建时间</th>
          <th>状态&nbsp;<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>操作</th>
        </tr>
          <#list listDatas as entity>
          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
          <td width="28">${entity_index+1}</td>
          <td>${entity.itemName?default('')}</td>
      	  <td><#if center?exists>${entity.sysDept.deptName?default('')}</#if></td>
      	  <td>${entity.createDate?default('')}</td>
          <td><#if entity.shareState?exists><#if entity.shareState=='0'>未发布<#else>已发布</#if><#else>未发布</#if></td>
           <td><a href="javascript:void(0)" onclick="push('<#if entity.shareState?exists>${entity.shareState}</#if>','${entity.id}')" class="tabs1_cz"><img src="${path}/images/small9/s_bushu.gif"/>发布</a></td>
          </tr>
        </#list>
      </table>
				<div class="tabs_foot"> 
      			<span class="tfl_btns">
			      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
			      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
			      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('1')"><b >发布</b></a>
      			</span>
      			 <span class="page pageR">
   			<@pager.pageTag/></span>
   		</div>
    </div>
    </form>
    <#include "/common/commonList.ftl">
	<#include "/common/commonLhg.ftl">
	<script type="text/javascript">    
	function updateStatus(type){
		  document.getElementById('pageForm').action="document!updateStatus.action?status="+type;
		  var state = "";
		  if('0'==type){state="确定要取消发布文档指标吗？"}
		  if('1'==type){state="确定要发布文档指标吗？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}
		function push(state,id){
		if(state=="1"){
			alert("当前指标已发布!");
			return;
		}
		 	if(confirm("确定要发布吗")) {
		 		 window.location="document!updateStatus.action?status=1&ids="+id;
			  }
		}
</script>
</body>
</html>

