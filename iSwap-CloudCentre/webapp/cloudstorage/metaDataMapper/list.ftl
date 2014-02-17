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
<body>
<div class="frameset_w" style="height:100%">
  <div class="main">
    <div class="common_menu">
      <div class="c_m_title">
      	<img src="${path}/images/big/Mapping.png"  align="absmiddle" />
      	Mapping管理
      </div>
	  <div class="c_m_btn"> 
	  	<span class="cm_btn_m">
	  		<a href="#" onclick="opChild('${path}/cloudstorage/metaDataMapper/metaDataMapper!addView.action?metaDataId=${metaDataId}&deptId=${deptId}','新建Mapper','800','450');return false;"><#-- opdg('${path}/exchange/mapper/mapper!upload.action','mapper配置','500','270');-->
		  		<b>
		  			<img src="${path}/images/cmb_xj.gif" class="cmb_img" />
		  			新建Mapper
		  			<a href="javascript:void(0)">
		  				<img src="${path}/images/bullet_add.png" class="bullet_add" />
		  			</a>
		  		</b>
	  		</a>
	  	</span>
	  </div>
    </div>
    <div class="main_c">
    <form name="sel" id="pageForm" action="${path}/cloudstorage/metaDataMapper/metaDataMapper!list.action" method="post">
    <input name="metaDataId" type="hidden" value="${metaDataId}"/>
    <input name="deptId" type="hidden" value="${deptId}"/>
    <table class="main_drop">
        <tr>
          <td align="right">Mapper名称：
            <input name="mapName" type="text" value="${mapName?default("")}" onkeypress="showKeyPress()"/><!-- onpaste="return false"-->
            <input name="" type="button" value="查询"  class="btn_s" onclick="selectMapping();"/></td>
        </tr>
      </table>
      <table class="tabs1" style="margin-top:0px;">
        <tr>
          <th width="20">&nbsp;</th>
          <th width="28">序号</th>
          <th>Mapping名称</th>
          <th>Mapping文件名</th>
          <th>来源部门</th>
           <th>来源指标</th>
		  <th>状态</th>
          <th width="190">操  作</th>
        </tr>
        <#list listDatas as entity>
        <tr <#if entity_index%2==0>class="trbg"</#if>  onclick="selectedTD(this);">
          <td><input name="ids" type="checkbox" value="${entity.id}" <#if entity.status=='1'>disabled</#if> /></td>
          <td width="28">${entity_index+1}</td>
          <td>${entity.mapName?default('')}</td>
          <td>${entity.mapCode?default('')}</td>		  
		  <td><#if entity.srcChangeItem?exists>${entity.srcChangeItem.sysDept.deptName?default('无')}</#if></td>
          <td><#if entity.srcChangeItem?exists>${entity.srcChangeItem.itemName?default('无')}</#if></td>
		  <td><#if entity.status=='0'><font class="font_red">未部署</font></#if><#if entity.status=='1'>已部署</#if></td>
          <td width="190">
          <a href="javascript:void(0)" id="hz0" onclick="opChild('${path}/cloudstorage/metaDataMapper/metaDataMapper!view.action?id='+${entity.id},'查看','650','580');" class="tabs1_cz">
          		<img src="${path}/images/small9/s_chakan.gif" />查看
         	</a> 
          <a id="hz2" href="#" <#if entity.status=='1'> style="color:#ccc;" <#else>onclick="opChild('${path}/cloudstorage/metaDataMapper/metaDataMapper!updateView.action?id='+${entity.id},'编辑Mapper','650','580');" </#if> class="tabs1_cz"><img src="${path}/images/czimg_edit.gif" />编辑</a>&nbsp;<!--<a id="hz4" href="javascript:alertMsg('测试正常！');" class="tabs1_cz" ><img src="${path}/images/small9/s_ceshi.gif" />测试</a>&nbsp;--><#if entity.status=='0'><a href="javascript:void(0);" onclick="updateStatus('${path}/cloudstorage/metaDataMapper/metaDataMapper!updateStatus.action?status=1&ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_bushu.gif" />部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;署</a></#if><#if entity.status=='1'><a href="javascript:void(0);" onclick="updateStatus('${path}/cloudstorage/metaDataMapper/metaDataMapper!updateStatus.action?status=0&ids=${entity.id}');" class="tabs1_cz" ><img src="${path}/images/small9/s_bushu.gif" />取消部署</a></#if></td>
        </tr>
		</#list>
      </table>
      <div class="tabs_foot"> <span class="tfl_btns"><img src="${path}/images/tabsf_arrow.gif" align="bottom" style="margin-left:8px; margin-right:8px;" /><a href="javascript:void(0);" onclick="checkedAll(this,'ids');return false;">全选</a><img src="${path}/images/tabsf_line.jpg" align="absmiddle" style="margin:0 4px 0 10px;" /><a href="javascript:void(0)" onclick="delMany('${path}/cloudstorage/metaDataMapper/metaDataMapper!delMany.action');" class="tfl_blink"><b class="hot">删除</b></a></span> 
      <span class="page pageR"><@pager.pageTag/></span> </div>
    </div>
    <div class="clear"></div>
  </div>
  <div class="clear"></div>
</div>
</form>
</body>
</html>
<#include "/common/commonList.ftl">
<#include "/common/commonLhg.ftl">
<#include "/common/commonUd.ftl">
<script>    
DG.removeBtn('save');
DG.removeBtn('reset');

function selectMapping(){
document.sel.submit();
}

//修改状态
function updateStatus(url){
	document.forms['sel'].action = url;
	document.forms['sel'].submit();
}

</script>
