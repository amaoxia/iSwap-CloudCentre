<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>接收指标项</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body onclick="parent.hideMenu();">
		<form name="pageForm" method="post"  action="${path}/exchange/shareItem/shareItem!list.action">
			<input type="hidden" name="deptId" id="deptId" value="${deptId}"/>
			<input type="hidden" name="deptName" id="deptName" value="${deptName}"/>
			    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/item.png"  align="absmiddle" />
			      		共享指标项
			      </div>
			    </div>
		    	<div class="main_c">
		           <table class="main_drop">
   		 			  <tr>
	          			<td align="right">
	          				应用名称：	<input name="conditions[e.appItemExchangeConf.appMsg.appName,string,like]"  onpaste="return false" value="${serchMap.e_appItemExchangeConf_appMsg_appName?default("")}" type="text"  />
			  				指标项名称：	<input name="conditions[e.appItemExchangeConf.appItem.appItemName,string,like]"  onpaste="return false" value="${serchMap.e_appItemExchangeConf_appItem_appItemName?default("")}" type="text"  />
					          <input name="" type="submit" value="查询"  class="btn_s"/>
					     </td>
					  </tr>
					</table>                              
			      	<table class="tabs1"  style="margin-top:0px;">
			          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="5%">序号</th>
			          <th width="5%">应用</th>
			          <th width="15%">指标名称</th>
			          <th width="10%">数据提供部门</th>
			          <th width="15%">数据接收部门</th>
					  <th width="10%">创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <!-- <th width="10%">是否已推送至目录</th>-->
					  <th width="25%">操作</th>
			        </tr>
			       <#list listDatas as entity>
					<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					 <td width="2%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
			         <td width="5%">${entity_index+1}</td>
			         <td width="5%">${entity.appItemExchangeConf.appMsg.appName?default('')}</td>
			         <td title="${entity.appItemExchangeConf.appItem.appItemName?default('')}">
			          	<#if (entity.appItemExchangeConf.appItem.appItemName?exists)>
				          	<#if (entity.appItemExchangeConf.appItem.appItemName?length lt 13)>
							  	${entity.appItemExchangeConf.appItem.appItemName?default('')}
							<#else>
								${entity.appItemExchangeConf.appItem.appItemName[0..12]}... 
							</#if> 
						</#if>
			          </td>
			          <td width="10%">${entity.appItemExchangeConf.sendDept.deptName?default('')}</td>
					  <td width="15%">
			          	<#list entity.appItemExchangeConf.appItemExchangeConfDetails as appItemExchangeConfDetail>
			          		${appItemExchangeConfDetail.receiveDept.deptName},
			            </#list>
			          </td>
					  <td width="10%">${entity.createDate?date?default('')}</td>
			          <td width="15%" opt="opt">
			          	 <a desc="${entity.appItemExchangeConf.id}" href="#"  disabled="disabled" class="tabs1_cz" >
				          	<img src="${path}/images/small9/s_biaojiegou.gif"/>查看提供方表结构
				           </a>
			          </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
	    			    <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/shareItem/shareItem!delete.action')"><b class="hot">清除配置</b></a>
						<span class="page pageR">
				      		 <@pager.pageTag/>
				        </span>
			       </div>
			    </div>
			    <div class="clear"></div>
			</form>
			<#include "/common/commonList.ftl">
			<#include "/common/commonLhg.ftl">
			<script type='text/javascript'>
				$(document).ready(function(){
					$("table[class='tabs1'] tr td[opt] a[desc]").each(function(index, domEle){
						var changeConfId = $(domEle).attr("desc");
						if(changeConfId){
							$.post("${path}/exchange/item/item!getSendChangeItemByChangeConfId4Ajax.action",{itemIds:changeConfId},function(result, status){
								if(result){
									if(result.sendChangeItemId){
										$(domEle).attr("disabled",false);
										$(domEle).click(function(){
											opdg('${path}/exchange/tabledesc/tabledesc!listView.action?itemId='+result.sendChangeItemId,'表结构','1000','470');
										});
									}
								}
							});
						}
					});
				});
			</script>
	</body>
</html>