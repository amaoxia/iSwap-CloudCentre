<#assign s=JspTaglibs["/WEB-INF/struts-tags.tld"]>
<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>角色列表</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
	</head>
	<body>
		<form name="pageForm" method="post"  action="${path}/sysmanager/role/role!list.action">
			 <@s.token />
   			<div class="common_menu">
	     		<div class="c_m_title"><img src="${path}/images/title/role.png"  align="absmiddle" />角色管理</div>
	      		<div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/role/role!addView.action','添加角色','600','340');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建角色<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    		</div>
    		<div class="main_c">
	            <table class="main_drop">
		            <tr>
			            <td align="right">
						          角色名称：
						     <input name="conditions[e.name,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_name?default("")}"/>
				             <input  type="submit" value="查询"  class="btn_s"/>
			            </td>
		           </tr>
	           </table>                              
	      	  <table class="tabs1"  style="margin-top:0px;">
		          <tr onclick="selectedTD(this);">
			          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
			          <th width="28">序号</th>
			          <th>角色名</th>
			          <th>角色代码</th>
			          <th>创建人</th>
					  <th>创建时间<a href="javascript:void(0);" onclick="setOrder(this)" name="e.dateCreate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th windth="20%">操作</th>
	           	  </tr>
		          <#list listDatas as entity>
		           <tr  <#if ((entity_index+1)%2)==0>class="trbg"</#if> onclick="selectedTD(this);">
		          <td><input  type="checkbox" name="ids" value="${entity.id}"<#if entity.code?exists><#if entity.code=='administrator'>disabled="disabled"</#if></#if> /></td>
		          <td>${entity_index+1}</td>
		          <td>${entity.name?default('')}</td>
		          <td>${entity.code?default('')}</td>
				  <td>${entity.creator?default('空')}</td>
				  <td>${entity.dateCreate?default('')}</td>
		          <td>
		          <#if entity.code?exists>
					<#if entity.code=='administrator'>
					<a href="javascript:void(0)" id="hz0" style="color:#ccc;" onclick="opdg('${path}/sysmanager/role/role!roleView.action?id=${entity.id}','角色信息','500','260');" class="tabs1_cz">
								          <img src="${path}/images/small9/s_chakan.gif" />查看</a> 
					  <a href="javascript:void(0)" id="hz0"  style="color:#ccc;" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif"/>编辑</a>
				  	  <a href="javascript:void(0)" class="tabs1_cz" style="color:#ccc;"><img src="${path}/images/czimg_sc.gif" />删除</a>
					<#else>
					<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/role/role!roleView.action?id=${entity.id}','角色信息','500','260');" class="tabs1_cz">
					<img src="${path}/images/small9/s_chakan.gif" />查看</a>
			       	<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/role/role!updateView.action?id=${entity.id}','新增角色','670','340');" class="tabs1_cz">
			       	<img src="${path}/images/czimg_edit.gif"/>编辑</a>
				  	<a href="#"class="tabs1_cz" onclick="del('${path}/sysmanager/role/role!delete.action?ids=${entity.id}')">
				  	<img src="${path}/images/czimg_sc.gif" />删除</a>
		            </#if>
		            <#else>
		            <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/role/role!roleView.action?id=${entity.id}','角色信息','500','260');" class="tabs1_cz">
					<img src="${path}/images/small9/s_chakan.gif" />查看</a>
			       	<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/role/role!updateView.action?id=${entity.id}','角色编辑','670','340');" class="tabs1_cz">
			       	<img src="${path}/images/czimg_edit.gif"/>编辑</a>
				  	<a href="#" class="tabs1_cz" onclick="del('${path}/sysmanager/role/role!delete.action?ids=${entity.id}')">
				  	<img src="${path}/images/czimg_sc.gif" />删除</a>
		            </#if>
				  </td>
				    </tr>
				   </#list>
	          </table>
	          <div class="tabs_foot"> 
		          <span class="tfl_btns">
		          	<img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
		            <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
		            <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
		            <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/sysmanager/role/role!delete.action')">
		            	<b class="hot">删除</b>
		            </a>
		          </span>
		          <span class="page pageR"><@pager.pageTag/></span>
		       </div>
	    	</div>
	    	<div class="clear"></div>
		</from>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
	</body>
</html>

