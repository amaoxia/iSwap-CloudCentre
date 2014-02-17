<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<#include "/common/taglibs.ftl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/lurt.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body >
<div class="frameset_w" style="height:100%" onclick="parent.hideMenu()">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/img_05.png"  align="absmiddle" />审计规则库</div>
      <div class="c_m_btn"> 
      <span class="cm_btn_m"><a href="javascript:void(0)" id="hz0" onclick="addRules();">
      <b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />新建规则</b></a></span>
      <span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
       <form name="pageForm" action="ruleFileAction!list.action" method="post">                               
           <table class="main_drop">
        <tr>
          <td align="right">
		  审计规则名称：<input name="conditions[e.fileName,string,like]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_fileName?default("")}"  type="text" />
		  创建人：<input name="conditions[e.creater,string,like]" value="${serchMap.e_creater?default("")}"  type="text" />
		<!--  创建时间：<input id="d4311" class="Wdate" name="start" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
	      	至 <input id="d4312" class="Wdate" name="end" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>-->
            <input name="" type="submit" value="查询"  class="btn_s" onClick="javascript:subForm('pageForm')" /></td>
        </tr>
      </table>                              
      <table class="tabs1"  style="margin-top:0px;">
        <tr onclick="selectedTD(this);">
          <th width="20"><a href="javascript:void(0);" onclick="checkedAll(this,'check');return false;"></a></th>
          <th width="28">序号</th>
          <th>审计规则名称</th>
          <th>所属应用</th>
          <th>创建人</th>
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
          <td>${entity.fileName?default("")}</td>
		  <td>${entity.applyTo?default("")}</td>
		  <td>${entity.creater?default("")}</td>
		  <td>${entity.creationTime?default("")}</td>
           <td>
		  <a href="javascript:void(0)" id="hz0" onclick="editRules(${entity.id});" class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>
		  <a href="javascript:void(0)"  onclick="del('ruleFileAction!delete.action?ids=${entity.id}');" class="tabs1_cz"><img src="${path}/images/czimg_sc.gif" />删除</a>
		  <a href="javascript:void(0)"  onclick="test(${entity.id});"  class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />测试</a>
		  </td>
        </tr>
           </#list>
      
      </table>
       <div class="tabs_foot"> 
      <span class="tfl_btns">
      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
     <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
       <a href="javascript:delMany('ruleFileAction!delete.action')" class="tfl_blink"><b class="hot">删除</b></a></span> 
      
      
      <span class="page pageR"><@pager.pageTag/></span> 
      </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
 </form>  
 <!--日期控件-->
<#include "/common/commonLhg.ftl">
<#include "/common/commonList.ftl">
<script type="text/javascript">
	<!--页面上弹出窗口-->
	function showWindow(url,titleValue,widths,heights){
	    opdg(url,titleValue,widths,heights);
		//var dg = new J.dialog({ page:url,title:titleValue, cover:true ,rang:true,width:widths,height:heights,autoSize:true});
	    //dg.ShowDialog();
	}
	 function addRules(){
     	opdg('${path}/iswapqa/rule/ruleAction!addRulesShow1.action','新建规则',800,550);
     	
     }
	 function editRules(id){
     	opdg('${path}/iswapqa/rule/ruleAction!editRulesShow1.action?ruleFileId='+id,'新建规则',800,550);
     }
     function test(id){
     	opdg('${path}/iswapqa/rule/ruleFileAction!test.action?ruleFileId='+id,'测试',800,550);
     }
</script>
</body>
</html>