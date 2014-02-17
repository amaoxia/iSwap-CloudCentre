<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${path}/js/jquery-1.5.1.js"></script>
</head>
<body >
 <form name="pageForm" action="auditResultsAction!list.action" method="post">  
<div class="frameset_w" style="height:100%" onclick="parent.hideMenu()">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />已审计通过</div>
      
    </div>
    <div class="main_c">
                                     
      <table class="main_drop">
        <tr>
          <td align="right">
		  指标名称：<input name="conditions[e.targetName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_targetName?default("")}"  type="text" />
		  所属流程名称：<input name="conditions[e.flowName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_flowName?default("")}"  type="text" />
		  审计时间：<input id="d4311" class="Wdate" name="start" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
	      	至 <input id="d4312" class="Wdate" name="end" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
            <input name="" type="button" value="查询"  class="btn_s" onClick="javascript:subForm('pageForm')" /></td>
        </tr>
      </table>               
      <table class="tabs1"  style="margin-top:0px;">
		<tr onclick="selectedTD(this);">
          <th width="28">序号</th>
          <th>指标名称</th>
          <th>审计结果</th>
          <th>所属流程名称</th>
		  <th>审计时间<a href="javascript:void(0)"><img src="${path}/images/tabs_arr.png" align="absmiddle" /></a></th>
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
          <td>${entity.targetName?default("")}</td>
		  <td>
		   <#if entity.auditResult=='1'>
		   通过
		  <#else> 
		  不通过
		   </#if> 
		</td>
		  <td>${entity.flowName?default("")}</td>
		  <td>${entity.auditTime?default("")}</td>
          <td><a href="javascript:void(0)" id="hz0" onclick="opdg();" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />修正</a> <a href="javascript:void(0)" class="tabs1_cz"><img src="${path}/images/small9/s_shenhe.gif" />审核</a></td>
        </tr>
          </#list>
      </table>
       <div class="tabs_foot"> 
      <span class="tfl_btns">
      <span style="margin-left:8px;margin-right:8px;" /></span> 
      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
     </span> 
      
      
      <span class="page pageR"><@pager.pageTag/></span> 
      </div>
    </div>
   
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
 </form> 
 !--日期控件-->
<script type='text/javascript' src='${path}/js/datepicker/WdatePicker.js'></script>
<#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript">    
	var dg = new J.dialog({ id:'hz0', page:'wangs/DQC/content_edit.html',title:'人工修正', cover:true ,rang:true,width:670,height:210,resize:false});
	function opdg(){
		dg.ShowDialog();
	}
</script>
</body>
</html>
