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
<form name="pageForm" method="post"  action="${path}/exchange/share/share!listApp.action" id="pageForm">
<div class="loayt_01 rlist100">
          <div class="loayt_mian" >
				<table class="main_drop" style="margin:6px auto 0px auto;">
       				 <tr>
		          			<td align="right">
				  				指标名称：	<input name="conditions[e.metaData.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="" type="text"  />
				  				所属应用：	<input name="conditions[e.appMsg.appName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="" type="text"  />
						          <input name="" type="submit" value="查询"  class="btn_s"/></td>
						        </tr>
						      </table>                              
						      	<table class="tabs1"  style="margin-top:0px;">
						          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
						          <th width="5%">序号</th>
						           <th width="10%">指标名称</th>
						          <th width="15%">所属应用</th>
						          <th width="10%">创建时间 <a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
								  <th width="10%">状态</th>
								  <th width="20%">操作</th>
						        </tr>
						       <#list dataApp as entity>
          							<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          						  <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
						          <td>${entity_index+1}</td>
						           <td>${entity.metaData.targetName?default('无')}</td>
						          <td><#if entity.appMsg?exists>${entity.appMsg.appName?default('无')}</#if></td>
						          <td>${entity.metaData.createDate?default('无')}</td>
								  <td><#if entity.shareState?exists><#if entity.shareState=='1'>发布<#else>未发布</#if><#else>未发布</#if></td>
						          <td>
						           <#if entity.shareState?exists>
	      						   <#if entity.shareState=='0'>
		      							<a href="share!updateStatus.action?ids=${entity.id}&status=1&appId=${entity.appMsg.id}"  class="tabs1_cz">
		      						   <img src="${path}/images/small9/s_chakan.gif" />发布</a> 	
	      						   <#else>
										<a href="share!updateStatus.action?ids=${entity.id}&status=0&appId=${entity.appMsg.id}" id="hz0"  class="tabs1_cz">
		      						   <img src="${path}/images/small9/s_chakan.gif" />取消发布</a> 		      						 	  
										</#if>
	      						   <#else>
	      						  <a href="share!updateStatus.action?ids=${entity.id}&status=1&appId=${entity.appMsg.id}" id="hz0" class="tabs1_cz">
		      						   <img src="${path}/images/small9/s_chakan.gif" />发布</a> 	
	      						   </#if>
						           </td>
						        </tr>
						        </#list>
						      </table>
						      <div class="tabs_foot" style="width:100%"> 
						      <span class="tfl_btns" style="float:left;">
						   <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
						       <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
						       <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			    			     <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('1')"><b>发布</b></a>
			    			    <a href="javascript:void(0)" class="tfl_blink" onclick="updateStatus('0')"><b>取消发布</b></a>
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
						//修改指标状态
						function updateStatus(status){
							  document.getElementById('pageForm').action="share!updateStatus.action?status="+status+"&appId=${appId?default('')}";
							  var state = "";
							  if('0'==status){state="确定取消发布该指标吗？"}
							  if('1'==status){state="确定发布该指标吗？"}
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