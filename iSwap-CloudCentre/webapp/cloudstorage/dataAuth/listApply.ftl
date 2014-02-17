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
<form name="pageForm" method="post"  action="${path}/cloudstorage/dataAuth/dataAuth!listApply.action?appId=${appId?default('')}">
<div class="loayt_01 rlist100">
          <div class="loayt_mian" >
           <table class="main_drop">
       				 <tr>
		          			<td align="right">
				  				部门名称：	<input name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="${deptName?default("")}" type="text"  />
				  				指标名称：	<input name="conditions[e.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.targetName?default("")}" type="text"  />
						          <input name="" type="submit" value="查询"  class="btn_s"/></td>
						        </tr>
						      </table>                              
						      	<table class="tabs1"  style="margin-top:0px;">
						          <th width="5%">序号</th>
						          <th width="15%">指标名称</th>
						          <th width="15%">所属应用</th>
						          <th width="10%">所属部门</th>
								  <th width="10%">状态</th>
								  <th width="10%">创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="e.metaData.createDate">&nbsp;&nbsp;&nbsp;<img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
								  <th width="20%">操作</th>
						        </tr>
						       <#list listDatas as entity>
          							<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
						          <td>${entity_index+1}</td>
						           <td><#if entity.metaData?exists>${entity.metaData.targetName?default('无')}</#if></td>
						           <td><#if entity.metaData?exists>${entity.appMsg.appName?default('无')}</#if></td>
						          <td><#if entity.sysDept?exists>${entity.sysDept.deptName?default('无')}</#if></td>
								  <td><#if entity.dataApplyState?exists><#if entity.dataApplyState=='0'>未申请<#elseif entity.dataApplyState=='1'>申请<#else>二次申请</#if><#else>未申请</#if></td>
								   <td><#if entity.metaData.createDate?exists>${entity.metaData.createDate?date?default('无')}</#if></td>	
						          <td>
		      							<a href="javascript:void(0)" onclick="opdg('${path}/cloudstorage/dataAuth/dataAuth!addView.action?id=${entity.id}&itemId=${entity.metaData.id}&appId=${entity.appMsg.id}','数据使用申请','950','580')"  id="hz0" class="tabs1_cz">
		      						   <img src="${path}/images/small9/s_chakan.gif" /><#if entity.dataApplyState?exists><#if entity.dataApplyState=='0'>申请<#else>修改申请 </#if><#else>申请</#if></a> 	
						           </td>
						        </tr>
						        </#list>
						      </table>
						      <div class="tabs_foot"  style="width:100%"> 
						      <span class="tfl_btns" style="float:left;">
             					</span> 
       							<span class="page pageR" style="margin-top:0px;">
						       <@pager.pageTag/>
						      </span>
						       </div>
						    </div>
					</form>
					<#include "/common/commonList.ftl">
					<#include "/common/commonLhg.ftl">
			</body>
</html>