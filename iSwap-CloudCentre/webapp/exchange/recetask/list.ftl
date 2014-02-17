<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<#import "/common/pager.ftl" as pager>
<head>
<#global path = request.getContextPath() >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
<!--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
</head>
<body onclick="parent.hideMenu()">
	<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="main_c">
      <div></div>
      <table class="main_drop" style="margin:6px auto 0px auto;">
       <form name="pageForm" action="receTaskAction!list.action" method="post">     
        <tr>
          <td align="right">
          指标名称：<input type="text" name="conditions[e.itemName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_itemName?default("")}"  />
	      	执行日期：从<input id="d4311" class="Wdate" name="start" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
	      	至：<input id="d4312" class="Wdate" name="end" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
	      	&nbsp;&nbsp;
	        <input name="" type="button"  value="查询" onClick="javascript:subForm('pageForm')"   class="btn_s"/>	
          </td>
        </tr>
        </form> 
      </table>
      <table class="tabs1">
        <tr onclick="selectedTD(this);">
          <th width="28">序号</th>
          
          <th>指标名称</th>
          <th>交换日期</th>
          <th>数据接收量</th>
          <th>数据发送方</th>
        </tr>
        
       <#assign i = 0>  
          <#list listDatas as receTask>  
           <#assign i = i+1>
           <#if i%2=0>
      <tr  onclick="selectedTD(this);">
        <#else> 
		  <tr class="trbg" onclick="selectedTD(this);">  
		  </#if> 
          <td>${receTask_index+1}</td>
          <td><a href="javascript:void(0)">${receTask.itemName?default('')}</a></td>
          <td>${receTask.exchangeDate?default('')}</td>
          <td>${receTask.dataNum?default('')}</td>
          <td>${receTask.receiveDeptAnme?default('')}</td>
        </tr>
        
        </#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><a href="javascript:void(0)" class="tfl_blink"></a></span><img src="${path}/images/tabsf_bg.png" height="23"/> 
       <span class="page pageR"><@pager.pageTag/></span> 
      </span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
  </div>
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
