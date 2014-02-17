<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=gbk" pageEncoding="gbk"%>
<#include "/common/taglibs.ftl">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
<#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript" >

	function goback(){
		  document.pageForm.action="../queueInfo!list.action";
		  document.pageForm.submit();
	
		}
</script>
</head>

<body >
<div class="frameset_w" style="height:100%">
  <div class="main">
     <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />队列消息查看</div>
      <div class="c_m_btn"><span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="goback();"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />返回<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
     <form name="pageForm" action="msgBody!list.action" method="post">
	  <!-- 当前队列共有 ${listDatas?size} 条消息 -->
      <table class="tabs1"  style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="28">序号</th>
          <th>所属队列</th>
          <th>消息ID</th>
          <th>优先级</th>
		  <th>消息大小(KB)</th>
		  <th>发送时间</th>
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
	          <td>${entity_index+1}</td>
	          <td>${entity.container?default("")}</td>
			  <td>${entity.id?default("")}</td>
			  <td>${entity.priority?default("")}</td>
			  <td>${entity.bytes?default("")}</td>
			  <td>${entity.createTime?default("")}</td>
			  <td>
			   <a href="javascript:void(0)" onclick="opdg('${path}/iswapmq/local/msgs/msgBody!updateView.action?id=${entity.id}','查看消息',675,230)" class="tabs1_cz" id="hz1"><img src="${request.getContextPath()}/images/czimg_edit.gif" />查看消息</a>
         	  </td>
         </tr>
         <input type="hidden" name="queueName" id="queueName" value="${entity.container?default("")}"/>
         </#list>
         
      </table>
      <div class="tabs_foot"> 
      <span class="tfl_btns">
      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
     <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;"></a>
      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
       <a href="javascript:delMany('queueInfo!delete.action')" class="tfl_blink"><b class="hot"></b></a></span> 
      <span class="page pageR"><@pager.pageTag/></span> 
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
 </form>  
</body>
</html>
