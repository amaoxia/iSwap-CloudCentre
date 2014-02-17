<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>路由配置列表</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body onclick="parent.hideMenu()">
<form name="pageForm" method="post"  action="${path}/exchange/sendItemDept/sendItemDept!list.action">
<div class="frameset_w" style="height:100%">
      <div class="main">
      <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/item.png"  align="absmiddle" />路由配置</div>
      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/sysmanager/user/user!addView.action','新增用户','660','540')"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建用户<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    	
    	<div class="main_c">
           <table class="main_drop">
       		 				<tr>
		          			<td align="right">
				  				指标项名称：	<input name="itemName" onkeypress="showKeyPress()" onpaste="return false" value="<#if itemName?exists>${itemName?default("")}</#if>" type="text"  />
				  				所属部门：	<input name="deptName" onkeypress="showKeyPress()" onpaste="return false" value="<#if deptName?exists>${deptName?default("")}</#if>" type="text"  />
						          <input name="" type="submit" value="查询"  class="btn_s"/></td>
						        </tr>
						      </table>                              
						      	<table class="tabs1"  style="margin-top:0px;">
						          <th width="2%"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
						          <th width="5%">序号</th>
						          <th width="5%">接收部门</th>
						          <th width="10%">发送部门</th>
						          <th width="10%">指标数量</th>
								  <th width="15%">部门创建时间<a href="javascript:void(0)" onclick="setOrder(this)" name="d.date_create"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
								  <th width="15%">操作</th>
						        </tr>
						       <#list obj as entity>
          						<tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          						  <td width="2%"><input name="ids" type="checkbox" value="${entity[0]}" <#if !entity[3]?exists>disabled</#if>/></td>
						          <td>${entity_index+1}</td>
						          <td>${entity[1]?default('无')}</td>
						          <td><a title="${entity[2]?default('无')}">${subStr(entity[2]?default('无'),10)}</a></td>
						          <td>${entity[3]?default('无')}</td>
								  <td>${entity[4]?date?default('无')}</td>
						          <td width="15%">
						         <!-- <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItemDept/sendItemDept!view.action?deptId=${entity[0]}','查看','570','440');" class="tabs1_cz">
						          <img src="${path}/images/small9/s_chakan.gif" />查看</a>-->
						          <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/sendItemDept/sendItemDept!addView.action?deptId=${entity[0]}','配置路由','470','540');" class="tabs1_cz">
						          <img src="${path}/images/czimg_edit.gif" />配置路由</a> 
						          <a href="#" id="hz0"  class="tabs1_cz" <#if !entity[3]?exists>style="color:#ccc;"<#else>onclick="del('${path}/exchange/sendItemDept/sendItemDept!delete.action?ids=${entity[0]}')" </#if>>
						          <img src="${path}/images/small9/czimg_sc.gif" />解除配置</a>
						          </td>
						        </tr>
						        </#list>
						      </table>
						      <div class="tabs_foot"> 
						       <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			    			    <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/sendItemDept/sendItemDept!delete.action')"><b class="hot">解除配置</b></a>
       							<span class="page pageR">
						       <@pager.pageTag/>
						      </span>
						       </div>
						    </div>
					</form>
					<#include "/common/commonList.ftl">
					<#include "/common/commonLhg.ftl">
					<script type="text/javascript">
					//删除本条
					function del(url) {
						if (confirm("确定要解除关系吗？")) {
							document.forms['pageForm'].action = url;
							document.forms['pageForm'].submit();
						}
					}
					
					//删除选中
					function delMany(url) {
						if($("input[name='ids']:checked ").length==0){
							alert("请选择记录！");
							return;
						}
						if (confirm("确定要解除关系吗？")) {
							document.forms['pageForm'].action = url;
							document.forms['pageForm'].submit();
						}
					}
					</script>
			</body>
</html>