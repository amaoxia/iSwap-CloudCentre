<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>基础库类型列表</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery.js'></script>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
</head>
			<body  onclick="parent.hideMenu()">
			<form name="pageForm" method="post"  action="${path}/cloudstorage/basicType/basicType!list.action">
			<div class="frameset_w" style="height:100%">
  			<div class="main">
   			<div class="common_menu">
     		<div class="c_m_title"><img src="${path}/images/title/role.png"  align="absmiddle" />基础库类型管理</div>
      		<div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/basicType/basicType!addView.action','新增类型','670','340');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建类型<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    		</div>
    		<div class="main_c">
            <table class="main_drop">
            <tr>
            <td align="right">
		      基础库类型名称：
		     <input name="conditions[e.basicTypeName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_basicTypeName?default("")}"/>
             <input  type="submit" value="查询"  class="btn_s"/></td>
           </tr>
           </table>                              
      	  <table class="tabs1"  style="margin-top:0px;">
          <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28">序号</th>
          <th>基础库类型名称</th>
          <th>基础库类型编码</th>
		  <th>创建时间<a href="javascript:void(0);" onclick="setOrder(this)" name="e.createDate"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
		  <th windth="20%">操作</th>
           </tr>
          <#list listDatas as entity>
           <tr  <#if ((entity_index+1)%2)==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input  type="checkbox" name="ids" value="${entity.id}"<#if entity.code?exists><#if entity.code=='administrator'>disabled="disabled"</#if></#if> /></td>
          <td>${entity_index+1}</td>
          <td>${entity.basicTypeName?default('')}</td>
          <td>${entity.basicTypeCode?default('')}</td>
		  <td>${entity.createDate?default('')}</td>
          <td>
            <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/basicType/basicType!view.action?id=${entity.id}','类型信息','500','250');" class="tabs1_cz">
			<img src="${path}/images/small9/s_chakan.gif" />查看</a>
	       	<a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/basicType/basicType!updateView.action?id=${entity.id}','编辑类型','670','340');" class="tabs1_cz">
	       	<img src="${path}/images/czimg_edit.gif"/>编辑</a>
		  	<a href="#"class="tabs1_cz" onclick="del('${path}/cloudstorage/basicType/basicType!delete.action?ids=${entity.id}')">
		  	<img src="${path}/images/czimg_sc.gif" />删除</a>
		  </td>
		    </tr>
		   </#list>
          </table>
          <div class="tabs_foot"> 
          <span class="tfl_btns">
          <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
           <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
           <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
           <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/cloudstorage/basicType/basicType!delete.action')"><b class="hot">删除</b></a></span>
            <span class="page pageR"><@pager.pageTag/></span></div>
    		</div>
    	  <div class="clear"></div>
  		</div>
  		<div class="clear"></div>
	</div>
</from>
</body>
</html>

