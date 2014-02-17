<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>数据共享目录</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
	</head>
	<body>
		<form name="pageForm" method="post"  action="${path}/cloudstorage/metaData/metaData!list.action?type=${type}">
			<input type="hidden" name="deptId" id="deptId" value="${deptId}"/>
			    <div class="common_menu">
			      <div class="c_m_title"><img src="${path}/images/title/meta.png"  align="absmiddle" />数据共享目录</div>
			      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/metadata/metadata!addView.action?type=${type}&deptId=${deptId}&deptName='+encodeURI('${deptName}'),'新增指标','630','<#if type=='1'>420<#elseif type=='2'>480<#else>520</#if>');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新增指标项<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
			    </div>
			    <div class="main_c">
		           <table class="main_drop">
		       		 <tr>
	          			<td align="right">
	          				指标名称：	
	          				<input name="conditions[e.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_targetName?default("")}" type="text"  />
			  				<#if deptId?exists><#else>部门名称：</#if>
			  				<input name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="${deptName?default("")}" type="text"  style="<#if deptId?exists>display:none;</#if>"/>
					        <input name="" type="submit" value="查询"  class="btn_s"/>
					    </td>
					 </tr>
			      </table>                              
			      	<table class="tabs1"  style="margin-top:0px;">
			          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
			          <th width="5%">序号</th>
			          <th width="15%">指标名称</th>
			          <th width="10%">部门名称</th>
			          <th width="10%">数据源名称</th>
					  <th width="5%">创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th width="25%">操作</th>
			        </tr>
			       <#list listDatas as entity>
						<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
					  <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
			          <td>${entity_index+1}</td>
			          <td title="${entity.targetName?default('')}">
			          	<#if (entity.targetName?exists)&&(entity.targetName?length lt 13)>
						  	${entity.targetName?default('')}
						<#else>
							${entity.targetName[0..12]}... 
						</#if> 
			          </td>
			          <td><#if entity.sysDept?exists>${entity.sysDept.deptName?default('无')}</#if></td>
			          <td><#if entity.couldDataSource?exists>${entity.couldDataSource.sourceName?default('无')}</#if></td>
					  <td><#if entity.createDate?exists>${entity.createDate?date?default('无')}</#if></td>
			          <td>
				           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/metaData/metaData!view.action?id=${entity.id}','查看','550','<#if type=='1'>370<#elseif type=='2'>400<#else>410</#if>');" class="tabs1_cz">
  						   		<img src="${path}/images/small9/s_chakan.gif" />查看
  						   </a> 	
				           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/metaData/metaData!updateView.action?type=${type}&id=${entity.id}','编辑','630','<#if type=='1'>420<#elseif type=='2'>480<#else>520</#if>');" class="tabs1_cz">
				          		<img src="${path}/images/czimg_edit.gif" />编辑
				           </a>
				           <a href="#" onclick="opdg('${path}/cloudstorage/tableinfo/tableinfo!addDesc.action?metaDataId=${entity.id}','表结构','1000','470');" class="tabs1_cz" >
				           		<img src="${path}/images/small9/s_biaojiegou.gif"/>表结构
				           </a>
				           <a href="#" onclick="opdg('${path}/cloudstorage/metaDataMapper/metaDataMapper!list.action?metaDataId=${entity.id}&deptId=${deptId}','mapper配置','1000','470');" class="tabs1_cz" >
				          	<img src="${path}/images/small9/s_biaojiegou.gif"/>mapper
				           </a>
				            <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/cloudstorage/metaData/metaData!delete.action?ids=${entity.id}&type=${type}')">
				           		<img src="${path}/images/small9/czimg_sc.gif" />删除
				           	</a>
			           </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
	    			   <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/cloudstorage/metaData/metaData!delete.action?type=${type}')"><b class="hot">删除</b></a>
     					<img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
						<span class="page pageR">
				       <@pager.pageTag/>
				      </span>
			      </div>
			</div>
			<div class="clear"></div>
		</form>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
	</body>
</html>