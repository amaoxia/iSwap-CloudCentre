<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<#include "/common/taglibs.ftl">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Mapping管理</title>
<link href="${path}/css/main.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/tree.css" rel="stylesheet" type="text/css" />
<link href="${path}/css/pop.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='${path}/js/jquery-1.5.1.js'></script>
</head>
<body onclick="parent.hideMenu()">
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title"><img src="${path}/images/big/Mapping.png"  align="absmiddle" />Mapping管理</div>
	  <div class="c_m_btn"> <span class="cm_btn_m"><a href="javascript:void(0)" ><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />注册Mapping<a href="javascript:void(0)"><img src="${path}/images/bullet_add.png" class="bullet_add" /></a></b></a></span><span class="cm_btn_m"> <a href="javascript:void(0)" onclick="opdg('${path}/cloudnode/mapperMsg/mapper!uploadView.action','Mapper上传','650','500');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />Mapper上传</b></a></span><span class="cm_btn_m">&nbsp;</span><span class="cm_btn_m"> <a href="javascript:void(0)" onclick="opdg('${path}/cloudnode/mapperMsg/mapper!multUploadView.action','Mapper上传','650','580');"><b><img src="${path}/images/cmb_xj.gif" class="cmb_img" />多Mapper上传</b></a></span><span class="cm_btn_m">&nbsp;</span></div>
    </div>
    <div class="main_c">
    <form name="sel" id="pageForm" action="${path}/cloudnode/mapperMsg/mapper!list.action" method="post">
    <table class="main_drop">
        <tr>
          <td align="right">Mapping名称：
            <input name="mapName" type="text" value="${mapName?default("")}" onkeypress="showKeyPress()"/><!-- onpaste="return false"-->
           	 数据格式：
            <select><option>DB库</option><option>EXCEL</option><option>XML</option></select>
			<#if sysdeptList?exists>所属部门：
            <select name="deptId"><option value="">---请选择部门-</option><#list sysdeptList as  dept><option value="${dept.id}" <#if deptId?exists><#if dept.id.toString()==deptId.toString()>selected</#if></#if> >${dept.deptName?default('')}</option></#list></select></#if>
            <input name="" type="button" value="查询"  class="btn_s" onclick="selectMapping();"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr>
          <th width="20">&nbsp;</th>
          <th width="28">序号</th>
          <th>Mapping名称</th>
          <th>Mapping文件名</th>
		  <th>数据格式</th>
		  <th>Mapping模式</th>
          <th>所属部门</th>
		  <th>状态</th>
          <th width="190">操  作</th>
        </tr>
        <#list listDatas as entity>
        <tr <#if entity_index%2==0>class="trbg"</#if>  onclick="selectedTD(this);">
          <td><input name="ids" type="checkbox" value="${entity.id}" <#if entity.status=='1'>disabled</#if> /></td>
          <td width="28">${entity_index+1}</td>
          <td>${entity.mapName?default('')}</td>
          <td>${entity.mapCode?default('')}</td>		  
          <td>${entity.dataType?default('无')}</td>
		  <td><#if entity.mappingType=='0'>发送</#if> <#if entity.mappingType=='1'>接收</#if></td>
          <td>${entity.dept.deptName?default('')}</td>
		  <td><#if entity.status=='0'><font class="font_red">未部署</font></#if><#if entity.status=='1'>已部署</#if></td>
          <td width="190"><a id="hz2" href="#" <#if entity.status=='1'> style="color:#ccc;" <#else>onclick="opdg('${path}/cloudnode/mapperMsg/mapper!updateView.action?id='+${entity.id},'编辑Mapper','650','580');" </#if> class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;<!--<a id="hz4" href="javascript:alertMsg('测试正常！');" class="tabs1_cz" ><img src="${path}/images/small9/s_ceshi.gif" />测试</a>&nbsp;--><#if entity.status=='0'><a href="javascript:void(0);" onclick="updateStatus('${path}/cloudnode/mapperMsg/mapper!updateStatus.action?status=1&ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_bushu.gif" />部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署</a></#if><#if entity.status=='1'><a href="javascript:void(0);" onclick="updateStatus('${path}/cloudnode/mapperMsg/mapper!updateStatus.action?status=0&ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_bushu.gif" />取消部署</a></#if></td>
        </tr>
		</#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:void(0)" onclick="delMany('${path}/cloudnode/mapperMsg/mapper!delMany.action');" class="tfl_blink"><b class="hot">删除</b></a></span> 
      <span class="page pageR"><@pager.pageTag/></span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<script>    
function selectMapping(){
document.sel.submit();
}

//修改状态
function updateStatus(url){
	document.forms['sel'].action = url;
	document.forms['sel'].submit();
}
</script>
</body>
</html>
