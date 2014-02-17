<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户列表</title>
		<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<!-- jquery-->
		<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
		<!-- 增删改查 提交js操作-->
		<script type="text/javascript">    
			//修改用户状态
			function updateStatus(type){
				  document.getElementById('pageForm').action="user!updateStatus.action?status="+type;
				  var state = "";
				  if('0'==type){state="确定禁用改用户？"}
				  if('1'==type){state="确定启用该用户？"}
				  if(checks()) {
					  if(confirm(state)) {
					 	document.getElementById('pageForm').submit();
					  }
				  } else {
				  	alert('至少选择一条数据');
				  }
			}
		</script>
	</head>
	<body onclick="parent.hideMenu()">
		<form name="pageForm" method="post"  action="${path}/sysmanager/user/user!list.action">
		    <div class="common_menu">
		      <div class="c_m_title"><img src="${path}/images/title/user.png"  align="absmiddle" />用户管理</div>
		      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/user/user!addView.action','新增用户','660','540')"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建用户<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
		    </div>
		    <div class="main_c">
			     <table class="main_drop">
			        <tr>
			          <td align="right">
					  用户名：		  <input  name="userName" onkeypress="showKeyPress()" onpaste="return false" type="text"  value="${userName?default('')}" />
					  所属部门：     <input  name="deptName" onkeypress="showKeyPress()" onpaste="return false" type="text" value="${deptName?default('')}" />
			          <input  type="submit" value="查询"  class="btn_s"/></td>
			        </tr>
			     </table>                              
			     <table class="tabs1"  style="margin-top:0px;">
			          <tr>
			          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
			          <th width="28">序号</th>
			          <th>用户名</th>
			          <th>所属部门</th>
			          <th>角色</th>
			          <th>状态</th>
					  <th>创建时间<a href="javascript:void(0);" onclick="setOrder(this)" name="u.date_Create">&nbsp;&nbsp;&nbsp;<img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
					  <th>操作</th>
			        </tr>
			        <!-- list列表编号 -->
					<#if page?exists>
					 <#if page.index gt 0>
					         <#assign len=(page.index-1)*(page.perPage)>
					 </#if>
					 </#if>
			        <#list obj as entity>
			          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
			          <td class="tright"><input name="ids" type="checkbox" value="${entity[0]}" <#if entity[0]?exists><#if entity[0]==1>disabled</#if></#if> /></td>
			          <td>${len+entity_index+1}</td>
			          <td>${entity[1]?default('')}</td>
			          <td>${entity[3]?default('未指定')}</td>
					  <td>${entity[4]?default('未授权')}</td>
					  <td>			 <span  class="font_red">
					                  <#if entity[5] == '0'>
					                      	禁用
					                  <#else>
					                  		启用
					                  </#if>
					              </span>
					  </td>
					  <td>${entity[2]?date?default('')}</td>
			          <td>
			          <#if entity[0]?exists>
			            <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/user/user!view.action?id=${entity[0]}','查看','560','520');" class="tabs1_cz">
									          <img src="${path}/images/small9/s_chakan.gif" />查看</a> 
			          <#if entity[0]==1>
			               	<a href="javascript:void(0)" id="hz0"  class="tabs1_cz" style="color:#ccc;"><img src="${path}/images/czimg_edit.gif" />编辑</a> 
			            	<a href="javascript:void(0)" class="tabs1_cz" style="color:#ccc;"><img src="${path}/images/small9/jihuo.gif" /><#if entity[5] == '0'>启用<#else>禁用</#if></a>
			                <a href="#" class="tabs1_cz" style="color:#ccc;"><img src="${path}/images/small9/czimg_sc.gif"/>删除</a>
			          <#else>
			             <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/user/user!updateView.action?id=${entity[0]}','修改用户信息','660','540');" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a> 
			          			<#if entity[5] == '0'>
						         	 <a href="user!updateStatus.action?ids=${entity[0]}&status=1" class="tabs1_cz"><img src="${path}/images/small9/jihuo.gif" />启用</a>
						          <#else>
						          	<a href="user!updateStatus.action?ids=${entity[0]}&status=0" class="tabs1_cz"><img src="${path}/images/small9/dongjie.gif" />禁用</a>
						          </#if>
			                    <a href="#" class="tabs1_cz" onclick="del('${path}/sysmanager/user/user!delete.action?ids=${entity[0]}')"><img src="${path}/images/small9/czimg_sc.gif"/>删除</a>
			           </#if></#if>
			             </td>
			        </tr>
			        </#list>
			      </table>
			      <div class="tabs_foot"> 
				      <span class="tfl_btns">
							      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
							      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
							      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
							      <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/sysmanager/user/user!delete.action')"><b class="hot">删除</b></a>
				      			<a href="javascript:updateStatus('0')"  class="tfl_blink"><b >禁用</b></a>
				      			<a href="javascript:updateStatus('1')"  class="tfl_blink"><b>启用</b></a>
				      </span> <span class="page pageR">
				   		<@pager.pageTag/></span>
				   </div>
			    <div class="clear"></div>
			  </div>
			  <div class="clear"></div>
		</form>
		<#include "/common/commonList.ftl">
		<#include "/common/commonLhg.ftl">
	</body>
</html>