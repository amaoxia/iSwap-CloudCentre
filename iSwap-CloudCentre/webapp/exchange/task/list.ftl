<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<#import "/common/pager.ftl" as pager>
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>任务管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>	
</head>
<body onclick="parent.hideMenu()" >
<form name="pageForm" action="taskAction!list.action?[conditions[e.sendState,string,ne]=0" method="post"> 
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title">
      			<img src="${path}/images/img_03.png" align="absmiddle" />任务管理</div>
           		<div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="opdg('${path}/exchange/task/taskAction!addView.action','添加任务',500,320);"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建任务<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
      <table class="main_drop">
       <tr>
          <td align="right">
			指标名称:<input type="text" onkeypress="showKeyPress()" onpaste="return false" name="conditions[e.item.itemName,string,like]" value="<#if serchMap.e_item_itemName?exists>${serchMap.e_item_itemName?default('')}</#if>"/>
           	 任务名称:<input type="text" onkeypress="showKeyPress()" onpaste="return false" name="conditions[e.taskName,string,like]" value="${serchMap.e_taskName?default('')}"/>
            <input  type="submit" value="查询"  class="btn_s"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="28">序号</th>
          <th width="40">提示灯</th>
          <th>任务名称</th>
          <th>执行日期</th>
          <th>指标名称</th>
          <th>任务负责单位</th>
          <th>状态&nbsp;<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
          <th>操作</th>
        </tr>
         <#assign i = 0>  
          <#list listDatas as sendTask>  
           <#assign i = i+1>
           <#if i%2=0>
      	<tr  onclick="selectedTD(this);">
        <#else> 
		  <tr class="trbg" onclick="selectedTD(this);">  
		  </#if> 
          <td>${sendTask_index+1}</td>
          <td style="height:10%;">
           <#if sendTask.sendState='3'>
          <img src="${path}/images/jc_red_s.png"/>
           </#if> 
            <#if sendTask.sendState='2'>
          <img src="${path}/images/jc_yellow_s.png"/>
           </#if> 
            <#if sendTask.sendState='1'>
          <img src="${path}/images/jc_green_s.png"/>
           </#if> 
          </td>
          <td>${sendTask.taskName}</td>
          <td>${sendTask.execDate}</td>
          <td>${sendTask.item.itemName?default('')}</td>
          <td>${sendTask.item.sysDept.deptName}</td>
          <td><font color="red" size="2">
           <#if sendTask.finishedState='0'>
         	未完成
           <#else>
         	已完成
           </#if> 
          </font></td>
          <td>
          <a href="#" onclick="opdg('${path}/exchange/task/taskAction!taskView.action?id=${sendTask.id}','任务信息',600,500)" class="tabs1_cz"><img src="${path}/images/small9/s_chakan.gif"/>查看</a>
          <a href="#" onclick="opdg('${path}/exchange/transact/transact!addView.action?taskId=${sendTask.id}','催办信息',600,350)" class="tabs1_cz"><img src="${path}/images/small9/s_cuiban.gif"/>我要催办</a>
          </td>
        </tr>
         </#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><a href="javascript:void(0)" class="tfl_blink"></a></span><img src="${path}/images/tabsf_bg.png" height="23"/> 
       <span class="page pageR"><@pager.pageTag/></span> 
      </span></div>
      </form>
    </div>

    <div class="clear"></div>
  </div>
<!--日期控件-->
<#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript">    
		<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights){
		var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true});
	    dg.ShowDialog();
	}
	function cuiban(id) {
	  	$.ajax({
	  		   type: "POST",
			   url: "${path}/exchange/task/taskAction!cuiban.action",
			   data: "id=" + id,
			   success: function(data){
			     alert("催办成功！");
			   }
	  	});
  	}
</script>
</body>
</html>
