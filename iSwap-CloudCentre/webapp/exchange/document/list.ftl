<#include "/common/taglibs.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档上传管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body onclick="parent.hideMenu()">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/title/document.png"  align="absmiddle" />文档上传管理</div>
      <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" id="hz1" onclick="opdg('${path}/exchange/document/document!multUploadView.action','上传文档','400','260')"><b><img src="${path}/images/small9/up.gif" class="cmb_img" />上传文档<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add"/></a></b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
    <form name="pageForm" id="pageForm" method="post" action="${path}/exchange/document/document!list.action"/>
    <table class="main_drop">
        <tr>
          <td align="right">文档名称：
            <input name="conditions[e.documentName,string,like]" type="text" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_documentName?default('')}"/>
            文档上传时间：
            <input id="d4311" class="Wdate" name="conditions[e.uploadDate,date,ge]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_uploadDate_ge?default('')}" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})"/>
        至
           <input id="d4312" class="Wdate" name="conditions[e.uploadDate_date,date,le]" onkeypress="showKeyPress()" onpaste="return false" value="${serchMap.e_uploadDate_date_le?default("")}" type="text" readonly="readonly" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})"/>
            <input type="submit" value="查询"  class="btn_s"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr>
          <th width="20">&nbsp;</th>
          <th width="28">序号</th>
          <th>文档名称</th>
          <th>指标项名称</th>
          <#if center?exists><th>文档提供部门</th></#if> 
          <th>文档交换状态</th>
          <th>文档上传时间</th>
          <th>操作</th>
        </tr>
         <#list listDatas as entity>
          <tr <#if (entity_index+1)%2==0>class="trbg"</#if> onclick="selectedTD(this);">
          <td><input name="ids" type="checkbox" value="${entity.id}" /></td>
          <td width="28">${entity_index+1} </td>
          <td>${entity.documentName?default('')}</td>
          <td>${entity.changeItem.itemName?default('')}</td>
          <#if center?exists><td>${entity.changeItem.sysDept.deptName?default('')}</td></#if>
          <td><#if entity.exchangeState?exists><#if entity.exchangeState=='0'>交换失败<#else>交换成功</#if><#else>未交换</#if></td>
          <td>${entity.uploadDate?default('空')}</td>
          <td><a href="javascript:void(0)" class="tabs1_cz" id="hz2" onclick="opdg('${path}/exchange/document/document!updateView.action?id=${entity.id}','上传文档','450','260')"><img src="${path}/images/small9/up.gif" /><#if entity.uploadState?exists><#if entity.uploadState=='0'>上传<#else>重新上传</#if><#else>上传</#if></a>
          <a href="#" class="tabs1_cz" onclick="del('${path}/exchange/document/document!delete.action?ids=${entity.id}')"><img src="${path}/images/czimg_sc.gif" />删除</a></td>
          </tr>
        </#list>
      </table>
      <div class="tabs_foot"> 
      <span class="tfl_btns">
			      <img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px;margin-right:8px;" />
			      <a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a>
			      <img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" />
			      <a href="javascript:void(0)" class="tfl_blink" onclick="delMany('${path}/exchange/document/document!delete.action')"><b class="hot">删除</b></a>
      </span> <span class="page pageR">
   		<@pager.pageTag/></span>
   		</div>
   			</form>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</body>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<!--通用方法-->
<!-- 增删改查 提交js操作-->
<script type="text/javascript">    
	function updateStatus(type){
		  document.getElementById('pageForm').action="user!updateStatus.action?status="+type;
		  var state = "";
		  if('0'==type){state="确定冻结模板？"}
		  if('1'==type){state="确定激活模板？"}
		  if(checks()) {
			  if(confirm(state)) {
			 	document.getElementById('pageForm').submit();
			  }
		  } else {
		  	alert('至少选择一条数据');
		  }
	}
</script>
</html>