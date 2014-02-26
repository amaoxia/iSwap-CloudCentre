<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据交换目录</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body onclick="parent.hideMenu();">
		<form name="pageForm" method="post"  action="${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!list.action">
			    <div class="common_menu">
			      <div class="c_m_title">
			      	<img src="${path}/images/title/item.png"  align="absmiddle" />
			      		数据交换目录
			      </div>
			      <div class="c_m_btn"> 
			      	<span class="cm_btn_m">
			      		<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!addView.action','新增指标',600,580);">
				      		<b>
				      			<img src="${path}/images/cmb_xj.gif" class="cmb_img" />
				      			新增指标配置
				      			<a href="javascript:void(0)">
				      				<img src="${path}/images/bullet_add.png" class="bullet_add" />
				      			</a>
				      		</b>
			      		</a>
			      	</span>
			      	<span class="cm_btn_m">&nbsp;</span></div>
			    </div>
		    	<div class="main_c">
		           <table class="main_drop">
   		 			  <tr>
	          			<td align="right">
	          				应用名称：	<input name="conditions[e.appMsg.appName,string,like]"  onpaste="return false" value="${serchMap.e_appMsg_appName?default("")}" type="text"  />
			  				指标项名称：	<input name="conditions[e.appItem.appItemName,string,like]"  onpaste="return false" value="${serchMap.e_appItem_appItemName?default("")}" type="text"  />
			  				数据提供部门名称：	<input name="conditions[e.sendDept.deptName,string,like]"  onpaste="return false" value="${serchMap.e_sendDept_deptName?default("")}" type="text"  />
					          <input name="" type="submit" value="查询"  class="btn_s"/>
					     </td>
					  </tr>
					</table>                              
			      	<table class="tabs1"  style="margin-top:0px;">
			          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="5%">序号</th>
			          <th width="15%">应用名称</th>
			          <th width="15%">指标名称</th>
			          <th width="15%">数据提供部门</th>
			          <th width="15%">数据接收部门</th>
					  <th width="10%">创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th width="25%">操作</th>
			        </tr>
			       <#list listDatas as entity>
					<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					 <td width="2%"><input name="ids" type="checkbox" value="${entity.id}" /></td>
			         <td width="5%">${entity_index+1}</td>
			         <td width="5%">${entity.appMsg.appName}</td>
			         <td title="${entity.appItem.appItemName?default('')}">
			         	<#if (entity.appItem.appItemName?exists)>
				          	<#if (entity.appItem.appItemName?length lt 13)>
							  	${entity.appItem.appItemName?default('')}
							<#else>
								${entity.appItem.appItemName[0..12]}... 
							</#if> 
						</#if> 
			          </td>
			          <td width="5%">${entity.sendDept.deptName}</td>
			          <td width="5%">
			          	<#list entity.appItemExchangeConfDetails as appItemExchangeConfDetail>
			          		${appItemExchangeConfDetail.receiveDept.deptName},
			            </#list>
			          </td>
					  <td width="10%">${entity.createDate?date?default('无')}</td>
			          <td width="15%">
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!view.action?id=${entity.id}','查看','570','460');" class="tabs1_cz">
				          	<img src="${path}/images/small9/s_chakan.gif" />查看
				          </a> 
				          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!updateView.action?id=${entity.id}','编辑','630','590');" class="tabs1_cz">
				          	<img src="${path}/images/czimg_edit.gif" />编辑
				          </a> 
				          <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!delete.action?ids=${entity.id}')">
				           <img src="${path}/images/small9/czimg_sc.gif" />删除
				          </a>
			          </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
	    			    <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/appitemmgr/appItemExchangeConf/appItemExchangeConf!delete.action')"><b class="hot">删除</b></a>
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