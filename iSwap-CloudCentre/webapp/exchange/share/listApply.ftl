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
						   <div class="loayt_01 rlist100">
				           <div class="loayt_mian" >
				           <form name="pageForm" method="post"  action="${path}/exchange/share/share!listApply.action">
				           <table class="main_drop">
			       				 <tr>
						          <td align="right">
					  				部门名称：	<input name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="${deptName?default('')}" type="text"  />
					  				指标名称：	<input name="conditions[e.targetName,string,like]" value="${serchMap.targetName?default("")}" onkeypress="showKeyPress()" onpaste="return false" type="text"  />
						          			<input name="" type="submit" value="查询"  class="btn_s"/>
						          </td>
						        </tr>
						      </table>
							      <table class="tabs1">
							        <tr>
							        <th width="5%">序号</th>
						           <th width="10%">指标名称</th>
						           <th width="10%">所属部门</th>
						          <th width="15%">所属应用</th>
						          <th width="10%">创建时间 <a href="javascript:void(0);" onclick="setOrder(this)" name="e.metaData.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
								  <th width="20%">操作</th>
							        </tr>
							        <tr class="trbg">
        						   <#list dataApp as entity>
          						  <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
						          <td>${entity_index+1}</td>
						          <td>${entity.metaData.targetName?default('无')}</td>
						          <td><#if entity.metaData.sysDept?exists>${entity.metaData.sysDept.deptName?default('无')}</#if></td>
						          <td><#if entity.appMsg?exists>${entity.appMsg.appName?default('')}</#if></td>
								  <td><#if entity.metaData.createDate?exists>${entity.metaData.createDate?date?default('无')}</#if></td>
						          <td>
						          <a href="#"  id="hz0" class="tabs1_cz" onclick="opdg('${path}/cloudstorage/apply/apply!addView.action?itemId=${entity.metaData.id?default('')}&dataAppId=${entity.id?default('')}&appId=${entity.appMsg.id?default('')}','申请','950','620');">
		      						   <img src="${path}/images/small9/s_chakan.gif" />申请</a> 	
						              <a href="#" onclick="opdg('${path}/cloudstorage/tableinfo/tableinfo!add.action?itemId=${entity.id}','表结构','1000','470');" class="tabs1_cz" >
						           <img src="${path}/images/small9/s_biaojiegou.gif"/>表结构</a>
						           </td>
						        </tr>
						        </#list>
							        </tr>
							      </table>
	  		  						 <div class="tabs_foot"  style="width:100%"> 
						      	<span class="tfl_btns" style="float:left;">
             					</span> 
       							<span class="page pageR" style="margin-top:0px;">
						       <@pager.pageTag/>
						      </span>
						      </form>
						       </div>
</div>
<div class=" clear"></div>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
</body>
</html>