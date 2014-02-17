<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>云存储节点配置</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body >
<div class="frameset_w" style="height:100%" onclick="parent.hideMenu()">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/CloudNodeMg.png"  align="absmiddle" />云存储节点管理</div>
      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudNodeMg/cloudNodeMg!addView.action','新建','580','380');">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建云节点<a href="javascript:void(0)">
      <img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
           <table class="main_drop">
        <tr>
          <td align="right">
          <form name="pageForm" method="post"  action="${path}/cloudstorage/cloudNodeMg/cloudNodeMg!list.action">
		  云节点名称：<input name="conditions[e.nodesName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_nodesName?default("")}" type="text" />
		  状态：
		  <select name="conditions[e.status,string,eq]">
		      <option value="">请选择</option>
              <option value="1" <#if serchMap.e_status?exists&&serchMap.e_status=="1">selected</#if>>成功</option>
              <option value="0" <#if serchMap.e_status?exists&&serchMap.e_status=="0">selected</#if>>失败</option>
            </select>
            <input  type="submit" value="查询"  class="btn_s"/></td>
        </tr>
      </table>                              
      <table class="tabs1"  style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a></th>
          <th width="28">序号</th>
          <th>云节点名称</th>
          <th>服务地址</th>
          <th>总存储</th>
		  <!--<th>已使用</th>-->
		  <th>状态<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
		  <th>创建时间</th>
		  <th>操作</th>
        </tr>
          <#list listDatas as entity>
           <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
           <td class="tright"><input name="ids" type="checkbox" value="${entity.id}" /></td>
          <td>${entity_index+1}</td>
          <td>${entity.nodesName?default('无')}</td>
          <td>${entity.serverAddress?default('无')}</td>
		  <td>${entity.storageCount?default('无')}</td>
		 <!-- <td>${entity.useCount?default('')}</td>-->
		  <td><#if entity.status?exists><#if entity.status="1">成功<#else>失败</#if><#else>(空)</#if></td>
		  <td><#if entity.createDate?exists>${entity.createDate?date?default('无')}</#if></td>
           <td>		
           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudNodeMg/cloudNodeMg!view.action?id=${entity.id}','查看','580','250');" class="tabs1_cz">
	       <img src="${path}/images/small9/s_chakan.gif" />查看</a> 																			  	
           <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/cloudstorage/cloudNodeMg/cloudNodeMg!updateView.action?id=${entity.id}','编辑','580','380');" class="tabs1_cz">
           <img src="${path}/images/czimg_edit.gif" />编辑</a>
          <a href="#" id="hz0"  class="tabs1_cz" onclick="del('${path}/cloudstorage/cloudNodeMg/cloudNodeMg!delete.action?ids=${entity.id}')"><img src="${path}/images/small9/czimg_sc.gif" />删除</a>
          </td>
        </tr>
      </#list>
      </table>
  					<div class="tabs_foot"> 
						 <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			    		 <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/cloudstorage/cloudNodeMg/cloudNodeMg!delete.action')"><b class="hot">删除</b></a>
             			<img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
       					<span class="page pageR">
						   <@pager.pageTag/>
						       </from>
						      </span>
						       </div>
						    </div>    
						    <#include "/common/commonList.ftl">
							<#include "/common/commonLhg.ftl">													
</body>
</html>