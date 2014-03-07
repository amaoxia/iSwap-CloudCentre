<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>发送指标项</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body onclick="parent.hideMenu();">
		<form name="pageForm" method="post"  action="${path}/exchange/sendItem/sendItem!list.action">
			<input type="hidden" name="deptId" id="deptId" value="${deptId}"/>
			<input type="hidden" name="deptName" id="deptName" value="${deptName}"/>
			    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/item.png"  align="absmiddle" />
			      		发送指标项
			      </div>
			      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItem/sendItem!addExchangeConfView.action?deptId=${deptId}&deptName=${deptName}','新建发送指标','660','540')"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建发送指标<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
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
			          <th width="5%">指标名称</th>
			          <th width="15%">接收部门</th>
			          <th width="5%">交换类型</th>
			          <th width="5%">数据源类型</th>
			          <th width="10%">关联数据源</th>
			          <th width="5%">关联信息</th>
					  <th width="10%">创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th width="20%">操作</th>
			        </tr>
			       <#list listDatas as entity>
					<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					 <td width="2%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
			         <td width="5%">${entity_index+1}</td>
			         <td width="5%">${entity.appItemExchangeConf.appMsg.appName?default('')}</td>
			         <td width="5%" title="${entity.appItemExchangeConf.appItem.appItemName?default('')}">
			          	<#if (entity.appItemExchangeConf.appItem.appItemName?exists)>
				          	<#if (entity.appItemExchangeConf.appItem.appItemName?length lt 13)>
							  	${entity.appItemExchangeConf.appItem.appItemName?default('')}
							<#else>
								${entity.appItemExchangeConf.appItem.appItemName[0..12]}... 
							</#if> 
						</#if>
			          </td>
			          <td width="15%">
			          	<#list entity.appItemExchangeConf.appItemExchangeConfDetails as appItemExchangeConfDetail>
			          		${appItemExchangeConfDetail.receiveDept.deptName},
			            </#list>
			          </td>
			          <td width="5%">${entity.dataTypeStr?default('')}</td>
			          <td width="5%">${entity.dsTypeStr?default('')}</td>
			          <td width="10%"><#if entity.datSource?exists>${entity.datSource.sourceName?default('无')}</#if></td>
			          <td width="5%">${entity.tableName?default('')}</td>
					  <td width="10%">${entity.createDate?date?default('')}</td>
			          <td width="20%">
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/item/item!view.action?id=${entity.id}','查看','570','460');" class="tabs1_cz">
				          	<img src="${path}/images/small9/s_chakan.gif" />查看
				          </a> 
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItem/sendItem!updateExchangeConfView.action?id=${entity.appItemExchangeConf.id}','修改发送指标','630','590');" class="tabs1_cz">
				          	<img src="${path}/images/czimg_edit.gif" />修改
				          </a>
				          <a href="javascript:void(0)" id="hz0" onclick="del('${path}/exchange/sendItem/sendItem!deleteExchangeConf.action?ids=${entity.appItemExchangeConf.id}');return false;" class="tabs1_cz">
				          	<img src="${path}/images/czimg_edit.gif" />删除
				          </a>
				           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItem/sendItem!updateView.action?id=${entity.id}','编辑','630','590');" class="tabs1_cz">
				          	<img src="${path}/images/czimg_edit.gif" />配置
				          </a> 
				          <#if entity.dataType?exists>
				           <a href="#"  <#if entity.dataType=='1'> onclick="opdg('${path}/exchange/tabledesc/tabledesc!addDesc.action?itemId=${entity.id}','表结构','1000','470');"<#else>style="color:#ccc;"</#if> class="tabs1_cz" >
				          	<img src="${path}/images/small9/s_biaojiegou.gif"/>表结构
				           </a>
				          </#if>
				          
				          <#-- 
				          <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/exchange/sendItem/sendItem!delete.action?ids=${entity.id}')">
				           <img src="${path}/images/small9/czimg_sc.gif" />清除配置
				          </a>
				          -->
			          </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
	    			    <#--<a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/sendItem/sendItem!delete.action')"><b class="hot">清除配置</b></a>-->
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
			</script>
	</body>
</html>