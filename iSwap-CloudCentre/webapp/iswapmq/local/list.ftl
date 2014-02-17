<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<#include "/common/taglibs.ftl">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>消息队列管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
  <script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />消息队列管理</div>
      <div class="c_m_btn"> 
      <span class="cm_btn_m">
      <a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/iswapmq/local/queueInfo!addView.action','新建队列',675,465);">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建队列</b></a></span>
      <!--
      <span class="cm_btn_m">
      <a href="javascript:void(0)" id="hz0" onclick="start();">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />启动服务</b></a></span>
      <span class="cm_btn_m">
      <a href="javascript:void(0)" id="hz0" onclick="stop();">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />停止服务</b></a></span>
      -->
      </div>
      
    </div>
    <div class="main_c">
     <form name="pageForm" id="pageForm" action="queueInfo!list.action" method="post">
           <table class="main_drop">
        <tr>
          <td align="right">
		  队列名称：<input name="conditions[e.queueName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_queueName?default("")}"  type="text" />
		  所属部门：<select name="conditions[e.deptId,string,like]" value="${serchMap.e_deptName?default("")}" onChange="">
              <option value="">所有部门</option>
              <#list listDepts as entity>
                 <option  value="${entity.id}">${entity.deptName}</option>
              </#list>
            </select>
		  服务类型： <select name="conditions[e.serType,string,like]" value="${serchMap.e_serType?default("")}" >
              <option value="" >所有类型</option>
              <option value="发送队列" <#if serType?exists&& serType== '发送队列' >selected</#if>>发送队列</option>
              <option value="接收队列" <#if serType?exists&& serType== '接收队列' >selected</#if>>接收队列</option>
            </select>
            <input name="" type="submit" value="查询"  class="btn_s" onClick="javascript:subForm('pageForm')"/></td>
        </tr>
      </table>    
                             
      <table class="tabs1"  style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28">序号</th>
          <th>队列名称</th>
          <th>所属部门</th>
          <th>队列类型</th>
          <th>消息服务器</th>
		  <th>创建时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
		  <th>操作</th>
        </tr>
          <#assign i = 0>  
	      <#list listDatas as entity>
	      
	      <#assign i = i+1>
	      <#if i%2=0>
		  <tr  onclick="selectedTD(this);">
		  <#else> 
		  <tr class="trbg" onclick="selectedTD(this);">  
		  </#if> 
	          <td class="tright"><input name="ids" value="${entity.id}" type="checkbox"/></td>
	          <td>${entity_index+1}</td>
	          <td><a href="javascript:void(0);" onClick="msgs('${entity.queueName}');" >${entity.queueName?default("")}</a></td>
	          <#list listDepts as entityobj>
	          	<#if entity.sysDept?exists >
	          	<#if entity.sysDept.id==entityobj.id><td>${entityobj.deptName?default("")}</td></#if>
			  	</#if>
			  </#list>
			  <td>${entity.serType?default("")}</td>
			  <td>消息接入</td>
			  <td>${entity.createTime?default("")}</td>
	            <td>
			      <a href="javascript:void(0)" onclick="opdg('${path}/iswapmq/local/queueInfo!updateView.action?id=${entity.id}','编辑队列',675,465)" class="tabs1_cz" id="hz1"><img src="${path}/images/czimg_edit.gif" />编辑</a>
				  <a href="javascript:void(0)"  onclick="del('${path}/iswapmq/local/queueInfo!delete.action?ids=${entity.id}');" class="tabs1_cz" /><img src="${path}/images/czimg_sc.gif" />删除</a>
				  <a href="javascript:void(0)" onclick="opdg('${path}/iswapmq/local/queueInfo!testView.action?id=${entity.id}','消息测试',675,238)" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />测试</a>
			  </td>
         </tr>
         
         </#list>
        
      </table>
      <div class="tabs_foot"> 
      <span class="tfl_btns">
      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
     <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
       <a href="javascript:delMany('queueInfo!delete.action')" class="tfl_blink"><b class="hot">删除</b></a></span> 
      
      
      <span class="page pageR"><@pager.pageTag/></span> 
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
 </form>
 <#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript">
$(function () {
$.ajax({
 type: "POST",
 url: "${path}/iswapmq/local/queueInfo!isRun.action",
 success: 
 function(msg)
 { 
 	if(msg == 'stop')
 	{
	 	$.ajax({
		 type: "POST",
		 url: "${path}/iswapmq/local/queueInfo!startMQ.action"
		});
	}
 } 
}); 
});
function msgs(name){
	document.pageForm.action="msgs/msgBody!list.action?queueName="+name;
	document.pageForm.submit();
}
function start(){
	document.pageForm.action="queueInfo!startMQ.action";
	document.pageForm.submit();
}	

function isRun(){
	document.pageForm.action="queueInfo!isRun.action";
	document.pageForm.submit();
}	
</script>
   
</body>
</html>



