<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云存储空间定制</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body onclick="parent.hideMenu()">
<form action="cloudStorageMake!list.action" method="post" name="pageForm" id="pageForm">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/cloudMake.png"  align="absmiddle" />云存储空间定制</div>
	  <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz1" onclick="opdg('${path}/cloudcenter/cloudStorageMake/cloudStorageMake!addView.action','注册云存储空间',600,500);"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />注册云存储空间<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
    <table class="main_drop">
        <tr>
          <td align="right">空间名称：
            <input name="storageName" value="${storageName?default("")}" type="text" onkeypress="showKeyPress()" onpaste="return false"/>
            所属应用：
            <select name="appId">
					  <option value="">---请选择---</option>
					  <#list appMsgs as appMsg>
					  <option value="${appMsg.id?default('')}" <#if appId?exists><#if appId==appMsg.id>selected</#if></#if>>${appMsg.appName?default("")}</option>
					  </#list>
			</select>
            <input name="" type="button" value="查询"  onclick="subForm('pageForm')"  class="btn_s"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr>
          <th width="20">&nbsp;</th>
          <th width="28">序号</th>
          <th width="30%">空间名称</th>
		 <!-- <th>已使用</th>-->
		  <th>最大存储空间</th>
		  <th>所属应用</th>
          <th>创建时间</th>
          <th>操  作</th>
        </tr>
        
        <#list listDatas as entity>
          <tr <#if entity_index%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input type="checkbox" name="ids" value="${entity.id}"/></td>
          <td width="28">${entity_index+1}</td>
          <td><a href="javascript:opdg('${path}/cloudcenter/cloudStorageMake/cloudStorageMake!view.action?id=${entity.id}','查看云存储空间',500,280);">${entity.storageName?default("")}</a></td>
          <!--<td><#if entity.storageSize?exists>${entity.storageSize?default("")}M</#if></td>-->
		  <td><#if entity.storageSizeCount?exists>${entity.storageSizeCount?default("")}M</#if></td>
		  <td>${entity.appMsg.appName?default("")}</td>
          <td>${entity.createDate?default("")}</td>
          <td><a href="javascript:opdg('${path}/cloudcenter/cloudStorageMake/cloudStorageMake!updateView.action?id=${entity.id}','编辑云存储空间',600,500 )" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;<a id="hz4" href="javascript:del('cloudStorageMake!delete.action?ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/czimg_sc.gif" />删除</a></td>
        </tr>
		</#list> 
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:delMany('cloudStorageMake!delete.action');" class="tfl_blink"><b class="hot">删除</b></a></span> 
      <span class="page pageR"> 
      <@pager.pageTag/>
      </span>  </span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
 <#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script>   
function reloadPage(){
	window.location.href="cloudStorageMake!list.action";
}
</script>
</body>
</html>
