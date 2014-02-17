<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>元数据管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
<form name="pageForm" method="post"  action="${path}/cloudstorage/metadata/metadata!listMeta.action?type=${type?default('')}">
<div class="loayt_01 rlist100">
          <div class="loayt_mian" >
				<table class="main_drop" style="margin:6px auto 0px auto;">
       				 <tr>
		          			<td align="right">
		          				指标名称：	<input name="conditions[e.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.etargetName?default("")}" type="text"  />
				  				部门名称：	<input name="deptName" value="${deptName?default('')}" onkeypress="showKeyPress()" onpaste="return false" type="text"  />
						          <input name="" type="submit" value="查询"  class="btn_s"/></td>
						        </tr>
						      </table>                              
						      	<table class="tabs1">
						          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
						          <th width="5%">序号</th>
						          <th width="20%">指标名称</th>
						          <th width="20%">所属部门</th>
								  <th width="13%">创建时间<a href="javascript:void(0)">&nbsp;&nbsp;&nbsp;<img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
								    <th width="10%">状态</th>
								  <th width="30%">操作</th>
						        </tr>
						       <#list listDatas as entity>
          							<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          						  <td width="2%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
						          <td width="5%">${entity_index+1}</td>
						           <td width="20%">${entity.targetName?default('无')}</td>
						          <td width="20%"><#if entity.sysDept?exists>${entity.sysDept.deptName?default('无')}</#if></td>
								  <td width="13%"><#if entity.createDate?exists>${entity.createDate?date?default('无')}</#if></td>
								  <td width="10%"><#if entity.shareState?exists><#if entity.shareState=='0'>未发布<#else>发布</#if><#else>未发布</#if></td>
						          <td width="30%">
		      							<a href="metadata!updateStatus.action?ids=${entity.id}&type=${type?default('')}&status=<#if entity.shareState?exists><#if entity.shareState=='0'>1<#else>0 </#if><#else>1</#if>" id="hz0" class="tabs1_cz">
		      						   <img src="${path}/images/small9/s_chakan.gif" /><#if entity.shareState?exists><#if entity.shareState=='0'>发布<#else>取消发布 </#if><#else>发布</#if></a> 	
						           </td>
						        </tr>
						        </#list>
						      </table>
						      <div class="tabs_foot"  style="width:100%"> 
						      <span class="tfl_btns" style="float:left;">
						       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
						       <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
			    			   <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('1')"><b>发布</b></a>
			    			   <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('0')"><b>取消发布</b></a>
             					<img src="${path}/images/tabsf_line.jpg" align="absmiddle" />
             					</span> 
       							<span class="page pageR" style="margin-top:0px;">
						       <@pager.pageTag/>
						      </span>
						       </div>
						    </div>
					</form>
					<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script type="text/javascript">    
	//修改发布状态
	function updateStatus(status){
		  document.getElementById('pageForm').action="metadata!updateStatus.action?type=${type?default('')}&status="+status;
		  var state = "";
		  if('0'==status){state="确定取消发布指标吗？"}
		  if('1'==status){state="确定发布指标吗？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}
	</script>
			</body>
</html>